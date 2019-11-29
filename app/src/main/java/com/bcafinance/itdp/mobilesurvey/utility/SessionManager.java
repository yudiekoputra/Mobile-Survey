package com.bcafinance.itdp.mobilesurvey.utility;

import android.content.Context;
import android.content.SharedPreferences;

import java.security.PublicKey;

public class SessionManager {
    //deklarasi shared preferences
    protected static SharedPreferences retrieveSharedPreferences(Context context){
        return context.getSharedPreferences(Constanta.SHARED_PREFERENCES_NAME, context.MODE_PRIVATE);
    }

    //mode editor(CRUD)
    protected static SharedPreferences.Editor retrieveSharedPreferencesEditor(Context context){
        return retrieveSharedPreferences(context).edit();
    }

    /*getter & setter
    definisikan fungsi getter & setter sesuai keperluan aplikasi
     */
    //save data login
    public static void saveDataLogin(Context context, String NIP, String password, boolean remember){
        SharedPreferences.Editor editor = retrieveSharedPreferencesEditor(context);
        editor.putString(Constanta.KEY_NIP, NIP);
        editor.putString(Constanta.KEY_PASSWORD, password);
        editor.putBoolean(Constanta.KEY_REMEMBER, remember);

        editor.commit();
    }
    public static void saveKodeKonsumen(Context context, String kodeKonsumen){
        SharedPreferences.Editor editor = retrieveSharedPreferencesEditor(context);
        editor.putString(Constanta.KODEKONSUMEN, kodeKonsumen);
        editor.commit();
    }
    public static void saveResponLogin(Context context, String token, String user, String position){
        SharedPreferences.Editor editor = retrieveSharedPreferencesEditor(context);
        editor.putString(Constanta.TOKEN, token);
        editor.putString(Constanta.USER, user);
        editor.putString(Constanta.POSITION, position);
        editor.commit();
    }

//    public static void saveDataDiri(Context context, String namaKonsumen, String noKTP, String tempatLahir, String tanggalLahir, String alamatRumah, String kelurahanRumah, String kecamatanRumah,
//                                    String kodePosRumah, String noTelp, String namaPasangan, String jmlTanggungan, String namaIbuKandung, String namaTempatUsaha,
//                                    String alamatUsaha, String kelurahanUsaha, String kecamatanUsaha, String kodePosUsaha, String merkMobil, String warnaMobil, String dealerShowroom,
//                                    String tanggalSurvey, String jamSurvey){
//        SharedPreferences.Editor editor = retrieveSharedPreferencesEditor(context);
//        editor.putString(Constanta.NAMA_KONSUMEN, namaKonsumen);
//        editor.putString(Constanta.NO_KTP, noKTP);
//        editor.putString(Constanta.TEMPAT_LAHIR, tempatLahir);
//        editor.putString(Constanta.TANGGAL_LAHIR, tanggalLahir);
//        editor.putString(Constanta.ALAMAT_RUMAH, alamatRumah);
//        editor.putString(Constanta.KELURAHAN_RUMAH, kelurahanRumah);
//        editor.putString(Constanta.KECAMATAN_RUMAH, kecamatanRumah);
//        editor.putString(Constanta.KODE_POS_RUMAH, kodePosRumah);
//        editor.putString(Constanta.NO_TELP, noTelp);
//        editor.putString(Constanta.NAMA_PASANGAN, namaPasangan);
//        editor.putString(Constanta.JML_TANGGUNGAN, jmlTanggungan);
//        editor.putString(Constanta.NAMA_IBU_KANDUNG, namaIbuKandung);
//        editor.putString(Constanta.NAMA_TEMPAT_USAHA, namaTempatUsaha);
//        editor.putString(Constanta.ALAMAT_USAHA, alamatUsaha);
//        editor.putString(Constanta.KELURAHAN_USAHA, kelurahanUsaha);
//        editor.putString(Constanta.KECAMATAN_USAHA, kecamatanUsaha);
//        editor.putString(Constanta.KODE_POS_USAHA, kodePosUsaha);
//        editor.putString(Constanta.MERK_MOBIL, merkMobil);
//        editor.putString(Constanta.WARNA_MOBIL, warnaMobil);
//        editor.putString(Constanta.DEALER_SHOWROOM, dealerShowroom);
//        editor.putString(Constanta.TANGGAL_SURVEY, tanggalSurvey);
//        editor.putString(Constanta.JAM_SURVEY, jamSurvey);
//        editor.commit();
//    }

