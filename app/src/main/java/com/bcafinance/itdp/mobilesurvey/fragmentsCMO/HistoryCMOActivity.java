package com.bcafinance.itdp.mobilesurvey.fragmentsCMO;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.bcafinance.itdp.mobilesurvey.HomeMenu.HomeBMActivity;
import com.bcafinance.itdp.mobilesurvey.HomeMenu.HomeCMOActivity;
import com.bcafinance.itdp.mobilesurvey.HomeMenu.HomeRMActivity;
import com.bcafinance.itdp.mobilesurvey.R;
import com.bcafinance.itdp.mobilesurvey.helper.APIUtilities;
import com.bcafinance.itdp.mobilesurvey.helper.HistoryKonsumen.Datum;
import com.bcafinance.itdp.mobilesurvey.helper.HistoryKonsumen.HistoryKonsumen;
import com.bcafinance.itdp.mobilesurvey.helper.RequestAPIServices;
import com.bcafinance.itdp.mobilesurvey.utility.ListAdapter;
import com.bcafinance.itdp.mobilesurvey.utility.SessionManager;
import com.bcafinance.itdp.mobilesurvey.utility.inputSurvey;
//import com.bcafinance.itdp.mobilesurvey.utility.input_survey;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryCMOActivity extends AppCompatActivity {
    private Context context = this;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<Datum> dataSurvey;
    private ListAdapter listAdapter;
    RequestAPIServices apiServices;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_cmo);
        apiServices = APIUtilities.getAPIServices();

        recyclerView = findViewById(R.id.datalist);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(layoutManager);
        MyRecyclerView();
        GetData();
    }

    private void GetData(){
        String token = SessionManager.getToken(context);
        Toast.makeText(getApplicationContext(), "Mohon Tunggu Sebentar...", Toast.LENGTH_LONG).show();
        apiServices.historyKonsumen("bearer "+token).enqueue(new Callback<HistoryKonsumen>() {
            @Override
            public void onResponse(Call<HistoryKonsumen> call, Response<HistoryKonsumen> response) {
//                if (response.code() == 200) {
//                    dataSurvey = new ArrayList<>();
//                    dataSurvey survey = response.body().setData(Datum);
//                    survey
//                    dataSurvey.add(survey);
//                    Toast.makeText(getApplicationContext(),"Data Berhasil Dimuat", Toast.LENGTH_LONG).show();
//                }
//                listAdapter = new ListAdapter(dataSurvey, context);
//                recyclerView.setAdapter(listAdapter);
//                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<HistoryKonsumen> call, Throwable t) {

            }
        });
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                dataSurvey = new ArrayList<>();
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
//                    inputSurvey survey = snapshot.getValue(inputSurvey.class);
//                    survey.setKey(snapshot.getKey());
//                    dataSurvey.add(survey);
//                    Toast.makeText(getApplicationContext(),"Data Berhasil Dimuat", Toast.LENGTH_LONG).show();
//                }
//                listAdapter = new ListAdapter (dataSurvey, context);
//                recyclerView.setAdapter(listAdapter);
//                listAdapter.notifyDataSetChanged();
//            }
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(getApplicationContext(),"Data Gagal Dimuat", Toast.LENGTH_LONG).show();
//                Log.e("MyListActivity", databaseError.getDetails()+" "+databaseError.getMessage());
//            }
//        });
    }

    private void MyRecyclerView(){
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.line));
        recyclerView.addItemDecoration(itemDecoration);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        backKeHome();
    }

    private void backKeHome(){
//        Bundle extra = getIntent().getExtras();
        String text = SessionManager.getPosition(context);
        if (text.equals("BM")) {
            Intent intent = new Intent(context, HomeBMActivity.class);
            startActivity(intent);
        }else if (text.equals("RM")) {
            Intent intent = new Intent(context, HomeRMActivity.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(context, HomeCMOActivity.class);
            startActivity(intent);
        }
        finish();
    }
}
