package com.example.chai.cocktails.views.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chai.cocktails.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocationFragment extends Fragment {
    private MapView mMapView;
    private GoogleMap googleMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, null, false);
        mMapView = view.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
                googleMap.setMyLocationEnabled(true);
                //To add marker
                LatLng singapore = new LatLng(1.28967, 103.85007);


                // For zooming functionality
                CameraPosition cameraPosition = new CameraPosition.Builder().target(singapore).zoom(14).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                //Creation of individual markers
                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(1.303839, 103.861482))
                        .title("Hawaii Cabaret & Nite-club")
                        .snippet("Textile Centre #04-01, 200 Jalan Sultan (S)199018, 199018"));

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(1.28967, 103.85007))
                        .title("Zouk Singapore")
                        .snippet("3C River Valley Road, Clarke Quay, #01-05 to #02-06, The Cannery, Singapore 179022"));

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(1.28967, 103.85007))
                        .title("F. Club Singapore")
                        .snippet("3B River Valley Rd, Clarke Quay Blk B, #01-08, Singapore 179021"));

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(1.290405, 103.847115))
                        .title("Attica Nightclub")
                        .snippet("3A River Valley Road, Clarke Quay, #02-05, 179020"));

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(1.2912935, 103.8615296))
                        .title("BANG BANG")
                        .snippet("7 Raffles Blvd, Marina Square, Singapore 039595"));

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(1.28967, 103.85007))
                        .title("Canvas")
                        .snippet("20 Upper Circular Rd, #B1-01/06 The Riverwalk, Singapore 058416"));
            }
        });
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
