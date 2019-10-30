package com.bcafinance.itdp.mobilesurvey.fragmentsCMO;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bcafinance.itdp.mobilesurvey.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

public class ViewSurveyActivity extends AppCompatActivity {
    CarouselView customCarouselView;
    int NUMBER_OF_IMAGES = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_survey);

        customCarouselView = findViewById(R.id.carouselView);
        customCarouselView.setPageCount(NUMBER_OF_IMAGES);
        customCarouselView.setViewListener(viewListener);
    }

    ViewListener viewListener = new ViewListener() {
        @Override
        public View setViewForPosition(int position) {
            View customView = getLayoutInflater().inflate(R.layout.view_carousel, null);
            return customView;
        }
    };
}
