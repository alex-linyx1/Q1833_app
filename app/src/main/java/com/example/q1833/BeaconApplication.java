package com.example.q1833;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.Region;
import java.util.UUID;

public class BeaconApplication extends Application implements MonitorNotifier {
    public static Region beaconRegion = null;
    Identifier major, minor, uuid;
    public static boolean inReg = false;

    public void onCreate() {
        super.onCreate();
        major = Identifier.fromInt(8791);
        minor = Identifier.fromInt(2450);
        uuid = Identifier.fromUuid(UUID.fromString("E2C56DB5-DFFB-48D2-B060-D0F5A71096E0"));
        beaconRegion = new Region("beaconRegion", uuid, minor, major);
        BeaconManager beaconManager = org.altbeacon.beacon.BeaconManager.getInstanceForApplication(this);

        beaconManager.getBeaconParsers().clear();
        beaconManager.getBeaconParsers().add(new BeaconParser().
                setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));

        BeaconManager.setDebug(true);
        beaconManager.addMonitorNotifier(this);

        for (Region region: beaconManager.getMonitoredRegions()) {
            beaconManager.stopMonitoring(region);
        }

        beaconManager.startMonitoring(beaconRegion);
    }

    @Override
    public void didEnterRegion(Region region) {
        inReg = true;
        pushNotification();
    }

    private void pushNotification() {
        NotificationManager notificationManager =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification.Builder builder;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "Q1833 уведомления",
                    "Q1833 уведомления", NotificationManager.IMPORTANCE_HIGH);
            channel.enableLights(true);
            channel.enableVibration(true);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            notificationManager.createNotificationChannel(channel);
            builder = new Notification.Builder(this, channel.getId());
        }
        else {
            builder = new Notification.Builder(this);
            builder.setPriority(Notification.PRIORITY_HIGH);
        }

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntent(new Intent(this, HouseActivity.class));

        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        builder.setSmallIcon(R.drawable.logo);
        builder.setContentTitle("Поздравляем! Здание найдено!");
        builder.setContentText("Нажмите, чтобы прочитать про его историю...");
        builder.setContentIntent(resultPendingIntent);

        notificationManager.notify(1, builder.build());
    }

    @Override
    public void didExitRegion(Region region) {
    }

    @Override
    public void didDetermineStateForRegion(int i, Region region) {}
}