    public static void saveUsahaKonsumen(Context context, String namaAlamatKantor, String latitude2, String longitude2){
        SharedPreferences.Editor editor = retrieveSharedPreferencesEditor(context);
        editor.putString(Constanta.NAMAALAMATKANTOR, namaAlamatKantor);
        editor.putString(Constanta.LATITUDE2, latitude2);
        editor.putString(Constanta.LONGITUDE2, longitude2);
        editor.commit();
    }

    public static void saveRumahKonsumen(Context context, String namaAlamatRumah, String latitude1, String longitude1){
        SharedPreferences.Editor editor = retrieveSharedPreferencesEditor(context);
        editor.putString(Constanta.NAMAALAMATRUMAH, namaAlamatRumah);
        editor.putString(Constanta.LATITUDE1, latitude1);
        editor.putString(Constanta.LONGITUDE1, longitude1);
        editor.commit();
    }

    public static void saveSurveyKonsumen(Context context, String pertanyaanSatu, String pertanyaanDua, String platNomor, String merkTipe,
                                          String warna, String tahunKendaraan, String jenisKredit, String totalAngsuran, String totalPenghasilan, String pertanyaanLima){
        SharedPreferences.Editor editor = retrieveSharedPreferencesEditor(context);
        editor.putString(String.valueOf(Constanta.PERTANYAANSATU), pertanyaanSatu);
        editor.putString(String.valueOf(Constanta.PERTANYAANDUA), pertanyaanDua);
        editor.putString(String.valueOf(Constanta.PLATNOMOR), platNomor);
        editor.putString(String.valueOf(Constanta.MERKTIPE), merkTipe);
        editor.putString(String.valueOf(Constanta.WARNA), warna);
        editor.putString(String.valueOf(Constanta.TAHUNKENDARAAN), tahunKendaraan);
        editor.putString(Constanta.JENISKREDIT, jenisKredit);
        editor.putString(Constanta.TOTALANGSURAN, totalAngsuran);
        editor.putString(Constanta.TOTALPENGHASILAN, totalPenghasilan);
        editor.putString(String.valueOf(Constanta.PERTANYAANLIMA), pertanyaanLima);

        editor.commit();
    }

    public static void saveSurveyRumahKonsumen(Context context, String pertanyaanEnam, String pertanyaanTujuh, String pertanyaanDelapan,String pertanyaanSembilan,
                                               String pertanyaanSepuluh, String pertanyaanSebelas, String pertanyaanDuaBelas, String pertanyaanTigaBelas){
        SharedPreferences.Editor editor = retrieveSharedPreferencesEditor(context);
        editor.putString(String.valueOf(Constanta.PERTANYAANENAM), pertanyaanEnam);
        editor.putString(String.valueOf(Constanta.PERTANYAANTUJUH), pertanyaanTujuh);
        editor.putString(String.valueOf(Constanta.PERTANYAANDELAPAN), pertanyaanDelapan);
        editor.putString(String.valueOf(Constanta.PERTANYAANSEMBILAN), pertanyaanSembilan);
        editor.putString(String.valueOf(Constanta.PERTANYAANSEPULUH), pertanyaanSepuluh);
        editor.putString(String.valueOf(Constanta.PERTANYAANSEBELAS), pertanyaanSebelas);
        editor.putString(String.valueOf(Constanta.PERTANYAANDUABELAS), pertanyaanDuaBelas);
        editor.putString(String.valueOf(Constanta.PERTANYAANTIGABELAS), pertanyaanTigaBelas);

        editor.commit();
    }

