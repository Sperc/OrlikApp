package com.example.pawel.orlikapp.ui.menu.find_playground;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.Playground;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.ui.menu.details_playground.DetailsPlaygroundFragment;
import com.example.pawel.orlikapp.utils.ConstansValues;
import com.example.pawel.orlikapp.utils.MyTokenizer;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;

import java.util.List;
import java.util.Map;

public class FindPlaygroundFragment extends Fragment implements OnMapReadyCallback, FindPlaygroundView, GoogleMap.OnMarkerClickListener {
    private static String defaultCategory = "Piłka nożna";
    FindPlaygroundPresenter findPlaygroundPresenter;
    Spinner spinner;
    //    LinearLayout detailLayout;
    MapView mapView;
    private GoogleMap mGoogleMap;
    MultiAutoCompleteTextView multiAutoCompleteTextView;
    LinearLayout shadowLayout;
    ArrayAdapter<String> adapter;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_playground, container, false);
        init(view);
        autoCompleteFunctons();
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.getMapAsync(this);
            mapView.onResume();
        }
    }


    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mGoogleMap = googleMap;
        MapsInitializer.initialize(getContext());
        double lati = Double.parseDouble(PreferencesShared.onReadString(PreferencesSharedKyes.latitude));
        double longi = Double.parseDouble(PreferencesShared.onReadString(PreferencesSharedKyes.longitude));
        updateCamera(new LatLng(lati, longi), ConstansValues.MAP_ZOOM_DEFAULT);

//        findPlaygroundPresenter.getPlaygroundByCity(PreferencesShared.onReadString(PreferencesSharedKyes.city), getListener(googleMap));
        findPlaygroundPresenter.getPlaygroundByCityAndCategory(PreferencesShared.onReadString(PreferencesSharedKyes.city), defaultCategory, getListener(googleMap));
        spinerFunctions(googleMap);
        googleMap.setOnMarkerClickListener(this);
        mGoogleMap.setInfoWindowAdapter(new CustomWindowAdapter(getContext()));
        mGoogleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                onStartDetailsFragment(marker);
//                Intent intent = new Intent(getContext(), DetailPlaygroundActivity.class);
//                Playground playground = (Playground) marker.getTag();
//                intent.putExtra("playground", playground);
//                startActivity(intent);
            }
        });
    }


    @Override
    public void onResponsePresenter(int message) {
        Toast.makeText(getContext(), getString(message), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        Playground p = (Playground) marker.getTag();
        updateCamera(new LatLng(p.getLatitude(), p.getLongitude()), ConstansValues.MAP_ZOOM_DETAIL);

        return false;
    }


    private void init(View view) {
        mapView = (MapView) view.findViewById(R.id.mapView);
        shadowLayout = (LinearLayout) view.findViewById(R.id.shadowLayout);
        multiAutoCompleteTextView = (MultiAutoCompleteTextView) view.findViewById(R.id.searchAutoComplete);
        findPlaygroundPresenter = new FindPlaygroundPresenter(this);
        spinner = (Spinner) view.findViewById(R.id.categorySpinner);
        ArrayAdapter<CharSequence> spinadapter = ArrayAdapter.createFromResource(getActivity(), R.array.categories, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinadapter);
    }

    public FindPlaygroundPresenter.FindPlaygroundListener getListener(final GoogleMap googleMap) {
        return new FindPlaygroundPresenter.FindPlaygroundListener() {
            @Override
            public void onSucces(List<Playground> playgrounds) {
                googleMap.clear();
                MapHelper.addMarkerFromList(googleMap, playgrounds);
                multiAutoCompleteConfig(playgrounds);
                onDrawableMultitexClick(DataHelper.getPlaygroundsLatLng(playgrounds), googleMap);
            }
        };
    }

    public void autoCompleteFunctons() {
        multiAutoCompleteTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            Drawable drawableRight = getResources().getDrawable(R.drawable.ic_clear_black_24dp, null);
            Drawable drawableLeft = getResources().getDrawable(R.drawable.ic_search_black_24dp, null);

            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    shadowLayout.setVisibility(View.VISIBLE);
                    multiAutoCompleteTextView.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, drawableRight, null);
                } else {
                    shadowLayout.setVisibility(View.GONE);
                    multiAutoCompleteTextView.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, null, null);
                }
            }
        });
        //turn off shadow background
        shadowLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                multiAutoCompleteTextView.clearFocus();
                return false;
            }
        });
    }

    private void onDrawableMultitexClick(final Map<String, LatLng> mapPlaygrounds, final GoogleMap googleMap) {
        multiAutoCompleteTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_RIGHT = 2;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (motionEvent.getX() <= (multiAutoCompleteTextView.getCompoundDrawables()[DRAWABLE_LEFT].getBounds().width())) {
                        // your action here
                        String text = multiAutoCompleteTextView.getText().toString();
                        LatLng latLng = mapPlaygrounds.get(text);
                        if (latLng != null) {
                            updateCamera(latLng, ConstansValues.MAP_ZOOM_DETAIL);
                        } else {
                            Toast.makeText(getContext(), R.string.wrongAddresName, Toast.LENGTH_SHORT).show();
                        }
                        clearInputFocus();
                        return true;
                    } else if (multiAutoCompleteTextView.getCompoundDrawables()[DRAWABLE_RIGHT] != null) {
                        if (motionEvent.getRawX() + multiAutoCompleteTextView.getPaddingRight() >= (multiAutoCompleteTextView.getRight() - multiAutoCompleteTextView.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            // your action here
                            multiAutoCompleteTextView.setText("");
                            clearInputFocus();
                            return true;
                        }
                    }
                }
                return false;

            }

        });
    }


    private void spinerFunctions(final GoogleMap googleMap) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = (TextView) view;
                String category = textView.getText().toString();
                findPlaygroundPresenter.getPlaygroundByCityAndCategory(PreferencesShared.onReadString(PreferencesSharedKyes.city), category, getListener(googleMap));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    void multiAutoCompleteConfig(List<Playground> playgrounds) {
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, DataHelper.getListString(playgrounds));
        multiAutoCompleteTextView.setAdapter(adapter);
        multiAutoCompleteTextView.setTokenizer(new MyTokenizer());
        multiAutoCompleteTextView.setThreshold(2);
    }

    public void updateCamera(LatLng latLng, float zoom) {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)
                .zoom(zoom)
//                .bearing(90)
//                .tilt(30)
                .build();
        mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }

    private void clearInputFocus() {
        multiAutoCompleteTextView.clearFocus();
        setOffSoftKeyboard();
    }

    private void setOffSoftKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void onStartDetailsFragment(Marker marker) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        DetailsPlaygroundFragment detailsPlaygroundFragment = new DetailsPlaygroundFragment();
//        InitializeDetailsPlaygroundFragment initializeDetailsPlaygroundFragment = new InitializeDetailsPlaygroundFragment();
        Bundle bundle = new Bundle();
        Playground playground = (Playground) marker.getTag();

        bundle.putSerializable("playground", playground);
        detailsPlaygroundFragment.setArguments(bundle);
        ft.replace(R.id.flcontent, detailsPlaygroundFragment);
        ft.addToBackStack(null);
        ft.commit();


//
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.flcontent, detailsPlaygroundFragment).commit();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Toast.makeText(getActivity(), "test", Toast.LENGTH_SHORT).show();
    }
}
