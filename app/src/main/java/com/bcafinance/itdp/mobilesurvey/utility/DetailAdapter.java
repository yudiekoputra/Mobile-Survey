//package com.bcafinance.itdp.mobilesurvey.utility;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bcafinance.itdp.mobilesurvey.R;
//
//import java.util.ArrayList;
//
//public class DetailAdapter extends RecyclerView.Adapter<DetailViewHolder> {
//    private Context context;
//    ArrayList<inputSurvey> list ;
//
//    public DetailAdapter(Context context, ArrayList<inputSurvey> list){
//        this.context = context;
//        this.list = list;
//    }
//
//    @NonNull
//    @Override
//    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View view = inflater.inflate(R.layout.custom_list_approval,parent,false);
//
//        return new DetailViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull DetailViewHolder holder, int position) {
//        final inputSurvey inputSurvey = list.get(position);
//        DetailViewHolder.layoutApproval.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TemporaryData.setDataPengajuan(list.get(i));
//                Bundle bundle = new Bundle();
//                bundle.putString("getPrimaryKey",list.get(i).getKey());
//
//                Intent intent = new Intent(v.getContext(), ApprovalActivity.class);
//                intent.putExtras(bundle);
//                v.getContext().startActivity(intent);
//
//            }
//        });
//
//
//        //tampilkan data
//        approvalViewHolder.namaAktiva.setText(list.get(i).getNama_aktiva());
//        approvalViewHolder.namaPemohon.setText(list.get(i).getNama());
//        approvalViewHolder.tanggalPengajuan.setText(list.get(i).getTanggalPengajuan());
//        approvalViewHolder.statusPengajuan.setText(list.get(i).getStatus());
//
//
//
//    }
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//    class DetailViewHolder extends RecyclerView.ViewHolder {
//        public DetailViewHolder(@NonNull View itemView) {
//            super(itemView);
//        }
//    }
//}
//
//@Override
//public void onBindViewHolder(@NonNull ApprovalViewHolder approvalViewHolder,final int i) {
//
//
//
//
//
//
//
//@Override
//public int getItemCount() {
//        return list.size();
//        }
//
//public class ApprovalViewHolder extends RecyclerView.ViewHolder{
//
//    TextView namaAktiva,namaPemohon,tanggalPengajuan,statusPengajuan;
//    LinearLayout layoutApproval;
//    public ApprovalViewHolder(@NonNull View itemView) {
//        super(itemView);
//        namaAktiva = itemView.findViewById(R.id.namaAktiva);
//        layoutApproval = itemView.findViewById(R.id.layoutApproval);
//        namaPemohon = itemView.findViewById(R.id.namapemohon);
//        tanggalPengajuan = itemView.findViewById(R.id.tanggalPengajuan);
//        statusPengajuan = itemView.findViewById(R.id.statusPengajuan);
//    }
//}