    public static void saveVerifikasiLingkunganRumah(Context context, String pertanyaanEmpatBelas, String pertanyaanLimaBelas, String pertanyaanEnamBelas, String pertanyaanTujuhBelas, String pertanyaanDelapanBelas,
                                                     String pertanyaanSembilanBelas, String pertanyaanDuaPuluh, String pertanyaanDuaSatu, String pertanyaanDuaDua,
                                                     String pertanyaanDuaTiga, String pertanyaanDuaEmpat, String pertanyaanDuaLima, String pertanyaanDuaEnam, String pertanyaanDuaTujuh,
                                                     String jawabanEnamBelas, String jawabanTujuhBelas, String jawabanDelapanBelasTipe, String jawabanDelapanBelasWarna, String jawabanDuaPuluh,
                                                     String jawabanDuaTiga, String jawabanDuaEmpat, String jawabanDuaLimaTipe, String jawabanDuaLimaWarna, String jawabanDuaTujuh, String namaNarasumber1, String namaNarasumber2){
        SharedPreferences.Editor editor = retrieveSharedPreferencesEditor(context);
        editor.putString(String.valueOf(Constanta.PERTANYAANEMPATBELAS), pertanyaanEmpatBelas);
        editor.putString(String.valueOf(Constanta.PERTANYAANLIMABELAS), pertanyaanLimaBelas);
        editor.putString(String.valueOf(Constanta.PERTANYAANENAMBELAS), pertanyaanEnamBelas);
        editor.putString(String.valueOf(Constanta.PERTANYAANTUJUHBELAS), pertanyaanTujuhBelas);
        editor.putString(String.valueOf(Constanta.PERTANYAANDELAPANBELAS), pertanyaanDelapanBelas);
        editor.putString(String.valueOf(Constanta.PERTANYAANSEMBILANBELAS), pertanyaanSembilanBelas);
        editor.putString(String.valueOf(Constanta.PERTANYAANDUAPULUH), pertanyaanDuaPuluh);
        editor.putString(String.valueOf(Constanta.PERTANYAANDUASATU), pertanyaanDuaSatu);
        editor.putString(String.valueOf(Constanta.PERTANYAANDUADUA), pertanyaanDuaDua);
        editor.putString(String.valueOf(Constanta.PERTANYAANDUATIGA), pertanyaanDuaTiga);
        editor.putString(String.valueOf(Constanta.PERTANYAANDUAEMPAT), pertanyaanDuaEmpat);
        editor.putString(String.valueOf(Constanta.PERTANYAANDUALIMA), pertanyaanDuaLima);
        editor.putString(String.valueOf(Constanta.PERTANYAANDUAENAM), pertanyaanDuaEnam);
        editor.putString(String.valueOf(Constanta.PERTANYAANDUATUJUH), pertanyaanDuaTujuh);
        editor.putString(String.valueOf(Constanta.JAWABANENAMBELAS), jawabanEnamBelas);
        editor.putString(String.valueOf(Constanta.JAWABANTUJUHBELAS), jawabanTujuhBelas);
        editor.putString(String.valueOf(Constanta.JAWABANDELAPANBELASTIPE), jawabanDelapanBelasTipe);
        editor.putString(String.valueOf(Constanta.JAWABANDELAPANBELASWARNA), jawabanDelapanBelasWarna);
        editor.putString(String.valueOf(Constanta.JAWABANDUAPULUH), jawabanDuaPuluh);
        editor.putString(String.valueOf(Constanta.JAWABANDUATIGA), jawabanDuaTiga);
        editor.putString(String.valueOf(Constanta.JAWABANDUAEMPAT), jawabanDuaEmpat);
        editor.putString(String.valueOf(Constanta.JAWABANDUALIMATIPE), jawabanDuaLimaTipe);
        editor.putString(String.valueOf(Constanta.JAWABANDUALIMAWARNA), jawabanDuaLimaWarna);
        editor.putString(String.valueOf(Constanta.JAWABANDUATUJUH), jawabanDuaTujuh);
        editor.putString(Constanta.NAMANARASUMBER1, namaNarasumber1);
        editor.putString(Constanta.NAMANARASUMBER2, namaNarasumber2);

        editor.commit();
    }

    public static void savePengamatanSurveyor(Context context, String pertanyaanDuaDelapan, String pertanyaanDuaSembilan, String pertanyaanTigaPuluh, String pertanyaanTigaSatu,
                                              String jawabanDuaDelapan, String jawabanDuaSembilan){
        SharedPreferences.Editor editor = retrieveSharedPreferencesEditor(context);
        editor.putString(String.valueOf(Constanta.PERTANYAANDUADELAPAN), pertanyaanDuaDelapan);
        editor.putString(String.valueOf(Constanta.PERTANYAANDUASEMBILAN), pertanyaanDuaSembilan);
        editor.putString(String.valueOf(Constanta.PERTANYAANTIGAPULUH), pertanyaanTigaPuluh);
        editor.putString(String.valueOf(Constanta.PERTANYAANTIGASATU), pertanyaanTigaSatu);
        editor.putString(Constanta.JAWABANDUADELAPAN, jawabanDuaDelapan);
        editor.putString(Constanta.JAWABANDUASEMBILAN, jawabanDuaSembilan);

        editor.commit();
    }

