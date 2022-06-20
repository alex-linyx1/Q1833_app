package com.example.q1833;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class HouseHistory extends AppCompatActivity {

    CarouselView carousel;

    int[] listImages = {R.drawable.img_carousel, R.drawable.img_carousel2, R.drawable.img_carousel3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_history);

        carousel = findViewById(R.id.carouselView);
        carousel.setPageCount(listImages.length);
        carousel.setImageListener(imageListener);


    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(listImages[position]);
        }
    };

}