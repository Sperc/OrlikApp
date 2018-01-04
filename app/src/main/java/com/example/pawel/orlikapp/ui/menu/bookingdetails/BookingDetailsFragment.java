package com.example.pawel.orlikapp.ui.menu.bookingdetails;


import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.Booking;
import com.example.pawel.orlikapp.model.Player;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingDetailsFragment extends Fragment implements BookingDetailsView {

    ListView listView;
    TextView organiserTextView;
    TextView bookingDateTextView;
    TextView bookingTimeTextView;
    Button jointBtn;
    Button escapeBtn;

    private BookingDetailsPresenter bookingDetailsPresenter;
    public BookingDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booking_details, container, false);
        init(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bookingDetailsPresenter = new BookingDetailsPresenter(this);
        bookingDetailsPresenter.getBookingList(new Long(1),bookingListener);
    }
    private void init(View view){
        listView = (ListView)view.findViewById(R.id.participantsList);
        bookingDateTextView = (TextView)view.findViewById(R.id.bookingDate);
        organiserTextView = (TextView)view.findViewById(R.id.organiser);
        bookingTimeTextView = (TextView)view.findViewById(R.id.bookingTime);
        jointBtn = (Button)view.findViewById(R.id.join);
        escapeBtn = (Button)view.findViewById(R.id.escape);
    }
    private void setTextViewInformation(Booking booking){
        organiserTextView.setText(getString(R.string.organiser)+": "+booking.getLeaderName());
        bookingDateTextView.setText(booking.getDate());
        bookingTimeTextView.setText(booking.getStartOrderHour()+":"+booking.getStartOrderMinutes()+" - "+ booking.getEndOrderHour()+":"+booking.getEndOrderMinutes());
    }
    private void setPlayerListView(List<Player> playerList){
        ArrayAdapter arrayAdapter = new ArrayAdapter<Player>(getContext(),android.R.layout.simple_list_item_1,playerList);
        listView.setAdapter(arrayAdapter);
    }
    private void setButton(List<Player> playerList){
        for(Player p: playerList){
            if(p.getUsername().equals(PreferencesShared.onReadString(PreferencesSharedKyes.username))){
                jointBtn.setVisibility(View.GONE);
                escapeBtn.setVisibility(View.VISIBLE);
                return;
            }
            jointBtn.setVisibility(View.VISIBLE);
            escapeBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSucces() {
        Toast.makeText(getActivity(), "accept", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure() {
        Toast.makeText(getActivity(), "failure", Toast.LENGTH_SHORT).show();
    }

    BookingDetailsPresenter.BookingListener bookingListener = new BookingDetailsPresenter.BookingListener() {
        @Override
        public void getBooking(Booking booking) {
            setPlayerListView(booking.getPlayers());
            setTextViewInformation(booking);
            setButton(booking.getPlayers());
            Toast.makeText(getContext(), String.valueOf(booking.getPlayers().size()), Toast.LENGTH_SHORT).show();
        }
    };
}
