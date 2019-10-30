//package com.bcafinance.itdp.mobilesurvey.fragmentsCMO;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.AlertDialog;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.net.Uri;
//import android.opengl.Matrix;
//import android.os.Bundle;
//import android.se.omapi.Session;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.bcafinance.itdp.mobilesurvey.HomeMenu.HomeBMActivity;
//import com.bcafinance.itdp.mobilesurvey.HomeMenu.HomeCMOActivity;
//import com.bcafinance.itdp.mobilesurvey.HomeMenu.HomeRMActivity;
//import com.bcafinance.itdp.mobilesurvey.R;
//import com.bcafinance.itdp.mobilesurvey.customs.CustomExpandCollapseBar;
//import com.bcafinance.itdp.mobilesurvey.fragmentsCMO.Edit_Form_Survey.FormIdentitasKonsumenEdit;
//import com.bcafinance.itdp.mobilesurvey.fragmentsCMO.Edit_Form_Survey.FormInformasiTambahanEdit;
//import com.bcafinance.itdp.mobilesurvey.fragmentsCMO.Edit_Form_Survey.FormPengamatanSurveyorEdit;
//import com.bcafinance.itdp.mobilesurvey.fragmentsCMO.Edit_Form_Survey.FormSurveyKonsumenEdit;
//import com.bcafinance.itdp.mobilesurvey.fragmentsCMO.Edit_Form_Survey.FormSurveyLingkunganUsahaEdit;
//import com.bcafinance.itdp.mobilesurvey.fragmentsCMO.Edit_Form_Survey.FormSurveyRumahKonsumenEdit;
//import com.bcafinance.itdp.mobilesurvey.fragmentsCMO.Edit_Form_Survey.FormVerifikasiLingkunganRumahEdit;
//import com.bcafinance.itdp.mobilesurvey.helper.BitmapHelper;
//import com.bcafinance.itdp.mobilesurvey.utility.Constanta;
//import com.bcafinance.itdp.mobilesurvey.utility.LoadingClass;
//import com.bcafinance.itdp.mobilesurvey.utility.SessionManager;
////import com.bcafinance.itdp.mobilesurvey.utility.data_survey;
//import com.bcafinance.itdp.mobilesurvey.utility.inputSurvey;
//import com.github.aakira.expandablelayout.ExpandableLinearLayout;
//import com.github.chrisbanes.photoview.PhotoView;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Locale;
//
//import static android.text.TextUtils.isEmpty;
//import static com.bcafinance.itdp.mobilesurvey.utility.SessionManager.getAlamatRumah;
//import static com.bcafinance.itdp.mobilesurvey.utility.SessionManager.getAlamatUsaha;
//import static com.bcafinance.itdp.mobilesurvey.utility.SessionManager.getJamSurvey;
//import static com.bcafinance.itdp.mobilesurvey.utility.SessionManager.getJmlTanggungan;
//import static com.bcafinance.itdp.mobilesurvey.utility.SessionManager.getKecamatanRumah;
//import static com.bcafinance.itdp.mobilesurvey.utility.SessionManager.getKecamatanUsaha;
//import static com.bcafinance.itdp.mobilesurvey.utility.SessionManager.getKelurahanRumah;
//import static com.bcafinance.itdp.mobilesurvey.utility.SessionManager.getKelurahanUsaha;
//import static com.bcafinance.itdp.mobilesurvey.utility.SessionManager.getKodePosRumah;
//import static com.bcafinance.itdp.mobilesurvey.utility.SessionManager.getKodePosUsaha;
//import static com.bcafinance.itdp.mobilesurvey.utility.SessionManager.getMerkMobil;
//import static com.bcafinance.itdp.mobilesurvey.utility.SessionManager.getNamaIbuKandung;
//import static com.bcafinance.itdp.mobilesurvey.utility.SessionManager.getNamaKonsumen;
//import static com.bcafinance.itdp.mobilesurvey.utility.SessionManager.getNamaPasangan;
//import static com.bcafinance.itdp.mobilesurvey.utility.SessionManager.getNamaUsaha;
//import static com.bcafinance.itdp.mobilesurvey.utility.SessionManager.getNoKTP;
//import static com.bcafinance.itdp.mobilesurvey.utility.SessionManager.getNoTelp;
//import static com.bcafinance.itdp.mobilesurvey.utility.SessionManager.getTanggalLahir;
//import static com.bcafinance.itdp.mobilesurvey.utility.SessionManager.getTanggalSurvey;
//import static com.bcafinance.itdp.mobilesurvey.utility.SessionManager.getTempatLahir;
//import static com.bcafinance.itdp.mobilesurvey.utility.SessionManager.getUsername;
//import static com.bcafinance.itdp.mobilesurvey.utility.SessionManager.getWarnaMobil;
//import static com.bcafinance.itdp.mobilesurvey.utility.SessionManager.saveSurveyLingkunganUsaha;
//
//public class FormKonfirmasiSurvey extends AppCompatActivity {
//    private Context context = this;
//    private CustomExpandCollapseBar expandCollapseBar;
//    private TextView namaKonsumen, noKTP, tempatLahir, tanggalLahir, alamatRumah, kelurahanRumah, kecamatanRumah,kodePosRumah, noTelp, namaPasangan,
//    jmlTanggungan, namaIbuKandung, namaTempatUsaha, alamatUsaha, kelurahanUsaha, kecamatanUsaha, kodePosUsaha, merkMobil, warnaMobil, dealerShowroom, tanggalSurvey,jamSurvey;
//    private TextView pertanyaanSatu, pertanyaanDua, platNomor, merkTipe, warna, tahunKendaraan, pertanyaanLima, jenisKredit, totalAngsuran, totalPenghasilan;
//    private TextView pertanyaanEnam, pertanyaanTujuh, pertanyaanDelapan, pertanyaanSembilan, pertanyaanSepuluh, pertanyaanSebelas, pertanyaanDuaBelas, pertanyaanTigaBelas;
//    private TextView namaNarasumber1, namaNarasumber2, pertanyaanEmpatBelas, pertanyaanLimaBelas, pertanyaanEnamBelas, pertanyaanTujuhBelas, pertanyaanDelapanBelas, pertanyaanSembilanBelas,
//            pertanyaanDuaPuluh, pertanyaanDuaSatu, pertanyaanDuaDua, pertanyaanDuaTiga, pertanyaanDuaEmpat, pertanyaanDuaLima, pertanyaanDuaEnam, pertanyaanDuaTujuh, jawabanEnamBelas,
//            jawabanTujuhBelas, jawabanDelapanBelasTipe, jawabanDelapanBelasWarna, jawabanDuaPuluh, jawabanDuaTiga, jawabanDuaEmpat, jawabanDuaLimaTipe, jawabanDuaLimaWarna, jawabanDuaTujuh;
//    private TextView pertanyaanDuaDelapan, pertanyaanDuaSembilan, pertanyaanTigaPuluh, pertanyaanTigaSatu, jawabanDuaDelapan, jawabanDuaSembilan;
//    private TextView pertanyaanTigaDua, pertanyaanTigaTiga, pertanyaanTigaEmpat, pertanyaanTigaLima, jawabanTigaDua, jawabanTigaTiga, jawabanTigaEmpat, jawabanTigaLima, namaNarasumber3, namaNarasumber4;
//    private TextView informasiTambahan;
//    private TextView namaAlamatRumah, namaAlamatKantor, latitude1, latitude2, longitude1, longitude2;
//    private ImageView imageRumah1, imageRumah2, imageJalanRumah1, imageJalanRumah2, imageKantor1, imageKantor2, imageJalanKantor1, imageJalanKantor2;
//    Button buttonSumbitAll;
//    boolean clicked = false;
//    private FirebaseAuth auth;
//    String bm;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_form_konfirmasi_survey);
//
//        TextView barExpandDataDiri =findViewById(R.id.barExpandDataDiri);
//        ExpandableLinearLayout layoutExpandDataDiri = findViewById(R.id.layoutExpandDataDiri);
//        expandCollapseBar = new CustomExpandCollapseBar(barExpandDataDiri, layoutExpandDataDiri,false);
//        namaKonsumen = findViewById(R.id.namaKonsumen);
//        namaKonsumen.setText(SessionManager.getNamaKonsumen(context));
//        noKTP = findViewById(R.id.noKTP);
//        noKTP.setText(SessionManager.getNoKTP(context));
//        tempatLahir = findViewById(R.id.tempatLahir);
//        tempatLahir.setText(SessionManager.getTempatLahir(context));
//        tanggalLahir = findViewById(R.id.tanggalLahir);
//        tanggalLahir.setText(SessionManager.getTanggalLahir(context));
//         alamatRumah = findViewById(R.id.alamatRumah);
//        alamatRumah.setText(SessionManager.getAlamatRumah(context));
//         kelurahanRumah = findViewById(R.id.kelurahanRumah);
//        kelurahanRumah.setText(SessionManager.getKelurahanRumah(context));
//         kecamatanRumah = findViewById(R.id.kecamatanRumah);
//        kecamatanRumah.setText(SessionManager.getKecamatanRumah(context));
//         kodePosRumah = findViewById(R.id.kodePosRumah);
//        kodePosRumah.setText(SessionManager.getKodePosRumah(context));
//         noTelp = findViewById(R.id.noTelp);
//        noTelp.setText(SessionManager.getNoTelp(context));
//         namaPasangan = findViewById(R.id.namaPasangan);
//        namaPasangan.setText(SessionManager.getNamaPasangan(context));
//         jmlTanggungan = findViewById(R.id.jmlTanggungan);
//        jmlTanggungan.setText(SessionManager.getJmlTanggungan(context));
//         namaIbuKandung = findViewById(R.id.namaIbuKandung);
//        namaIbuKandung.setText(SessionManager.getNamaIbuKandung(context));
//         namaTempatUsaha = findViewById(R.id.namaTempatUsaha);
//        namaTempatUsaha.setText(SessionManager.getNamaUsaha(context));
//         alamatUsaha = findViewById(R.id.alamatUsaha);
//        alamatUsaha.setText(SessionManager.getAlamatUsaha(context));
//        kelurahanUsaha = findViewById(R.id.kelurahanUsaha);
//        kelurahanUsaha.setText(SessionManager.getKelurahanUsaha(context));
//         kecamatanUsaha = findViewById(R.id.kecamatanUsaha);
//        kecamatanUsaha.setText(SessionManager.getKecamatanUsaha(context));
//         kodePosUsaha = findViewById(R.id.kodePosUsaha);
//        kodePosUsaha.setText(SessionManager.getKodePosUsaha(context));
//         merkMobil = findViewById(R.id.merkMobil);
//        merkMobil.setText(SessionManager.getMerkMobil(context));
//         warnaMobil = findViewById(R.id.warnaMobil);
//        warnaMobil.setText(SessionManager.getWarnaMobil(context));
//         dealerShowroom = findViewById(R.id.dealerShowroom);
//        dealerShowroom.setText(SessionManager.getDealerShowroom(context));
//         tanggalSurvey = findViewById(R.id.tanggalSurvey);
//        tanggalSurvey.setText(SessionManager.getTanggalSurvey(context));
//         jamSurvey = findViewById(R.id.jamSurvey);
//        jamSurvey.setText(SessionManager.getJamSurvey(context));
////        Button buttonEdit1 = findViewById(R.id.buttonEdit1);
////        buttonEdit1.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                saveContent();
////            }
////        });
//
//        TextView barExpandSurveyKonsumen =findViewById(R.id.barExpandSurveyKonsumen);
//        ExpandableLinearLayout layoutSurveyKonsumen = findViewById(R.id.layoutSurveyKonsumen);
//        expandCollapseBar = new CustomExpandCollapseBar(barExpandSurveyKonsumen, layoutSurveyKonsumen,false);
//        pertanyaanSatu = findViewById(R.id.pertanyaanSatu);
//        pertanyaanSatu.setText(SessionManager.getPertanyaanSatu(context));
//        pertanyaanDua = findViewById(R.id.pertanyaanDua);
//        pertanyaanDua.setText(SessionManager.getPertanyaanDua(context));
//        platNomor = findViewById(R.id.platNomor);
//        platNomor.setText(SessionManager.getPlatNomor(context));
//        merkTipe = findViewById(R.id.merkTipe);
//        merkTipe.setText(SessionManager.getMerkTipe(context));
//        warna = findViewById(R.id.warna);
//        warna.setText(SessionManager.getWarna(context));
//        tahunKendaraan = findViewById(R.id.tahunKendaraan);
//        tahunKendaraan.setText(SessionManager.getTahunKendaraan(context));
//        jenisKredit = findViewById(R.id.jenisKredit);
//        jenisKredit.setText(SessionManager.getJenisKredit(context));
//        totalAngsuran = findViewById(R.id.totalAngsuran);
//        totalAngsuran.setText(SessionManager.getTotalAngsuran(context));
//        totalPenghasilan = findViewById(R.id.totalPenghasilan);
//        totalPenghasilan.setText(SessionManager.getTotalPenghasilan(context));
//        pertanyaanLima = findViewById(R.id.pertanyaanLima);
//        pertanyaanLima.setText(SessionManager.getPertanyaanLima(context));
////        Button buttonEdit2 = findViewById(R.id.buttonEdit2);
////        buttonEdit2.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                saveSurveyKonsumen();
////            }
////        });
//
//        TextView barExpandSurveyRumahKonsumen =findViewById(R.id.barExpandSurveyRumahKonsumen);
//        ExpandableLinearLayout layoutSurveyRumahKonsumen = findViewById(R.id.layoutSurveyRumahKonsumen);
//        expandCollapseBar = new CustomExpandCollapseBar(barExpandSurveyRumahKonsumen, layoutSurveyRumahKonsumen,false);
//        pertanyaanEnam = findViewById(R.id.pertanyaanEnam);
//        pertanyaanEnam.setText(SessionManager.getPertanyaanEnam(context));
//        pertanyaanTujuh = findViewById(R.id.pertanyaanTujuh);
//        pertanyaanTujuh.setText(SessionManager.getPertanyaanTujuh(context));
//        pertanyaanDelapan = findViewById(R.id.pertanyaanDelapan);
//        pertanyaanDelapan.setText(SessionManager.getPertanyaanDelapan(context));
//        pertanyaanSembilan = findViewById(R.id.pertanyaanSembilan);
//        pertanyaanSembilan.setText(SessionManager.getPertanyaanSembilan(context));
//        pertanyaanSepuluh = findViewById(R.id.pertanyaanSepuluh);
//        pertanyaanSepuluh.setText(SessionManager.getPertanyaanSepuluh(context));
//        pertanyaanSebelas = findViewById(R.id.pertanyaanSebelas);
//        pertanyaanSebelas.setText(SessionManager.getPertanyaanSebelas(context));
//        pertanyaanDuaBelas = findViewById(R.id.pertanyaanDuaBelas);
//        pertanyaanDuaBelas.setText(SessionManager.getPertanyaanDuaBelas(context));
//        pertanyaanTigaBelas = findViewById(R.id.pertanyaanTigaBelas);
//        pertanyaanTigaBelas.setText(SessionManager.getPertanyaanTigaBelas(context));
////        Button buttonEdit3 = findViewById(R.id.buttonEdit3);
////        buttonEdit3.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                saveSurveyRumahKonsumen();
////            }
////        });
//
//        TextView barExpandVerifikasiLingkunganRumah =findViewById(R.id.barExpandVerifikasiLingkunganRumah);
//        ExpandableLinearLayout layoutVerifikasiLingunganRumah = findViewById(R.id.layoutVerifikasiLingunganRumah);
//        expandCollapseBar = new CustomExpandCollapseBar(barExpandVerifikasiLingkunganRumah, layoutVerifikasiLingunganRumah,false);
//        pertanyaanEmpatBelas = findViewById(R.id.pertanyaanEmpatBelas);
//        pertanyaanLimaBelas = findViewById(R.id.pertanyaanLimaBelas);
//        pertanyaanEnamBelas = findViewById(R.id.pertanyaanEnamBelas);
//        pertanyaanTujuhBelas = findViewById(R.id.pertanyaanTujuhBelas);
//        pertanyaanDelapanBelas = findViewById(R.id.pertanyaanDelapanBelas);
//        pertanyaanSembilanBelas = findViewById(R.id.pertanyaanSembilanBelas);
//        pertanyaanDuaPuluh = findViewById(R.id.pertanyaanDuaPuluh);
//        pertanyaanDuaSatu = findViewById(R.id.pertanyaanDuaSatu);
//        pertanyaanDuaDua = findViewById(R.id.pertanyaanDuaDua);
//        pertanyaanDuaTiga = findViewById(R.id.pertanyaanDuaTiga);
//        pertanyaanDuaEmpat = findViewById(R.id.pertanyaanDuaEmpat);
//        pertanyaanDuaLima = findViewById(R.id.pertanyaanDuaLima);
//        pertanyaanDuaEnam = findViewById(R.id.pertanyaanDuaEnam);
//        pertanyaanDuaTujuh = findViewById(R.id.pertanyaanDuaTujuh);
//        jawabanEnamBelas = findViewById(R.id.jawabanEnamBelas);
//        jawabanTujuhBelas = findViewById(R.id.jawabanTujuhBelas);
//        jawabanDelapanBelasTipe = findViewById(R.id.jawabanDelapanBelasTipe);
//        jawabanDelapanBelasWarna = findViewById(R.id.jawabanDelapanBelasWarna);
//        jawabanDuaPuluh = findViewById(R.id.jawabanDuaPuluh);
//        jawabanDuaTiga = findViewById(R.id.jawabanDuaTiga);
//        jawabanDuaEmpat = findViewById(R.id.jawabanDuaEmpat);
//        jawabanDuaLimaTipe = findViewById(R.id.jawabanDuaLimaTipe);
//        jawabanDuaLimaWarna = findViewById(R.id.jawabanDuaLimaWarna);
//        jawabanDuaTujuh = findViewById(R.id.jawabanDuaTujuh);
//        namaNarasumber1 = findViewById(R.id.namaNarasumber1);
//        namaNarasumber2 = findViewById(R.id.namaNarasumber2);
//
//        namaNarasumber1.setText(SessionManager.getNamaNarasumber1(context));
//        namaNarasumber2.setText(SessionManager.getNamaNarasumber2(context));
//        pertanyaanEmpatBelas.setText(SessionManager.getPertanyaanEmpatBelas(context));
//        pertanyaanLimaBelas.setText(SessionManager.getPertanyaanLimaBelas(context));
//        pertanyaanEnamBelas.setText(SessionManager.getPertanyaanEnamBelas(context));
//        pertanyaanTujuhBelas.setText(SessionManager.getPertanyaanTujuhBelas(context));
//        pertanyaanDelapanBelas.setText(SessionManager.getPertanyaanDelapanBelas(context));
//        pertanyaanSembilanBelas.setText(SessionManager.getPertanyaanSembilanBelas(context));
//        pertanyaanDuaPuluh.setText(SessionManager.getPertanyaanDuaPuluh(context));
//        pertanyaanDuaSatu.setText(SessionManager.getPertanyaanDuaSatu(context));
//        pertanyaanDuaDua.setText(SessionManager.getPertanyaanDuaDua(context));
//        pertanyaanDuaTiga.setText(SessionManager.getPertanyaanDuaTiga(context));
//        pertanyaanDuaEmpat.setText(SessionManager.getPertanyaanDuaEmpat(context));
//        pertanyaanDuaLima.setText(SessionManager.getPertanyaanDuaLima(context));
//        pertanyaanDuaEnam.setText(SessionManager.getPertanyaanDuaEnam(context));
//        pertanyaanDuaTujuh.setText(SessionManager.getPertanyaanDuaTujuh(context));
//        jawabanEnamBelas.setText(SessionManager.getJawabanEnamBelas(context));
//        jawabanTujuhBelas.setText(SessionManager.getJawabanTujuhBelas(context));
//        jawabanDelapanBelasTipe.setText(SessionManager.getJawabanDelapanBelasTipe(context));
//        jawabanDelapanBelasWarna.setText(SessionManager.getJawabanDelapanBelasWarna(context));
//        jawabanDuaPuluh.setText(SessionManager.getJawabanDuaPuluh(context));
//        jawabanDuaTiga.setText(SessionManager.getJawabanDuaTiga(context));
//        jawabanDuaEmpat.setText(SessionManager.getJawabanDuaEmpat(context));
//        jawabanDuaLimaTipe.setText(SessionManager.getJawabanDuaLimaTipe(context));
//        jawabanDuaLimaWarna.setText(SessionManager.getJawabanDuaLimaWarna(context));
//        jawabanDuaTujuh.setText(SessionManager.getJawabanDuaTujuh(context));
//
////        Button buttonEdit4 = findViewById(R.id.buttonEdit4);
////        buttonEdit4.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                saveVerifikasiLingkunganRumah();
////            }
////        });
//
//        TextView barExpandPengamatanSurveyor =findViewById(R.id.barExpandPengamatanSurveyor);
//        ExpandableLinearLayout layoutPengamatanSurveyor = findViewById(R.id.layoutPengamatanSurveyor);
//        expandCollapseBar = new CustomExpandCollapseBar(barExpandPengamatanSurveyor, layoutPengamatanSurveyor,false);
//        pertanyaanDuaDelapan = findViewById(R.id.pertanyaanduaDelapan);
//        pertanyaanDuaSembilan = findViewById(R.id.pertanyaanDuaSembilan);
//        pertanyaanTigaPuluh = findViewById(R.id.pertanyaanTigaPuluh);
//        pertanyaanTigaSatu = findViewById(R.id.pertanyaanTigaSatu);
//        jawabanDuaDelapan = findViewById(R.id.jawabanDuaDelapan);
//        jawabanDuaSembilan = findViewById(R.id.jawabanDuaSembilan);
//
//        pertanyaanDuaDelapan.setText(SessionManager.getPertanyaanDuaDelapan(context));
//        pertanyaanDuaSembilan.setText(SessionManager.getPertanyaanDuaSembilan(context));
//        pertanyaanTigaPuluh.setText(SessionManager.getPertanyaanTigaPuluh(context));
//        pertanyaanTigaSatu.setText(SessionManager.getPertanyaanTigaSatu(context));
//        jawabanDuaDelapan.setText(SessionManager.getJawabanDuaDelapan(context));
//        jawabanDuaSembilan.setText(SessionManager.getJawabanDuaSembilan(context));
//
////        Button buttonEdit5 = findViewById(R.id.buttonEdit5);
////        buttonEdit5.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                savePengamatanSurveyor();
////            }
////        });
//
//        TextView barExpandSurveyTempatUsaha =findViewById(R.id.barExpandSurveyTempatUsaha);
//        ExpandableLinearLayout layoutSurveyTempatUsaha = findViewById(R.id.layoutSurveyTempatUsaha);
//        expandCollapseBar = new CustomExpandCollapseBar(barExpandSurveyTempatUsaha, layoutSurveyTempatUsaha,false);
//        namaNarasumber3 = findViewById(R.id.namaNarasumber3);
//        namaNarasumber4 = findViewById(R.id.namaNarasumber4);
//        pertanyaanTigaDua = findViewById(R.id.pertanyaanTigaDua);
//        pertanyaanTigaTiga = findViewById(R.id.pertanyaanTigaTiga);
//        pertanyaanTigaEmpat = findViewById(R.id.pertanyaanTigaEmpat);
//        pertanyaanTigaLima = findViewById(R.id.pertanyaanTigaLima);
//        jawabanTigaDua = findViewById(R.id.jawabanTigaDua);
//        jawabanTigaTiga = findViewById(R.id.jawabanTigaTiga);
//        jawabanTigaEmpat = findViewById(R.id.jawabanTigaEmpat);
//        jawabanTigaLima = findViewById(R.id.jawabanTigaLima);
//
//        namaNarasumber3.setText(SessionManager.getNamaNarasumber3(context));
//        namaNarasumber4.setText(SessionManager.getNamaNarasumber4(context));
//        pertanyaanTigaDua.setText(SessionManager.getPertanyaanTigaDua(context));
//        pertanyaanTigaTiga.setText(SessionManager.getPertanyaanTigaTiga(context));
//        pertanyaanTigaEmpat.setText(SessionManager.getPertanyaanTigaEmpat(context));
//        pertanyaanTigaLima.setText(SessionManager.getPertanyaanTigaLima(context));
//        jawabanTigaDua.setText(SessionManager.getJawabanTigaDua(context));
//        jawabanTigaTiga.setText(SessionManager.getJawabanTigaTiga(context));
//        jawabanTigaEmpat.setText(SessionManager.getJawabanTigaEmpat(context));
//        jawabanTigaLima.setText(SessionManager.getJawabanTigaLima(context));
//
////        Button buttonEdit6 = findViewById(R.id.buttonEdit6);
////        buttonEdit6.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                saveSurveyLingkunganUsaha();
////            }
////        });
//
//        TextView barExpandFoto =findViewById(R.id.barExpandFoto);
//        ExpandableLinearLayout layoutFoto = findViewById(R.id.layoutFoto);
//        expandCollapseBar = new CustomExpandCollapseBar(barExpandFoto, layoutFoto,false);
//        imageRumah1 = findViewById(R.id.imageRumah1);
////        if (clicked =true){
////            imageRumah.setImageDrawable(null);
////        }else {
//            imageRumah1.setImageBitmap(BitmapHelper.getInstance().getBitmap());
//        imageRumah1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                imageClick();
//            }
//        });
//        imageRumah2 = findViewById(R.id.imageRumah2);
//        imageRumah2.setImageBitmap(BitmapHelper.getInstance().getBitmap2());
//        imageRumah2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                imageClick2();
//            }
//        });
//        imageJalanRumah1 = findViewById(R.id.imageJalanRumah1);
//        imageJalanRumah1.setImageBitmap(BitmapHelper.getInstance().getBitmap3());
//        imageJalanRumah1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                imageClick3();
//            }
//        });
//        imageJalanRumah2 = findViewById(R.id.imageJalanRumah2);
//        imageJalanRumah2.setImageBitmap(BitmapHelper.getInstance().getBitmap4());
//        imageJalanRumah2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                imageClick4();
//            }
//        });
//        namaAlamatRumah = findViewById(R.id.namaAlamatRumah);
//        namaAlamatRumah.setText(SessionManager.getNamaAlamatRumah(context));
//        latitude1 = findViewById(R.id.latitude1);
//        latitude1.setText(SessionManager.getLatitude1(context));
//        longitude1 = findViewById(R.id.longitude1);
//        longitude1.setText(SessionManager.getLongitude1(context));
//        Button buttonMap1 = findViewById(R.id.buttonMap1);
//        buttonMap1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, GetMapsAddressActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        TextView barExpandFotoKantor =findViewById(R.id.barExpandFotoKantor);
//        ExpandableLinearLayout layoutFotoKantor = findViewById(R.id.layoutFotoKantor);
//        expandCollapseBar = new CustomExpandCollapseBar(barExpandFotoKantor, layoutFotoKantor,false);
//        imageKantor1 = findViewById(R.id.imageUsaha1);
//        imageKantor1.setImageBitmap(BitmapHelper.getInstance().getBitmap5());
//        imageKantor1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                imageClick5();
//            }
//        });
//        imageKantor2 = findViewById(R.id.imageUsaha2);
//        imageKantor2.setImageBitmap(BitmapHelper.getInstance().getBitmap6());
//        imageKantor2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                imageClick6();
//            }
//        });
//        imageJalanKantor1 = findViewById(R.id.imageJalanUsaha1);
//        imageJalanKantor1.setImageBitmap(BitmapHelper.getInstance().getBitmap7());
//        imageJalanKantor1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                imageClick7();
//            }
//        });
//        imageJalanKantor2 = findViewById(R.id.imageJalanUsaha2);
//        imageJalanKantor2.setImageBitmap(BitmapHelper.getInstance().getBitmap8());
//        imageJalanKantor2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                imageClick8();
//            }
//        });
//        namaAlamatKantor = findViewById(R.id.namaAlamatKantor);
//        namaAlamatKantor.setText(SessionManager.getNamaAlamatKantor(context));
//        latitude2 = findViewById(R.id.latitude2);
//        latitude2.setText(SessionManager.getLatitude2(context));
//        longitude2 = findViewById(R.id.longitude2);
//        longitude2.setText(SessionManager.getLongitude2(context));
//        Button buttonMap2 = findViewById(R.id.buttonMap2);
//        buttonMap2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, GetMapsAddressActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        TextView barExpandInformasiTambahan =findViewById(R.id.barExpandInformasiTambahan);
//        ExpandableLinearLayout layoutInformasiTambahan = findViewById(R.id.layoutInformasiTambahan);
//        expandCollapseBar = new CustomExpandCollapseBar(barExpandInformasiTambahan, layoutInformasiTambahan,false);
//        informasiTambahan = findViewById(R.id.informasiTambahan);
//        informasiTambahan.setText(SessionManager.getInformasiTambahan(context));
////        Button buttonEdit7 = findViewById(R.id.buttonEdit7);
////        buttonEdit7.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                saveInformasiTambahan();
////            }
////        });
//
//        String text = SessionManager.getUsername(context);
//        buttonSumbitAll = findViewById(R.id.buttonApprove);
//        if (text.equalsIgnoreCase("bm")) {
//            buttonSumbitAll.setVisibility(View.VISIBLE);
//            buttonSumbitAll.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//    //                setKosong();
//                    sendDataApprove();
//                }
//            });
//        }
//        Button buttonReject = findViewById(R.id.buttonReject);
//        if (text.equalsIgnoreCase("bm")) {
//            buttonReject.setVisibility(View.VISIBLE);
//            buttonReject.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //                setKosong();
//                    sendDataReject();
//                }
//            });
//        }
//    }
////    public void onAuthSuccess(FirebaseUser user){
////        String username = usernameFromEmail(user.getEmail());
////        System.out.println(username);
////        Object s;
////        SessionManager.getUsername(context, String s);
////
////        writeNewAdmin(user.getUid(), username, user.getEmail());
////
////        if (username.equalsIgnoreCase("cmo")){
////            Intent intent = new Intent(context, HomeCMOActivity.class);
////            startActivity(intent);
////            finish();
////        }else if(username.equalsIgnoreCase("bm")){
////            Intent intent = new Intent(context, HomeBMActivity.class);
////            startActivity(intent);
////            finish();
////        }else if (username.equalsIgnoreCase("rm")){
////            Intent intent = new Intent(context, HomeRMActivity.class);
////            startActivity(intent);
////            finish();
////        }
////
////    }
//
//    private void imageClick(){
//        if(imageRumah1 != null){
//            AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
//            View mView = getLayoutInflater().inflate(R.layout.custom_foto_pinch, null);
//            PhotoView photoView = mView.findViewById(R.id.imageView);
//            photoView.setImageBitmap(BitmapHelper.getInstance().getBitmap());
//            mBuilder.setView(mView);
//            AlertDialog mDialog = mBuilder.create();
//            mDialog.show();
//        }else {
//
//        }
//    }
//    private void imageClick2(){
//        if (imageRumah2 !=null){
//            AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
//            View mView = getLayoutInflater().inflate(R.layout.custom_foto_pinch, null);
//            PhotoView photoView = mView.findViewById(R.id.imageView);
//            photoView.setImageBitmap(BitmapHelper.getInstance().getBitmap2());
//            mBuilder.setView(mView);
//            AlertDialog mDialog = mBuilder.create();
//            mDialog.show();
//        }
//    }
//    private void imageClick3(){
//        if(imageJalanRumah1 != null){
//            AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
//            View mView = getLayoutInflater().inflate(R.layout.custom_foto_pinch, null);
//            PhotoView photoView = mView.findViewById(R.id.imageView);
//            photoView.setImageBitmap(BitmapHelper.getInstance().getBitmap3());
//            mBuilder.setView(mView);
//            AlertDialog mDialog = mBuilder.create();
//            mDialog.show();
//        }else {
//
//        }
//    }
//    private void imageClick4(){
//        if (imageJalanRumah2 !=null){
//            AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
//            View mView = getLayoutInflater().inflate(R.layout.custom_foto_pinch, null);
//            PhotoView photoView = mView.findViewById(R.id.imageView);
//            photoView.setImageBitmap(BitmapHelper.getInstance().getBitmap4());
//            mBuilder.setView(mView);
//            AlertDialog mDialog = mBuilder.create();
//            mDialog.show();
//        }
//    }
//    private void imageClick5(){
//        if (imageKantor1 !=null){
//            AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
//            View mView = getLayoutInflater().inflate(R.layout.custom_foto_pinch, null);
//            PhotoView photoView = mView.findViewById(R.id.imageView);
//            photoView.setImageBitmap(BitmapHelper.getInstance().getBitmap5());
//            mBuilder.setView(mView);
//            AlertDialog mDialog = mBuilder.create();
//            mDialog.show();
//        }
//    }
//    private void imageClick6(){
//        if (imageKantor2 !=null){
//            AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
//            View mView = getLayoutInflater().inflate(R.layout.custom_foto_pinch, null);
//            PhotoView photoView = mView.findViewById(R.id.imageView);
//            photoView.setImageBitmap(BitmapHelper.getInstance().getBitmap6());
//            mBuilder.setView(mView);
//            AlertDialog mDialog = mBuilder.create();
//            mDialog.show();
//        }
//    }
//    private void imageClick7(){
//        if (imageJalanKantor1 !=null){
//            AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
//            View mView = getLayoutInflater().inflate(R.layout.custom_foto_pinch, null);
//            PhotoView photoView = mView.findViewById(R.id.imageView);
//            photoView.setImageBitmap(BitmapHelper.getInstance().getBitmap7());
//            mBuilder.setView(mView);
//            AlertDialog mDialog = mBuilder.create();
//            mDialog.show();
//        }
//    }
//    private void imageClick8(){
//        if (imageJalanKantor2 !=null){
//            AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
//            View mView = getLayoutInflater().inflate(R.layout.custom_foto_pinch, null);
//            PhotoView photoView = mView.findViewById(R.id.imageView);
//            photoView.setImageBitmap(BitmapHelper.getInstance().getBitmap8());
//            mBuilder.setView(mView);
//            AlertDialog mDialog = mBuilder.create();
//            mDialog.show();
//        }
//    }
//
////    private void saveContent(){
//////        String namaKonsumenValue = namaKonsumen.getText().toString();
//////        String noKTPValue = noKTP.getText().toString();
//////        String tempatLahirValue = tempatLahir.getText().toString();
//////        String tanggalLahirValue = tanggalLahir.getText().toString();
//////        String alamatRumahValue = alamatRumah.getText().toString();
//////        String kelurahanRumahValue = kelurahanRumah.getText().toString();
//////        String kecamatanRumahValue = kecamatanRumah.getText().toString();
//////        String kodePosRumahValue = kodePosRumah.getText().toString();
//////        String noTelpValue = noTelp.getText().toString();
//////        String namaPasanganValue = namaPasangan.getText().toString();
//////        String jmlTanggunganValue = jmlTanggungan.getText().toString();
//////        String namaIbuKandungValue = namaIbuKandung.getText().toString();
//////        String namaTempatUsahaValue =namaTempatUsaha.getText().toString();
//////        String alamatUsahaValue = alamatUsaha.getText().toString();
//////        String kelurahanUsahaValue = kelurahanUsaha.getText().toString();
//////        String kecamatanUsahaValue = kecamatanUsaha.getText().toString();
//////        String kodePosUsahaValue = kodePosUsaha.getText().toString();
//////        String merkMobilValue = merkMobil.getText().toString();
//////        String warnaMobilValue = warnaMobil.getText().toString();
//////        String dealerShowroomValue = dealerShowroom.getText().toString();
//////        String tanggalSurveyValue = tanggalSurvey.getText().toString();
//////        String jamSurveyValue = jamSurvey.getText().toString();
//////
//////        //save data login
//////        SessionManager.saveDataDiri(context, namaKonsumenValue, noKTPValue, tempatLahirValue, tanggalLahirValue, alamatRumahValue, kelurahanRumahValue, kecamatanRumahValue,
//////                kodePosRumahValue, noTelpValue, namaPasanganValue, jmlTanggunganValue, namaIbuKandungValue, namaTempatUsahaValue, alamatUsahaValue, kelurahanUsahaValue, kecamatanUsahaValue, kodePosUsahaValue,
//////                merkMobilValue, warnaMobilValue, dealerShowroomValue, tanggalSurveyValue, jamSurveyValue);
////        Intent intent = new Intent(context, FormIdentitasKonsumenEdit.class);
////        startActivity(intent);
////        finish();
////    }
////
////    private void saveSurveyKonsumen(){
//////        String pertanyaanSatuValue = pertanyaanSatu.getText().toString();
//////        String pertanyaanDuaValue = pertanyaanDua.getText().toString();
//////        String platNomorValue = platNomor.getText().toString();
//////        String merkTipeValue = merkTipe.getText().toString();
//////        String warnaValue = warna.getText().toString();
//////        String tahunKendaraanValue = tahunKendaraan.getText().toString();
//////        String jenisKreditValue = jenisKredit.getText().toString();
//////        String totalAngsuranValue = totalAngsuran.getText().toString();
//////        String totalPenghasilanValue = totalPenghasilan.getText().toString();
//////        String pertanyaanLimaValue = pertanyaanLima.getText().toString();
//////
//////        SessionManager.saveSurveyKonsumen(context, pertanyaanSatuValue, pertanyaanDuaValue, platNomorValue, merkTipeValue, warnaValue,
//////                tahunKendaraanValue, jenisKreditValue, totalAngsuranValue, totalPenghasilanValue, pertanyaanLimaValue);
//////        Intent intent = new Intent(context, FormSurveyRumahKonsumen.class);
////        Intent intent = new Intent(context, FormSurveyKonsumenEdit.class);
////        startActivity(intent);
////        finish();
////    }
////
////    private void saveSurveyRumahKonsumen(){
////        String pertanyaanEnamValue = pertanyaanEnam.getText().toString();
////        String pertanyaanTujuhValue = pertanyaanTujuh.getText().toString();
////        String pertanyaanDelapanValue = pertanyaanDelapan.getText().toString();
////        String pertanyaanSembilanValue = pertanyaanSembilan.getText().toString();
////        String pertanyaanSepuluhValue = pertanyaanSepuluh.getText().toString();
////        String pertanyaanSebelasValue = pertanyaanSebelas.getText().toString();
////        String pertanyaanDuaBelasValue = pertanyaanDuaBelas.getText().toString();
////        String pertanyaanTigaBelasValue = pertanyaanTigaBelas.getText().toString();
////
////        SessionManager.saveSurveyRumahKonsumen(context, pertanyaanEnamValue, pertanyaanTujuhValue, pertanyaanDelapanValue, pertanyaanSembilanValue, pertanyaanSepuluhValue, pertanyaanSebelasValue, pertanyaanDuaBelasValue, pertanyaanTigaBelasValue);
////        Intent intent = new Intent(context, FormSurveyRumahKonsumenEdit.class);
////        startActivity(intent);
////        finish();
////    }
////
////    private void saveVerifikasiLingkunganRumah(){
////        String pertanyaanEmpatBelasValue = pertanyaanEmpatBelas.getText().toString();
////        String pertanyaanLimaBelasValue = pertanyaanLimaBelas.getText().toString();
////        String pertanyaanEnamBelasValue = pertanyaanEnamBelas.getText().toString();
////        String pertanyaanTujuhBelasValue = pertanyaanTujuhBelas.getText().toString();
////        String pertanyaanDelapanBelasValue = pertanyaanDelapanBelas.getText().toString();
////        String pertanyaanSembilanBelasValue = pertanyaanSembilanBelas.getText().toString();
////        String pertanyaanDuaPuluhValue = pertanyaanDuaPuluh.getText().toString();
////        String pertanyaanDuaSatuValue = pertanyaanDuaSatu.getText().toString();
////        String pertanyaanDuaDuaValue = pertanyaanDuaDua.getText().toString();
////        String pertanyaanDuaTigaValue = pertanyaanDuaTiga.getText().toString();
////        String pertanyaanDuaEmpatValue = pertanyaanDuaEmpat.getText().toString();
////        String pertanyaanDuaLimaValue = pertanyaanDuaLima.getText().toString();
////        String pertanyaanDuaEnamValue = pertanyaanDuaEnam.getText().toString();
////        String pertanyaanDuaTujuhValue = pertanyaanDuaTujuh.getText().toString();
////        String jawabanEnamBelasValue = jawabanEnamBelas.getText().toString();
////        String jawabanTujuhBelasValue = jawabanTujuhBelas.getText().toString();
////        String jawabanDelapanBelasTipeValue = jawabanDelapanBelasTipe.getText().toString();
////        String jawabanDelapanBelasWarnaValue = jawabanDelapanBelasWarna.getText().toString();
////        String jawabanDuaPuluhValue = jawabanDuaPuluh.getText().toString();
////        String jawabanDuaTigaValue = jawabanDuaTiga.getText().toString();
////        String jawabanDuaEmpatValue = jawabanDuaEmpat.getText().toString();
////        String jawabanDuaLimaTipeValue = jawabanDuaLimaTipe.getText().toString();
////        String jawabanDuaLimaWarnaValue = jawabanDuaLimaWarna.getText().toString();
////        String jawabanDuaTujuhValue = jawabanDuaTujuh.getText().toString();
////        String namaNarasumber1Value = namaNarasumber1.getText().toString();
////        String namaNarasumber2Value = namaNarasumber2.getText().toString();
////
////        SessionManager.saveVerifikasiLingkunganRumah(context, pertanyaanEmpatBelasValue, pertanyaanLimaBelasValue, pertanyaanEnamBelasValue, pertanyaanTujuhBelasValue, pertanyaanDelapanBelasValue,
////                pertanyaanSembilanBelasValue, pertanyaanDuaPuluhValue, pertanyaanDuaSatuValue, pertanyaanDuaDuaValue, pertanyaanDuaTigaValue, pertanyaanDuaEmpatValue, pertanyaanDuaLimaValue,
////                pertanyaanDuaEnamValue,pertanyaanDuaTujuhValue, jawabanEnamBelasValue, jawabanTujuhBelasValue, jawabanDelapanBelasTipeValue, jawabanDelapanBelasWarnaValue, jawabanDuaPuluhValue,
////                jawabanDuaTigaValue, jawabanDuaEmpatValue, jawabanDuaLimaTipeValue, jawabanDuaLimaWarnaValue, jawabanDuaTujuhValue, namaNarasumber1Value, namaNarasumber2Value);
////        Intent intent = new Intent(context, FormVerifikasiLingkunganRumahEdit.class);
////        startActivity(intent);
////        finish();
////    }
////
////    private void savePengamatanSurveyor(){
////        String pertanyaanDuaDelapanValue = pertanyaanDuaDelapan.getText().toString();
////        String pertanyaanDuaSembilanValue = pertanyaanDuaSembilan.getText().toString();
////        String pertanyaanTigaPuluhValue = pertanyaanTigaPuluh.getText().toString();
////        String pertanyaanTigaSatuValue = pertanyaanTigaSatu.getText().toString();
////        String jawabanDuaDelapanValue = jawabanDuaDelapan.getText().toString();
////        String jawabanDuaSembilanValue = jawabanDuaSembilan.getText().toString();
////
////        SessionManager.savePengamatanSurveyor(context, pertanyaanDuaDelapanValue, pertanyaanDuaSembilanValue, pertanyaanTigaPuluhValue, pertanyaanTigaSatuValue, jawabanDuaDelapanValue, jawabanDuaSembilanValue);
////        Intent intent = new Intent(context, FormPengamatanSurveyorEdit.class);
////        startActivity(intent);
////        finish();
////    }
////
////    private void saveSurveyLingkunganUsaha(){
////        String pertanyaanTigaDuaValue = pertanyaanTigaDua.getText().toString();
////        String pertanyaanTigaTigaValue = pertanyaanTigaTiga.getText().toString();
////        String pertanyaanTigaEmpatValue = pertanyaanTigaEmpat.getText().toString();
////        String pertanyaanTigalimaValue = pertanyaanTigaLima.getText().toString();
////        String jawabanTigaDuaValue = jawabanTigaDua.getText().toString();
////        String jawabanTigaTigaValue = jawabanTigaTiga.getText().toString();
////        String jawabanTigaEmpatValue = jawabanTigaEmpat.getText().toString();
////        String jawabanTigaLimaValue = jawabanTigaLima.getText().toString();
////        String namaNarasumber3Value = namaNarasumber3.getText().toString();
////        String namaNarasumber4Value = namaNarasumber4.getText().toString();
////
////        SessionManager.saveSurveyLingkunganUsaha(context, pertanyaanTigaDuaValue, pertanyaanTigaTigaValue, pertanyaanTigaEmpatValue, pertanyaanTigalimaValue, jawabanTigaDuaValue,
////                jawabanTigaTigaValue, jawabanTigaEmpatValue, jawabanTigaLimaValue, namaNarasumber3Value, namaNarasumber4Value);
////        Intent intent = new Intent(context, FormSurveyLingkunganUsahaEdit.class);
////        startActivity(intent);
////        finish();
////    }
////
////    private void saveInformasiTambahan(){
////        String informasiTambahanValue = informasiTambahan.getText().toString();
////
////        SessionManager.saveInformasiTambahan(context, informasiTambahanValue);
////        Intent intent = new Intent(context, FormInformasiTambahanEdit.class);
////        startActivity(intent);
////        finish();
////    }
////
////    private void setKosong(){
////        clicked = true;
//////        imageRumah.setImageBitmap(BitmapHelper.getInstance().getBitmapNull());
//////        imageJalanRumah.setImageDrawable(null);
//////        imageKantor.setImageDrawable(null);
//////        imageJalanKantor.setImageDrawable(null);
////
////        Intent intent = new Intent(context, HomeCMOActivity.class);
////        startActivity(intent);
////        finish();
////    }
//
//    private void updateStatus(inputSurvey inputSurvey) {
//        String getKey =  getIntent().getExtras().getString("getPrimaryKey");
//
//        getReference.child(getKey)
//                .setValue(inputSurvey)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Toast.makeText(context, "Data Berhasil disimpan", Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//    }
//
//    FirebaseDatabase database = FirebaseDatabase.getInstance();
//    DatabaseReference getReference;
//    inputSurvey inputSurvey;
//
//    private void saveDataApprove(){
//            inputSurvey = new inputSurvey();
//            getReference = FirebaseDatabase.getInstance().getReference().child("inputSurvey");
//
//        String statusValue = "Approve";
//        String namaKonsumenValue = namaKonsumen.getText().toString();
//        String noKTPValue = noKTP.getText().toString();
//        String tempatLahirValue = tempatLahir.getText().toString();
//        String tanggalLahirValue = tanggalLahir.getText().toString();
//        String noTelpValue = noTelp.getText().toString();
//        String merkMobilValue = merkMobil.getText().toString();
//        String warnaMobilValue = warnaMobil.getText().toString();
//        String dealerShowroomValue = dealerShowroom.getText().toString();
//        String tanggalSurveyValue = tanggalSurvey.getText().toString();
//        String jamSurveyValue = jamSurvey.getText().toString();
//        String informasiTambahanValue = informasiTambahan.getText().toString();
//        String alamatRumahValue = getIntent().getStringExtra(Constanta.ALAMAT_RUMAH);
//        String kelurahanRumahValue = getIntent().getStringExtra(Constanta.KELURAHAN_RUMAH);
//        String kecamatanRumahValue = getIntent().getStringExtra(Constanta.KECAMATAN_RUMAH);
//        String kodePosRumahValue = getIntent().getStringExtra(Constanta.KODE_POS_RUMAH);
//        String pertanyaanEnamValue = SessionManager.getPertanyaanEnam(context);
//        String pertanyaanTujuhValue = SessionManager.getPertanyaanTujuh(context);
//        String pertanyaanDelapanValue = SessionManager.getPertanyaanDelapan(context);
//        String pertanyaanSembilanValue = SessionManager.getPertanyaanSembilan(context);
//        String pertanyaanSepuluhValue = SessionManager.getPertanyaanSepuluh(context);
//        String pertanyaanSebelasValue = SessionManager.getPertanyaanSebelas(context);
//        String pertanyaanDuaBelasValue = SessionManager.getPertanyaanDuaBelas(context);
//        String pertanyaanTigaBelasValue = SessionManager.getPertanyaanTigaBelas(context);
//        String pertanyaanEmpatBelasValue = SessionManager.getPertanyaanEmpatBelas(context);
//        String pertanyaanLimaBelasValue = SessionManager.getPertanyaanLimaBelas(context);
//        String pertanyaanEnamBelasValue = SessionManager.getPertanyaanEnamBelas(context);
//        String pertanyaanTujuhBelasValue =SessionManager.getPertanyaanTujuhBelas(context);
//        String pertanyaanDelapanBelasValue =SessionManager.getPertanyaanDelapanBelas(context);
//        String pertanyaanSembilanBelasValue =SessionManager.getPertanyaanSembilanBelas(context);
//        String pertanyaanDuaPuluhValue = SessionManager.getPertanyaanDuaPuluh(context);
//        String pertanyaanDuaSatuValue = SessionManager.getPertanyaanDuaSatu(context);
//        String pertanyaanDuaDuaValue = SessionManager.getPertanyaanDua(context);
//        String pertanyaanDuaTigaValue = SessionManager.getPertanyaanDuaTiga(context);
//        String pertanyaanDuaEmpatValue = SessionManager.getPertanyaanDuaEmpat(context);
//        String pertanyaanDuaLimaValue = SessionManager.getPertanyaanDuaLima(context);
//        String pertanyaanDuaEnamValue = SessionManager.getPertanyaanDuaEnam(context);
//        String pertanyaanDuaTujuhValue = SessionManager.getPertanyaanDuaTujuh(context);
//        String jawabanEnamBelasValue = SessionManager.getJawabanEnamBelas(context);
//        String jawabanTujuhBelasValue = SessionManager.getJawabanTujuhBelas(context);
//        String jawabanDelapanBelasTipeValue = SessionManager.getJawabanDelapanBelasTipe(context);
//        String jawabanDelapanBelasWarnaValue = SessionManager.getJawabanDelapanBelasWarna(context);
//        String jawabanDuaPuluhValue = SessionManager.getJawabanDuaPuluh(context);
//        String jawabanDuaTigaValue = SessionManager.getJawabanDuaTiga(context);
//        String jawabanDuaEmpatValue = SessionManager.getJawabanDuaEmpat(context);
//        String jawabanDuaLimaTipeValue = SessionManager.getJawabanDuaLimaTipe(context);
//        String jawabanDuaLimaWarnaValue = SessionManager.getJawabanDuaLimaWarna(context);
//        String jawabanDuaTujuhValue = SessionManager.getJawabanDuaTujuh(context);
//        String namaNarasumber1Value = SessionManager.getNamaNarasumber1(context);
//        String namaNarasumber2Value = SessionManager.getNamaNarasumber2(context);
//        String namaAlamatRumahValue = getIntent().getStringExtra(Constanta.NAMAALAMATRUMAH);
//        String latitude1Value = getIntent().getStringExtra(Constanta.LATITUDE1);
//        String longitude1Value = getIntent().getStringExtra(Constanta.LONGITUDE1);
//
//            updateStatus(inputSurvey);
//
//
//        }
//
//    private void sendDataApprove(){
//        final ProgressDialog loading = LoadingClass.loadingAnimationCustom(context);
//        loading.show();
//
//        String getKey =  getIntent().getExtras().getString("getPrimaryKey");
//
//        String statusValue = "Approve";
//        String namaKonsumenValue = namaKonsumen.getText().toString();
//        String noKTPValue = noKTP.getText().toString();
//        String tempatLahirValue = tempatLahir.getText().toString();
//        String tanggalLahirValue = tanggalLahir.getText().toString();
//        String noTelpValue = noTelp.getText().toString();
//        String merkMobilValue = merkMobil.getText().toString();
//        String warnaMobilValue = warnaMobil.getText().toString();
//        String dealerShowroomValue = dealerShowroom.getText().toString();
//        String tanggalSurveyValue = tanggalSurvey.getText().toString();
//        String jamSurveyValue = jamSurvey.getText().toString();
//        String informasiTambahanValue = informasiTambahan.getText().toString();
//        String alamatRumahValue = getIntent().getStringExtra(Constanta.ALAMAT_RUMAH);
//        String kelurahanRumahValue = getIntent().getStringExtra(Constanta.KELURAHAN_RUMAH);
//        String kecamatanRumahValue = getIntent().getStringExtra(Constanta.KECAMATAN_RUMAH);
//        String kodePosRumahValue = getIntent().getStringExtra(Constanta.KODE_POS_RUMAH);
//        String pertanyaanEnamValue = SessionManager.getPertanyaanEnam(context);
//        String pertanyaanTujuhValue = SessionManager.getPertanyaanTujuh(context);
//        String pertanyaanDelapanValue = SessionManager.getPertanyaanDelapan(context);
//        String pertanyaanSembilanValue = SessionManager.getPertanyaanSembilan(context);
//        String pertanyaanSepuluhValue = SessionManager.getPertanyaanSepuluh(context);
//        String pertanyaanSebelasValue = SessionManager.getPertanyaanSebelas(context);
//        String pertanyaanDuaBelasValue = SessionManager.getPertanyaanDuaBelas(context);
//        String pertanyaanTigaBelasValue = SessionManager.getPertanyaanTigaBelas(context);
//        String pertanyaanEmpatBelasValue = SessionManager.getPertanyaanEmpatBelas(context);
//        String pertanyaanLimaBelasValue = SessionManager.getPertanyaanLimaBelas(context);
//        String pertanyaanEnamBelasValue = SessionManager.getPertanyaanEnamBelas(context);
//        String pertanyaanTujuhBelasValue =SessionManager.getPertanyaanTujuhBelas(context);
//        String pertanyaanDelapanBelasValue =SessionManager.getPertanyaanDelapanBelas(context);
//        String pertanyaanSembilanBelasValue =SessionManager.getPertanyaanSembilanBelas(context);
//        String pertanyaanDuaPuluhValue = SessionManager.getPertanyaanDuaPuluh(context);
//        String pertanyaanDuaSatuValue = SessionManager.getPertanyaanDuaSatu(context);
//        String pertanyaanDuaDuaValue = SessionManager.getPertanyaanDua(context);
//        String pertanyaanDuaTigaValue = SessionManager.getPertanyaanDuaTiga(context);
//        String pertanyaanDuaEmpatValue = SessionManager.getPertanyaanDuaEmpat(context);
//        String pertanyaanDuaLimaValue = SessionManager.getPertanyaanDuaLima(context);
//        String pertanyaanDuaEnamValue = SessionManager.getPertanyaanDuaEnam(context);
//        String pertanyaanDuaTujuhValue = SessionManager.getPertanyaanDuaTujuh(context);
//        String jawabanEnamBelasValue = SessionManager.getJawabanEnamBelas(context);
//        String jawabanTujuhBelasValue = SessionManager.getJawabanTujuhBelas(context);
//        String jawabanDelapanBelasTipeValue = SessionManager.getJawabanDelapanBelasTipe(context);
//        String jawabanDelapanBelasWarnaValue = SessionManager.getJawabanDelapanBelasWarna(context);
//        String jawabanDuaPuluhValue = SessionManager.getJawabanDuaPuluh(context);
//        String jawabanDuaTigaValue = SessionManager.getJawabanDuaTiga(context);
//        String jawabanDuaEmpatValue = SessionManager.getJawabanDuaEmpat(context);
//        String jawabanDuaLimaTipeValue = SessionManager.getJawabanDuaLimaTipe(context);
//        String jawabanDuaLimaWarnaValue = SessionManager.getJawabanDuaLimaWarna(context);
//        String jawabanDuaTujuhValue = SessionManager.getJawabanDuaTujuh(context);
//        String namaNarasumber1Value = SessionManager.getNamaNarasumber1(context);
//        String namaNarasumber2Value = SessionManager.getNamaNarasumber2(context);
//        String namaAlamatRumahValue = getIntent().getStringExtra(Constanta.NAMAALAMATRUMAH);
//        String latitude1Value = getIntent().getStringExtra(Constanta.LATITUDE1);
//        String longitude1Value = getIntent().getStringExtra(Constanta.LONGITUDE1);
//        getReference = database.getReference();
////        getReference.child("inputSurvey").child(getKey)
////                .setValue(new inputSurvey(statusValue, namaKonsumenValue, noKTPValue, tempatLahirValue, tanggalLahirValue, noTelpValue,
////                        merkMobilValue, warnaMobilValue, dealerShowroomValue, tanggalSurveyValue, jamSurveyValue, informasiTambahanValue, alamatRumahValue, kelurahanRumahValue,
////                        kecamatanRumahValue, kodePosRumahValue, pertanyaanEnamValue, pertanyaanTujuhValue, pertanyaanDelapanValue, pertanyaanSembilanValue, pertanyaanSepuluhValue, pertanyaanSebelasValue,
////                        pertanyaanDuaBelasValue, pertanyaanTigaBelasValue, pertanyaanEmpatBelasValue, pertanyaanLimaBelasValue, pertanyaanEnamBelasValue, pertanyaanTujuhBelasValue, pertanyaanDelapanBelasValue, pertanyaanSembilanBelasValue, pertanyaanDuaPuluhValue,
////                        pertanyaanDuaSatuValue, pertanyaanDuaDuaValue, pertanyaanDuaTigaValue, pertanyaanDuaEmpatValue, pertanyaanDuaLimaValue, pertanyaanDuaEnamValue, pertanyaanDuaTujuhValue,
////                        jawabanEnamBelasValue, jawabanTujuhBelasValue, jawabanDelapanBelasTipeValue, jawabanDelapanBelasWarnaValue, jawabanDuaPuluhValue, jawabanDuaTigaValue, jawabanDuaEmpatValue, jawabanDuaLimaTipeValue, jawabanDuaLimaWarnaValue, jawabanDuaTujuhValue, namaNarasumber1Value, namaNarasumber2Value,
////                        namaAlamatRumahValue, latitude1Value, longitude1Value)).addOnSuccessListener(this, new OnSuccessListener<Void>() {
////            @Override
////            public void onSuccess(Void aVoid) {
////                Toast.makeText(context, "Data Tersimpan", Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent(context, HomeBMActivity.class);
//                startActivity(intent);
//                finish();
//            }
////        });
//
////        String username = auth.getCurrentUser().getUid();
////        final ProgressDialog loading = LoadingClass.loadingAnimationCustom(context);
////        loading.show();
////
////        FirebaseDatabase database = FirebaseDatabase.getInstance();
////        DatabaseReference getReference;
////
////        String namaKonsumenValue = namaKonsumen.getText().toString();
////        String noKTPValue = noKTP.getText().toString();
////        String tempatLahirValue = tempatLahir.getText().toString();
////        String tanggalLahirValue = tanggalLahir.getText().toString();
////        String alamatRumahValue = alamatRumah.getText().toString();
////        String kelurahanRumahValue = kelurahanRumah.getText().toString();
////        String kecamatanRumahValue = kecamatanRumah.getText().toString();
////        String kodePosRumahValue = kodePosRumah.getText().toString();
////        String noTelpValue = noTelp.getText().toString();
////        String namaPasanganValue = namaPasangan.getText().toString();
////        String jmlTanggunganValue = jmlTanggungan.getText().toString();
////        String namaIbuKandungValue = namaIbuKandung.getText().toString();
////        String namaTempatUsahaValue =namaTempatUsaha.getText().toString();
////        String alamatUsahaValue = alamatUsaha.getText().toString();
////        String kelurahanUsahaValue = kelurahanUsaha.getText().toString();
////        String kecamatanUsahaValue = kecamatanUsaha.getText().toString();
////        String kodePosUsahaValue = kodePosUsaha.getText().toString();
////        String merkMobilValue = merkMobil.getText().toString();
////        String warnaMobilValue = warnaMobil.getText().toString();
////        String dealerShowroomValue = dealerShowroom.getText().toString();
////        String tanggalSurveyValue = tanggalSurvey.getText().toString();
////        String jamSurveyValue = jamSurvey.getText().toString();
////
////        getReference = database.getReference();
////
////        if (isEmpty(namaKonsumenValue)&& isEmpty(noKTPValue)){
////            Toast.makeText(context, "Nama & No KTP Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
////
////        }else {
////            getReference.child("inputSurvey").push()
////                    .setValue(new data_survey(namaKonsumenValue, noKTPValue, tempatLahirValue, tanggalLahirValue, alamatRumahValue, kelurahanRumahValue, kecamatanRumahValue,
////                            kodePosRumahValue, noTelpValue, namaPasanganValue, jmlTanggunganValue, namaIbuKandungValue, namaTempatUsahaValue, alamatUsahaValue, kelurahanUsahaValue, kecamatanUsahaValue,
////                            kodePosUsahaValue, merkMobilValue, warnaMobilValue, dealerShowroomValue, tanggalSurveyValue, jamSurveyValue)).addOnSuccessListener(this, new OnSuccessListener<Void>() {
////                @Override
////                public void onSuccess(Void aVoid) {
////                    Toast.makeText(context, "Data Tersimpan", Toast.LENGTH_SHORT).show();
////
////                    Intent intent = new Intent(context, HomeCMOActivity.class);
////                    startActivity(intent);
////                    finish();
////                }
////            });
////        }
////    }
//    private void sendDataReject() {
//        final ProgressDialog loading = LoadingClass.loadingAnimationCustom(context);
//        loading.show();
//
//        String getKey =  getIntent().getExtras().getString("getPrimaryKey");
//
//        String statusValue = "Reject";
//        String namaKonsumenValue = namaKonsumen.getText().toString();
//        String noKTPValue = noKTP.getText().toString();
//        String tempatLahirValue = tempatLahir.getText().toString();
//        String tanggalLahirValue = tanggalLahir.getText().toString();
//        String noTelpValue = noTelp.getText().toString();
//        String merkMobilValue = merkMobil.getText().toString();
//        String warnaMobilValue = warnaMobil.getText().toString();
//        String dealerShowroomValue = dealerShowroom.getText().toString();
//        String tanggalSurveyValue = tanggalSurvey.getText().toString();
//        String jamSurveyValue = jamSurvey.getText().toString();
//        String informasiTambahanValue = informasiTambahan.getText().toString();
//        String alamatRumahValue = getIntent().getStringExtra(Constanta.ALAMAT_RUMAH);
//        String kelurahanRumahValue = getIntent().getStringExtra(Constanta.KELURAHAN_RUMAH);
//        String kecamatanRumahValue = getIntent().getStringExtra(Constanta.KECAMATAN_RUMAH);
//        String kodePosRumahValue = getIntent().getStringExtra(Constanta.KODE_POS_RUMAH);
//        String pertanyaanEnamValue = SessionManager.getPertanyaanEnam(context);
//        String pertanyaanTujuhValue = SessionManager.getPertanyaanTujuh(context);
//        String pertanyaanDelapanValue = SessionManager.getPertanyaanDelapan(context);
//        String pertanyaanSembilanValue = SessionManager.getPertanyaanSembilan(context);
//        String pertanyaanSepuluhValue = SessionManager.getPertanyaanSepuluh(context);
//        String pertanyaanSebelasValue = SessionManager.getPertanyaanSebelas(context);
//        String pertanyaanDuaBelasValue = SessionManager.getPertanyaanDuaBelas(context);
//        String pertanyaanTigaBelasValue = SessionManager.getPertanyaanTigaBelas(context);
//        String pertanyaanEmpatBelasValue = SessionManager.getPertanyaanEmpatBelas(context);
//        String pertanyaanLimaBelasValue = SessionManager.getPertanyaanLimaBelas(context);
//        String pertanyaanEnamBelasValue = SessionManager.getPertanyaanEnamBelas(context);
//        String pertanyaanTujuhBelasValue = SessionManager.getPertanyaanTujuhBelas(context);
//        String pertanyaanDelapanBelasValue = SessionManager.getPertanyaanDelapanBelas(context);
//        String pertanyaanSembilanBelasValue = SessionManager.getPertanyaanSembilanBelas(context);
//        String pertanyaanDuaPuluhValue = SessionManager.getPertanyaanDuaPuluh(context);
//        String pertanyaanDuaSatuValue = SessionManager.getPertanyaanDuaSatu(context);
//        String pertanyaanDuaDuaValue = SessionManager.getPertanyaanDua(context);
//        String pertanyaanDuaTigaValue = SessionManager.getPertanyaanDuaTiga(context);
//        String pertanyaanDuaEmpatValue = SessionManager.getPertanyaanDuaEmpat(context);
//        String pertanyaanDuaLimaValue = SessionManager.getPertanyaanDuaLima(context);
//        String pertanyaanDuaEnamValue = SessionManager.getPertanyaanDuaEnam(context);
//        String pertanyaanDuaTujuhValue = SessionManager.getPertanyaanDuaTujuh(context);
//        String jawabanEnamBelasValue = SessionManager.getJawabanEnamBelas(context);
//        String jawabanTujuhBelasValue = SessionManager.getJawabanTujuhBelas(context);
//        String jawabanDelapanBelasTipeValue = SessionManager.getJawabanDelapanBelasTipe(context);
//        String jawabanDelapanBelasWarnaValue = SessionManager.getJawabanDelapanBelasWarna(context);
//        String jawabanDuaPuluhValue = SessionManager.getJawabanDuaPuluh(context);
//        String jawabanDuaTigaValue = SessionManager.getJawabanDuaTiga(context);
//        String jawabanDuaEmpatValue = SessionManager.getJawabanDuaEmpat(context);
//        String jawabanDuaLimaTipeValue = SessionManager.getJawabanDuaLimaTipe(context);
//        String jawabanDuaLimaWarnaValue = SessionManager.getJawabanDuaLimaWarna(context);
//        String jawabanDuaTujuhValue = SessionManager.getJawabanDuaTujuh(context);
//        String namaNarasumber1Value = SessionManager.getNamaNarasumber1(context);
//        String namaNarasumber2Value = SessionManager.getNamaNarasumber2(context);
//        String namaAlamatRumahValue = getIntent().getStringExtra(Constanta.NAMAALAMATRUMAH);
//        String latitude1Value = getIntent().getStringExtra(Constanta.LATITUDE1);
//        String longitude1Value = getIntent().getStringExtra(Constanta.LONGITUDE1);
//        getReference = database.getReference();
////        getReference.child("inputSurvey").child(getKey)
////                .setValue(new inputSurvey(statusValue, namaKonsumenValue, noKTPValue, tempatLahirValue, tanggalLahirValue, noTelpValue,
////                        merkMobilValue, warnaMobilValue, dealerShowroomValue, tanggalSurveyValue, jamSurveyValue, informasiTambahanValue, alamatRumahValue, kelurahanRumahValue,
////                        kecamatanRumahValue, kodePosRumahValue, pertanyaanEnamValue, pertanyaanTujuhValue, pertanyaanDelapanValue, pertanyaanSembilanValue, pertanyaanSepuluhValue, pertanyaanSebelasValue,
////                        pertanyaanDuaBelasValue, pertanyaanTigaBelasValue, pertanyaanEmpatBelasValue, pertanyaanLimaBelasValue, pertanyaanEnamBelasValue, pertanyaanTujuhBelasValue, pertanyaanDelapanBelasValue, pertanyaanSembilanBelasValue, pertanyaanDuaPuluhValue,
////                        pertanyaanDuaSatuValue, pertanyaanDuaDuaValue, pertanyaanDuaTigaValue, pertanyaanDuaEmpatValue, pertanyaanDuaLimaValue, pertanyaanDuaEnamValue, pertanyaanDuaTujuhValue,
////                        jawabanEnamBelasValue, jawabanTujuhBelasValue, jawabanDelapanBelasTipeValue, jawabanDelapanBelasWarnaValue, jawabanDuaPuluhValue, jawabanDuaTigaValue, jawabanDuaEmpatValue, jawabanDuaLimaTipeValue, jawabanDuaLimaWarnaValue, jawabanDuaTujuhValue, namaNarasumber1Value, namaNarasumber2Value,
////                        namaAlamatRumahValue, latitude1Value, longitude1Value)).addOnSuccessListener(this, new OnSuccessListener<Void>() {
////            @Override
////            public void onSuccess(Void aVoid) {
////                Toast.makeText(context, "Data Tersimpan", Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent(context, HomeBMActivity.class);
//                startActivity(intent);
//                finish();
//            }
////        });
////    }
//}
