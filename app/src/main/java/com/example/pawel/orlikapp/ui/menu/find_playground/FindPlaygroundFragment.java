package com.example.pawel.orlikapp.ui.menu.find_playground;


import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.Playground;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.List;

public class FindPlaygroundFragment extends Fragment implements OnMapReadyCallback, FindPlaygroundView, GoogleMap.OnMarkerClickListener {
    FindPlaygroundPresenter findPlaygroundPresenter;
    FloatingSearchView floatingSearchView;
    Spinner spinner;

    RelativeLayout relativeLayout;
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
//        autocompleteTest();
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
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lati, longi), 12));

        findPlaygroundPresenter.getPlaygroundByCity(PreferencesShared.onReadString(PreferencesSharedKyes.city), getListener(googleMap));
        spinerFunctions(googleMap);

    }


    @Override
    public void onResponsePresenter(int message) {
        Toast.makeText(getActivity(), getString(message), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
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

//    private void autocompleteTest() {
//        multiAutoCompleteTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
//                if (i == EditorInfo.IME_ACTION_SEARCH || i == EditorInfo.IME_ACTION_DONE || keyEvent.getAction() == KeyEvent.ACTION_DOWN || keyEvent.getAction() == KeyEvent.ENTER) {
//                    Toast.makeText(getActivity(), "test", Toast.LENGTH_SHORT).show();
//                }
//                return false;
//            }
//        });
//    }

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
                    shadowLayout.setVisibility(View.INVISIBLE);
                    Toast.makeText(getActivity(), "zmiana", Toast.LENGTH_SHORT).show();
                    multiAutoCompleteTextView.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, null, null);
                }
            }
        });
        multiAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView t = (TextView) view;
                Toast.makeText(getContext(), t.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        multiAutoCompleteTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (motionEvent.getX() <= (multiAutoCompleteTextView.getCompoundDrawables()[DRAWABLE_LEFT].getBounds().width())) {
                        // your action here
                        String text = multiAutoCompleteTextView.getText().toString();
                        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();

                        return true;
                    } else if (multiAutoCompleteTextView.getCompoundDrawables()[DRAWABLE_RIGHT] != null) {
                        if (motionEvent.getRawX() + multiAutoCompleteTextView.getPaddingRight() >= (multiAutoCompleteTextView.getRight() - multiAutoCompleteTextView.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            // your action here
                            Toast.makeText(getActivity(), "workRight", Toast.LENGTH_SHORT).show();
                            shadowLayout.setVisibility(View.GONE);
                            return true;
                        }
                    }
                }
                return false;
            }

        });

    }

    void initSearchAdpater(List<Playground> playgrounds) {
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, DataHelper.getListString(playgrounds));
        multiAutoCompleteTextView.setAdapter(adapter);
        multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        multiAutoCompleteTextView.setThreshold(2);
    }

    public FindPlaygroundPresenter.FindPlaygroundListener getListener(final GoogleMap googleMap) {
        return new FindPlaygroundPresenter.FindPlaygroundListener() {
            @Override
            public void onSucces(List<Playground> playgrounds) {
                googleMap.clear();
                Map.addMarkerFromList(googleMap, playgrounds);
                initSearchAdpater(playgrounds);
            }
        };
    }

}
