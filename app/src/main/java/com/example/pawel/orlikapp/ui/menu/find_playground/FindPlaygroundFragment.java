package com.example.pawel.orlikapp.ui.menu.find_playground;


import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.Playground;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.utils.DataHelper;
import com.example.pawel.orlikapp.utils.Logs;
import com.example.pawel.orlikapp.utils.Suggestion;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class FindPlaygroundFragment extends Fragment implements OnMapReadyCallback, FindPlaygroundView {
    SupportMapFragment supportMapFragment;
    FindPlaygroundPresenter findPlaygroundPresenter;
    FloatingSearchView floatingSearchView;

    MapView mapView;
    private GoogleMap googleMap;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_playground, container, false);
        floatingSearchView = (FloatingSearchView) view.findViewById(R.id.floating_search_view);
        init(view);
        onFloatingSearchViewInitComponent();
        //findPlaygroundPresenter.getPlaygroundByCity(PreferencesShared.onReadString(PreferencesSharedKyes.city));
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }



    @Override
    public void onMapReady(final GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        this.googleMap = googleMap;
        double lati = Double.parseDouble(PreferencesShared.onReadString(PreferencesSharedKyes.latitude));
        double longi = Double.parseDouble(PreferencesShared.onReadString(PreferencesSharedKyes.longitude));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lati, longi), 12));

        findPlaygroundPresenter.getPlaygroundByCity(PreferencesShared.onReadString(PreferencesSharedKyes.city), new FindPlaygroundPresenter.FindPlaygroundListener() {
            @Override
            public void onSucces(List<Playground> playgrounds) {
                Map.addMarkerFromList(googleMap,playgrounds);
                DataHelper.loadData(playgrounds);
            }
        });

    }

    @Override
    public void onResponse(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    //    @Override
//    public void onSucces(List<Playground> playgrounds) {
//        DataHelper.loadData(playgrounds);
//    }
//
//    @Override
//    public void onFailure() {
//        Toast.makeText(getActivity(), R.string.serverProblem, Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onAuthorizationProblem() {
//        Toast.makeText(getActivity(), R.string.authorizationProblem, Toast.LENGTH_SHORT).show();
//    }
    private void init(View view) {
        floatingSearchView = (FloatingSearchView) view.findViewById(R.id.floating_search_view);
        mapView = (MapView) view.findViewById(R.id.mapView);
        findPlaygroundPresenter = new FindPlaygroundPresenter(this);
    }

    public void onFloatingSearchViewInitComponent() {
        floatingSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
                Toast.makeText(getActivity(), searchSuggestion.getBody(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSearchAction(String currentQuery) {
                Toast.makeText(getActivity(), currentQuery + 11, Toast.LENGTH_SHORT).show();
            }
        });
        floatingSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                if (!oldQuery.equals("") && newQuery.equals("")) {
                    floatingSearchView.clearSuggestions();
                } else {
                    DataHelper.findSuggestions(getActivity(), newQuery, 5, new DataHelper.OnFindSuggestionsListener() {
                        @Override
                        public void onResults(List<Suggestion> results) {
                            floatingSearchView.swapSuggestions(results);
                        }
                    });
                }
            }
        });
    }

}