    public static void saveSurveyLingkunganUsaha(Context context, String pertanyaanTigaDua, String pertanyaanTigaTiga, String pertanyaanTigaEmpat, String pertanyaanTigaLima,
                                                 String jawabanTigaDua, String jawabanTigaTiga, String jawabanTigaEmpat, String jawabanTigaLima, String namaNarasumber3, String namaNarasumber4){
        SharedPreferences.Editor editor = retrieveSharedPreferencesEditor(context);
        editor.putString(String.valueOf(Constanta.PERTANYAANTIGADUA), pertanyaanTigaDua);
        editor.putString(String.valueOf(Constanta.PERTANYAANTIGATIGA), pertanyaanTigaTiga);
        editor.putString(String.valueOf(Constanta.PERTANYAANTIGAEMPAT), pertanyaanTigaEmpat);
        editor.putString(String.valueOf(Constanta.PERTANYAANTIGALIMA), pertanyaanTigaLima);
        editor.putString(Constanta.JAWABANTIGADUA, jawabanTigaDua);
        editor.putString(Constanta.JAWABANTIGATIGA, jawabanTigaTiga);
        editor.putString(Constanta.JAWABANTIGAEMPAT, jawabanTigaEmpat);
        editor.putString(Constanta.JAWABANTIGALIMA, jawabanTigaLima);
        editor.putString(Constanta.NAMANARASUMBER3, namaNarasumber3);
        editor.putString(Constanta.NAMANARASUMBER4, namaNarasumber4);

        editor.commit();
    }

    //save flag login
    public static void saveLoginFlag(Context context, boolean login){
        SharedPreferences.Editor editor = retrieveSharedPreferencesEditor(context);
        editor.putBoolean(Constanta.KEY_FLAG_LOGIN, login);

        editor.commit();
    }

    //get data (getter)

    //ambil flag login
    public static boolean cekLoginFlag(Context context){
        return retrieveSharedPreferences(context).getBoolean(Constanta.KEY_FLAG_LOGIN, false);
    }

