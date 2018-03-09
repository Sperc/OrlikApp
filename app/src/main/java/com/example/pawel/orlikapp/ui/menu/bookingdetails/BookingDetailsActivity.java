package com.example.pawel.orlikapp.ui.menu.bookingdetails;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.Booking;
import com.example.pawel.orlikapp.model.Picture;
import com.example.pawel.orlikapp.model.Player;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.api.ServiceGenerator;
import com.example.pawel.orlikapp.ui.menu.create_booking.CreateBookingActivity;
import com.example.pawel.orlikapp.utils.Logs;
import com.example.pawel.orlikapp.utils.Time;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import de.hdodenhof.circleimageview.CircleImageView;

public class BookingDetailsActivity extends AppCompatActivity implements BookingDetailsView {
    private int BUTTON = 0; // 1 - addButton, 0 - removeButton
    private ImageButton openParticipantsList;
    private TextView timeBookingTextView;
    private TextView dateBookingTextView;
    private TextView freePlaces;
    private TextView playerName;
    private TextView playerEmail;
    private CircleImageView circleImageView;
    private FrameLayout frameLayout;
    private ImageButton addToList, deleteBtn;
    private TextView maxPlaces;
    private TextView playgroundName;
    private TextView address;

    private BookingDetailsPresenter bookingDetailsPresenter;
    private long bookingId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);
        Intent intent = getIntent();
        bookingId = (long) intent.getSerializableExtra("booking_id");
        bookingDetailsPresenter = new BookingDetailsPresenter(this);
        init();
        onClick();
        bookingDetailsPresenter.getBookingById(bookingId, bookingListener);
    }//

    @Override
    protected void onResume() {
        super.onResume();
//        bookingDetailsPresenter.getBookingById(bookingId, bookingListener);
    }

    private void init() {
        deleteBtn = (ImageButton) findViewById(R.id.deleteBtn);
        addToList = (ImageButton) findViewById(R.id.addToList);
        frameLayout = (FrameLayout) findViewById(R.id.frame);
        timeBookingTextView = (TextView) findViewById(R.id.timeBookingTextView);
        dateBookingTextView = (TextView) findViewById(R.id.dateBookingTextView);
        openParticipantsList = (ImageButton) findViewById(R.id.openListDialog);
        freePlaces = (TextView) findViewById(R.id.freePlaces);
        playerEmail = (TextView) findViewById(R.id.playerEmail);
        playerName = (TextView) findViewById(R.id.playerName);
        circleImageView = (CircleImageView) findViewById(R.id.playerPhoto);
        maxPlaces = (TextView) findViewById(R.id.maxPlaces);
        playgroundName = (TextView) findViewById(R.id.playgroundName);
        address = (TextView) findViewById(R.id.address);
    }

    private void onClick() {
        addToList.setOnClickListener(view -> {
            if (BUTTON == 0) {
                bookingDetailsPresenter.removePlayerFromBooking(bookingId, PreferencesShared.onReadString(PreferencesSharedKyes.username), onBookingPlayerListener);
                BUTTON = 1;
            } else {
                bookingDetailsPresenter.addPlayerToBooking(bookingId, onBookingPlayerListener);
                BUTTON = 0;
            }

        });
        deleteBtn.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(BookingDetailsActivity.this);
            builder.setTitle(getString(R.string.deleteBookingDialog));
            builder.setPositiveButton("TAK", (dialogInterface, i) -> {
                bookingDetailsPresenter.onDeleteBooking(bookingId);
            });
            builder.setNegativeButton("Anuluj", (dialogInterface, i) -> dialogInterface.dismiss());
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });
    }

    private void openBookingPlayerList(final List<Player> playerList) {
        openParticipantsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frameLayout.setVisibility(View.VISIBLE);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                PlayerListFragmet playerListFragmet = new PlayerListFragmet();
                Bundle bundle = new Bundle();
                bundle.putSerializable("player_list", (Serializable) playerList);
                bundle.putSerializable("isLeader", isLeader());
                bundle.putSerializable("leaderName", playerEmail.getText().toString());
                playerListFragmet.setArguments(bundle);
                ft.replace(R.id.frame, playerListFragmet);
                ft.addToBackStack(null);
                ft.commit();

            }
        });
    }

    private void setButton(List<Player> playerList) {
        for (Player p : playerList) {
            if (p.getUsername().equals(PreferencesShared.onReadString(PreferencesSharedKyes.username))) {
                //delete
                BUTTON = 0;
//                addToList.setText("Usun");
                addToList.setBackground(getDrawable(R.drawable.circle_background_remove_icon));
                freePlaces.setText(String.valueOf(playerList.size()));
                return;
            }
        }
        //add
        BUTTON = 1;
//        addToList.setText("Dodaj");
        addToList.setBackground(getDrawable(R.drawable.circle_background_add_icon));
        freePlaces.setText(String.valueOf(playerList.size()));
    }

    //true - uzytkownik jest liderem, false - nie jest
    public boolean isLeader() {
        String username = PreferencesShared.onReadString(PreferencesSharedKyes.username);
        if (username.equals(playerEmail.getText().toString()))
            return true;
        return false;
    }

    BookingDetailsPresenter.BookingListener bookingListener = new BookingDetailsPresenter.BookingListener() {
        @Override
        public void getBooking(Booking booking) {
            // if leader show deleteBooking Btn else hide
            if (booking.getLeaderName().equals(PreferencesShared.onReadString(PreferencesSharedKyes.username))) {
                deleteBtn.setVisibility(View.VISIBLE);
            } else {
                deleteBtn.setVisibility(View.GONE);
            }

            address.setText(booking.getPlayground().getAddres());
            playgroundName.setText(booking.getPlayground().getName());
            setButton(booking.getPlayers());
            playerEmail.setText(booking.getLeaderName());
            bookingDetailsPresenter.getPlayerByUsername(booking.getLeaderName(), getPlayerListener);
            freePlaces.setText(String.valueOf(booking.getPlayers().size()));
            maxPlaces.setText("/" + String.valueOf(booking.getMaxNumberOfPlayer()));
            dateBookingTextView.setText(booking.getDate());
            Time startTime = new Time(booking.getStartOrderHour(), booking.getStartOrderMinutes());
            Time endTime = new Time(booking.getEndOrderHour(), booking.getEndOrderMinutes());
            timeBookingTextView.setText(startTime.displayTime() + " - " + endTime.displayTime());
            openBookingPlayerList(booking.getPlayers());
        }
    };

    BookingDetailsPresenter.GetPlayerListener getPlayerListener = new BookingDetailsPresenter.GetPlayerListener() {
        @Override
        public void onSucces(Player player) {
            playerName.setText(player.toString());
            Optional<Picture> picture = Optional.ofNullable(player.getPicture());
            picture.ifPresent(picture1 -> Picasso.with(getApplicationContext()).load(ServiceGenerator.BASE_URL_IMAGE + picture1.getId()).into(circleImageView));
        }
    };
    BookingDetailsPresenter.OnBookingPlayerListener onBookingPlayerListener = new BookingDetailsPresenter.OnBookingPlayerListener() {
        @Override
        public void onSucces(List<Player> playerList) {
            setButton(playerList);
            Toast.makeText(BookingDetailsActivity.this, "Dodano", Toast.LENGTH_SHORT).show();
            openBookingPlayerList(playerList);
        }
    };

    @Override
    public void onSucces() {
        Logs.d("BookingDetailsActivity", "DELETE_BOOKING_SUCCESFUL");
        finish();
    }

    @Override
    public void onFailure() {
        Logs.d("BookingDetailsActivity", "FAILURE");
    }

    @Override
    public void onServerNotResponse() {
        Logs.d("BookingDetailsActivity", "SERVER_NOT_RESPONSE");
    }
}