package com.bcafinance.itdp.mobilesurvey.utility;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bcafinance.itdp.mobilesurvey.R;
//import com.bcafinance.itdp.mobilesurvey.fragmentsCMO.FormKonfirmasiSurvey;
import com.bcafinance.itdp.mobilesurvey.fragmentsCMO.ViewSurveyActivity;
import com.bcafinance.itdp.mobilesurvey.helper.HistoryKonsumen.Datum;
import com.bcafinance.itdp.mobilesurvey.helper.HistoryKonsumen.HistoryKonsumen;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{
    private ArrayList<Datum> listSurvey;
    private Context context;

    public ListAdapter(ArrayList<Datum> listSurvey, Context context){
        this.listSurvey = listSurvey;
        this.context = context;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        final String namaKonsumen = listSurvey.get(position).getNamaKonsumen();
        final String mobileId = listSurvey.get(position).getMobileID();
        final String tanggalSurvey = listSurvey.get(position).getTanggalInput();

        holder.namaKonsumen.setText("Nama : "+namaKonsumen);
        holder.mobileId.setText("Mobile ID : "+mobileId);
        holder.tanggalSurvey.setText("Tanggal Input : "+tanggalSurvey);

        holder.ListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TemporaryData.setDataListAdapter(listSurvey.get(position));
                Bundle bundle = new Bundle();
                bundle.putString("getPrimaryKey",listSurvey.get(position).getMobileID());

//                Intent intent = new Intent(v.getContext(), FormKonfirmasiSurvey.class);
                Intent intent = new Intent(v.getContext(), ViewSurveyActivity.class);
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return listSurvey.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView namaKonsumen, mobileId, tanggalSurvey;
        private LinearLayout ListItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaKonsumen = itemView.findViewById(R.id.namaKonsumen);
            mobileId = itemView.findViewById(R.id.mobileId);
            tanggalSurvey = itemView.findViewById(R.id.tanggalInputSurvey);
            ListItem = itemView.findViewById(R.id.list_item);
        }
    }
}
