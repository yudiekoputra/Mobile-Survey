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

public class HistoryCMOActivity extends AppCompatActivity {
    private Context context = this;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private DatabaseReference reference;
    private ArrayList<inputSurvey> dataSurvey;
    private FirebaseAuth auth;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_cmo);

        recyclerView = findViewById(R.id.datalist);
        auth = FirebaseAuth.getInstance();
        MyRecyclerView();
        GetData();
    }

    private void GetData(){
        Toast.makeText(getApplicationContext(), "Mohon Tunggu Sebentar...", Toast.LENGTH_LONG).show();
        reference = FirebaseDatabase.getInstance().getReference();
        reference.child("inputSurvey").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataSurvey = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    inputSurvey survey = snapshot.getValue(inputSurvey.class);
                    survey.setKey(snapshot.getKey());
                    dataSurvey.add(survey);
                    Toast.makeText(getApplicationContext(),"Data Berhasil Dimuat", Toast.LENGTH_LONG).show();
                }
                listAdapter = new ListAdapter (dataSurvey, context);
                recyclerView.setAdapter(listAdapter);
                listAdapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Data Gagal Dimuat", Toast.LENGTH_LONG).show();
                Log.e("MyListActivity", databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
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
        if (text.equals("CMO")){
            Intent intent = new Intent(context, HomeCMOActivity.class);
            startActivity(intent);
        }else if (text.equals("BM")) {
            Intent intent = new Intent(context, HomeBMActivity.class);
            startActivity(intent);
        }else if (text.equals("RM")) {
            Intent intent = new Intent(context, HomeRMActivity.class);
            startActivity(intent);
        }
        finish();
    }
}
