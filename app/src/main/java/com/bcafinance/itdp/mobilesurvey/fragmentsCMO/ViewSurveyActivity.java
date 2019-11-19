package com.bcafinance.itdp.mobilesurvey.fragmentsCMO;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bcafinance.itdp.mobilesurvey.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

public class ViewSurveyActivity extends AppCompatActivity {
    CarouselView customCarouselView;
    int NUMBER_OF_IMAGES = 12;
    private TextView namaKonsumen, noTelp, noKTP, tanggalLahir, alamatRumah, kelurahanRumah, kecamatanRumah, kodePosRumah, namaPasangan, jmlTanggungan,
    namaIbuKandung, namaTempatUsaha, alamatUsaha, kelurahanUsaha, kecamatanUsaha, kodePosUsaha, merkTipe, warnaMobil, dealerShowroom, tanggalSurvey, jamSurvey, informasiTambahan;

//    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_survey);

//        databaseReference = FirebaseDatabase.getInstance().getReference();
//        databaseReference = databaseReference.child("inputSurvey");

        customCarouselView = findViewById(R.id.carouselView);
        customCarouselView.setPageCount(NUMBER_OF_IMAGES);
        customCarouselView.setViewListener(viewListener);

        namaKonsumen = findViewById(R.id.namaKonsumen);
        noTelp = findViewById(R.id.noTelp);
        noKTP = findViewById(R.id.noKTP);
        tanggalLahir = findViewById(R.id.tanggalLahir);
        alamatRumah = findViewById(R.id.alamatRumah);
        kelurahanRumah = findViewById(R.id.kelurahanRumah);
        kecamatanRumah = findViewById(R.id.kecamatanRumah);
        kodePosRumah = findViewById(R.id.kodePosRumah);
        namaPasangan = findViewById(R.id.namaPasangan);
        jmlTanggungan = findViewById(R.id.jmlTanggungan);
        namaIbuKandung = findViewById(R.id.namaIbuKandung);
        namaTempatUsaha = findViewById(R.id.namaTempatUsaha);
        alamatUsaha = findViewById(R.id.alamatUsaha);
        kelurahanUsaha = findViewById(R.id.kelurahanUsaha);
        kecamatanUsaha = findViewById(R.id.kecamatanUsaha);
        kodePosUsaha = findViewById(R.id.kodePosUsaha);
        merkTipe = findViewById(R.id.merkTipe);
        warnaMobil = findViewById(R.id.warnaMobil);
        dealerShowroom = findViewById(R.id.dealerShowroom);
        tanggalSurvey = findViewById(R.id.tanggalSurvey);
        jamSurvey = findViewById(R.id.jamSurvey);
        informasiTambahan = findViewById(R.id.informasiTambahan);
    }

    ViewListener viewListener = new ViewListener() {
        @Override
        public View setViewForPosition(int position) {
            View customView = getLayoutInflater().inflate(R.layout.view_carousel, null);
            return customView;
        }
    };
}
