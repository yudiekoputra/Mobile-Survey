package com.bcafinance.itdp.mobilesurvey.fragmentsCMO;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bcafinance.itdp.mobilesurvey.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

import mumayank.com.airlocationlibrary.AirLocation;

public class GetMapsAddressActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    LocationManager locationManager;
    private TextView latitude, longitude, namaAlamatRumah;
    private AirLocation airLocation;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_maps_address);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        namaAlamatRumah = findViewById(R.id.namaAlamatRumah);
        latitude = findViewById(R.id.latitude);
        longitude = findViewById(R.id.longitude);
        Button buttonMap1=findViewById(R.id.buttonMap1);
        buttonMap1.setOnClickListener(v -> getMyLocation());
        if (!fakeGPS()) {
        }else{
            Toast.makeText(context, "Anda Menggunakan Fake GPS", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void getMyLocation(){
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
//            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000000, 5, this);
            locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, null);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
        airLocation = new AirLocation(this, true, true, new AirLocation.Callbacks() {
            @Override
            public void onSuccess(Location location) {
                String latitude_value = String.valueOf(location.getLatitude());
                String longitude_value = String.valueOf(location.getLongitude());

                latitude.setText(latitude_value);
                longitude.setText(longitude_value);

                LatLng myLocation=new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(myLocation).title("Saya sekarang disini!"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
            }

            @Override
            public void onFailed(AirLocation.LocationFailedEnum locationFailedEnum) {
                Toast.makeText(context,"Gagalmendeteksi Lokasi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        airLocation.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

    }

    public boolean fakeGPS(){
        // returns true if mock location enabled, false if not enabled.
        if (Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ALLOW_MOCK_LOCATION).equals("0")){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            namaAlamatRumah.setText(namaAlamatRumah.getText() +addresses.get(0).getAddressLine(0)+", "+
                    addresses.get(0).getAddressLine(1)+", "+addresses.get(0).getAddressLine(2));
        }catch(Exception e)
        {

        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