    public static String getUsername(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.KEY_USERNAME, "");
    }

    public static String getToken(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.TOKEN, "");
    }

    public static String getUser(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.USER, "");
    }

    public static String getPosition(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.POSITION, "");
    }

    public static String getKodeKonsumen(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.KODEKONSUMEN, "");
    }

    //ambil username
    public static String getNIP(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.KEY_NIP, "");
    }
    //ambil password
    public static String getPassword(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.KEY_PASSWORD, "");
    }
    //ambil remember me
    public static boolean getRemember(Context context){
        return retrieveSharedPreferences(context).getBoolean(Constanta.KEY_REMEMBER, false);
    }

    public static String getPertanyaanSatu(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.PERTANYAANSATU, "");
    }

    public static String getPertanyaanDua(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.PERTANYAANDUA, "");
    }

    public static String getMerkTipe(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.MERKTIPE, "");
    }

    public static String getPlatNomor(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.PLATNOMOR, "");
    }

    public static String getWarna(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.WARNA, "");
    }

    public static String getTahunKendaraan(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.TAHUNKENDARAAN, "");
    }

    public static String getJenisKredit(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.JENISKREDIT, "");
    }

    public static String getTotalAngsuran(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.TOTALANGSURAN, "");
    }

    public static String getTotalPenghasilan(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.TOTALPENGHASILAN, "");
    }

    public static String getPertanyaanLima(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.PERTANYAANLIMA, "");
    }

    public static String getPertanyaanEnam(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.PERTANYAANENAM, "");
    }

    public static String getPertanyaanTujuh(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.PERTANYAANTUJUH, "");
    }

    public static String getPertanyaanDelapan(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.PERTANYAANDELAPAN, "");
    }

    public static String getPertanyaanSembilan(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.PERTANYAANSEMBILAN, "");
    }

    public static String getPertanyaanSepuluh(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.PERTANYAANSEPULUH, "");
    }

    public static String getPertanyaanSebelas(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.PERTANYAANSEBELAS, "");
    }

    public static String getPertanyaanDuaBelas(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.PERTANYAANDUABELAS, "");
    }

    public static String getPertanyaanTigaBelas(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.PERTANYAANTIGABELAS, "");
    }

    public static String getPertanyaanEmpatBelas(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.PERTANYAANEMPATBELAS, "");
    }

    public static String getPertanyaanLimaBelas(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.PERTANYAANLIMABELAS, "");
    }

    public static String getPertanyaanEnamBelas(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.PERTANYAANENAMBELAS, "");
    }

    public static String getPertanyaanTujuhBelas(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.PERTANYAANTUJUHBELAS, "");
    }

    public static String getPertanyaanDelapanBelas(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.PERTANYAANDELAPANBELAS, "");
    }

    public static String getPertanyaanSembilanBelas(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.PERTANYAANSEMBILANBELAS, "");
    }

    public static String getPertanyaanDuaPuluh(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.PERTANYAANDUAPULUH, "");
    }

    public static String getPertanyaanDuaSatu(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.PERTANYAANDUASATU, "");
    }

    public static String getPertanyaanDuaDua(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.PERTANYAANDUADUA, "");
    }

    public static String getPertanyaanDuaTiga(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.PERTANYAANDUATIGA, "");
    }

    public static String getPertanyaanDuaEmpat(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.PERTANYAANDUAEMPAT, "");
    }

    public static String getPertanyaanDuaLima(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.PERTANYAANDUALIMA, "");
    }

    public static String getPertanyaanDuaEnam(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.PERTANYAANDUAENAM, "");
    }

    public static String getPertanyaanDuaTujuh(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.PERTANYAANDUATUJUH, "");
    }

    public static String getJawabanEnamBelas(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.JAWABANENAMBELAS, "");
    }

    public static String getJawabanTujuhBelas(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.JAWABANTUJUHBELAS, "");
    }

    public static String getJawabanDelapanBelasTipe(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.JAWABANDELAPANBELASTIPE, "");
    }

    public static String getJawabanDelapanBelasWarna(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.JAWABANDELAPANBELASWARNA, "");
    }

    public static String getJawabanDuaPuluh(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.JAWABANDUAPULUH, "");
    }

    public static String getJawabanDuaTiga(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.JAWABANDUATIGA, "");
    }

    public static String getJawabanDuaEmpat(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.JAWABANDUAEMPAT, "");
    }

    public static String getJawabanDuaLimaTipe(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.JAWABANDUALIMATIPE, "");
    }

    public static String getJawabanDuaLimaWarna(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.JAWABANDUALIMAWARNA, "");
    }

    public static String getJawabanDuaTujuh(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.JAWABANDUATUJUH, "");
    }

    public static String getNamaNarasumber1(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.NAMANARASUMBER1, "");
    }

    public static String getNamaNarasumber2(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.NAMANARASUMBER2, "");
    }

    public static String getPertanyaanDuaDelapan(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.PERTANYAANDUADELAPAN, "");
    }

    public static String getPertanyaanDuaSembilan(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.PERTANYAANDUASEMBILAN, "");
    }

    public static String getPertanyaanTigaPuluh(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.PERTANYAANTIGAPULUH, "");
    }

    public static String getPertanyaanTigaSatu(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.PERTANYAANTIGASATU, "");
    }

    public static String getJawabanDuaDelapan(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.JAWABANDUADELAPAN, "");
    }

    public static String getJawabanDuaSembilan(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.JAWABANDUASEMBILAN, "");
    }

    public static String getPertanyaanTigaDua(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.PERTANYAANTIGADUA, "");
    }

    public static String getPertanyaanTigaTiga(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.PERTANYAANTIGATIGA, "");
    }

    public static String getPertanyaanTigaEmpat(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.PERTANYAANTIGAEMPAT, "");
    }

    public static String getPertanyaanTigaLima(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.PERTANYAANTIGALIMA, "");
    }

    public static String getJawabanTigaDua(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.JAWABANTIGADUA, "");
    }

    public static String getJawabanTigaTiga(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.JAWABANTIGATIGA, "");
    }

    public static String getJawabanTigaEmpat(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.JAWABANTIGAEMPAT, "");
    }

    public static String getJawabanTigaLima(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.JAWABANTIGALIMA, "");
    }

    public static String getNamaNarasumber3(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.NAMANARASUMBER3, "");
    }

    public static String getNamaNarasumber4(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.NAMANARASUMBER4, "");
    }

    public static String getInformasiTambahan(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.INFORMASITAMBAHAN, "");
    }

    public static String getNamaAlamatRumah(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.NAMAALAMATRUMAH, "");
    }

    public static String getLatitude1(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.LATITUDE1, "");
    }

    public static String getLongitude1(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.LONGITUDE1, "");
    }

    public static String getNamaAlamatKantor(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.NAMAALAMATKANTOR, "");
    }

    public static String getLatitude2(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.LATITUDE2, "");
    }

    public static String getLongitude2(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.LONGITUDE2, "");
    }
}
