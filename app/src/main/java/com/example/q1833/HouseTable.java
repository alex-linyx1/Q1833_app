package com.example.q1833;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.q1833.adapter.HomeAdapter;
import com.example.q1833.model.Home;

import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.Region;

import java.util.ArrayList;
import java.util.List;

public class HouseTable extends AppCompatActivity implements MonitorNotifier {
    HomeAdapter homeAdapter;
    RecyclerView homeRecycler;
    List<Home> homes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_table);

        BeaconManager.getInstanceForApplication(this).addMonitorNotifier(this);

        homes = new ArrayList<>();
        homes.add(new Home(1, "house1"));
        homes.add(new Home(2, "house2"));
        homes.add(new Home(3, "house3"));
        homes.add(new Home(4, "house4"));
        homes.add(new Home(5, "house5"));
        homes.add(new Home(6, "house6"));

        homeRecycler = findViewById(R.id.HomeRecycler);

        setHomeRecycler();
    }



    private void setHomeRecycler() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(
                this,
                2,
                RecyclerView.VERTICAL,
                false);
        homeRecycler.setLayoutManager(layoutManager);

        homeAdapter = new HomeAdapter(homes, this);
        homeRecycler.setAdapter(homeAdapter);
    }

    @Override
    public void didEnterRegion(Region region) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                homes.get(0).setImg("house1_opened");
                homeAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void didExitRegion(Region region) {

    }

    @Override
    public void didDetermineStateForRegion(int i, Region region) {

    }

}