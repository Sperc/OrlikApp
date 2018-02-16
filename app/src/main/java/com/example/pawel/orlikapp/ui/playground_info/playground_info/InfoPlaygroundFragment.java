package com.example.pawel.orlikapp.ui.playground_info.playground_info;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.Playground;
import com.example.pawel.orlikapp.ui.menu.details_playground.DetailsPlaygroundFragment;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoPlaygroundFragment extends Fragment {


    public InfoPlaygroundFragment() {
        // Required empty public constructor
    }

    private ImageView playgroundPhoto;
    private TextView addressTextView;
    private TextView avaiableTextView;
    private TextView timeTextView;
    private TextView phoneNumberTextView;
    private TextView description;
    private Button booking;

    Playground playground;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info_playground, container, false);
        ButterKnife.bind(view);
        Bundle bundle = getArguments();
        playground = (Playground) bundle.getSerializable("playground");
        init(view);
        onClick(view);
        return view;
    }

    private void onClick(View view) {
        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStartDetailsFragment(playground);
            }
        });
    }

    private void init(View view) {
        playgroundPhoto = (ImageView) view.findViewById(R.id.playgroundPhoto);
        addressTextView = (TextView) view.findViewById(R.id.addresTextView);
        avaiableTextView = (TextView) view.findViewById(R.id.avaiableTextView);
        timeTextView = (TextView) view.findViewById(R.id.timeTextView);
        phoneNumberTextView = (TextView) view.findViewById(R.id.phoneNumberTextView);
        description = (TextView) view.findViewById(R.id.descriptionTextView);
        booking = (Button) view.findViewById(R.id.bookingBtn);
    }

    private void onStartDetailsFragment(Playground pl) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        DetailsPlaygroundFragment detailsPlaygroundFragment = new DetailsPlaygroundFragment();
//        InitializeDetailsPlaygroundFragment initializeDetailsPlaygroundFragment = new InitializeDetailsPlaygroundFragment();
        Bundle bundle = new Bundle();
        Playground playground = (Playground) pl;

        bundle.putSerializable("playground", playground);
        detailsPlaygroundFragment.setArguments(bundle);
        ft.replace(R.id.playground_container, detailsPlaygroundFragment);
        ft.addToBackStack(null);
        ft.commit();


//
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.flcontent, detailsPlaygroundFragment).commit();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().finish();
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        getActivity().finish();
    }
}
