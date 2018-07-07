package pe.edu.upc.proyectotsys.viewcontrollers.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import pe.edu.upc.proyectotsys.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private static final int REQ_PERMISSION = 0;
    private GoogleMap mMap;
    private Location currentLocation;
    private LocationManager locationManager;
    private CameraPosition camera;
    private Marker marker;
    private Double dblLat, dblLon;
    private SharedPreferences prefs_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        prefs_map = getSharedPreferences("Preferences_Maps", Context.MODE_PRIVATE);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GuardarCoordenadas(dblLat.toString(), dblLon.toString());
                finish();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        locationManager = (LocationManager) MapsActivity.this.getSystemService(LOCATION_SERVICE);

        if(checkPermission()){
            mMap.setMyLocationEnabled(true);

        }else {askPermission();}

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, this);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
    }

    private void zoomToLocation(Location location){
        camera = new CameraPosition.Builder()
                .target(new LatLng(location.getLatitude(), location.getLongitude()))
                .zoom(15)
                .bearing(0)
                .tilt(30)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera));
    }

    private void CreateOrUpdateMarkerByLocation(Location location){
        if (marker == null){
            marker = mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).draggable(true));
        }
    }

    // Check for permission to access Location
    private boolean checkPermission() {
        return (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);
    }
    // Asks for permission
    private void askPermission() {
        ActivityCompat.requestPermissions(this, new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION
        },REQ_PERMISSION);
    }


    private void GuardarCoordenadas(String lat, String lng){
            SharedPreferences.Editor edit = prefs_map.edit();
            edit.putString("latitud", lat);
            edit.putString("longitud", lng);
            edit.apply();
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch ( requestCode ) {
//            case REQ_PERMISSION: {
//                if ( grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
//                    if(checkPermission()) mMap.setMyLocationEnabled(true);
//
//                } else {
//                    // Permission denied
//
//                }
//                break;
//            }
//        }
//    }

    @Override
    public void onLocationChanged(Location location) {
        CreateOrUpdateMarkerByLocation(location);
        dblLon = location.getLongitude();
        dblLat = location.getLatitude();
        zoomToLocation(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}