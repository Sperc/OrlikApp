package com.example.pawel.orlikapp.ui.menu.create_booking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.Booking;
import com.example.pawel.orlikapp.model.Picture;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.api.ServiceGenerator;
import com.example.pawel.orlikapp.ui.menu.bookingdetails.BookingDetailsView;
import com.example.pawel.orlikapp.utils.Time;
import com.squareup.picasso.Picasso;

import java.util.Optional;

public class CreateBookingActivity extends AppCompatActivity {

    private Booking booking;

    private EditText maxPlayersEditText;
    private TextView nameTextView;
    private TextView addressTextView;
    private TextView dateBookingTextView;
    private TextView timeBookingTextView;
    private RadioButton onlyMeRadioButton;
    private RadioButton everyoneRadioButton;
    private ImageView playgroundPhoto;

    private Button createBooking;
    private CreateBookingPresenter createBookingPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_booking);
        Intent intent = getIntent();
        booking = (Booking) intent.getSerializableExtra("booking");
        init();
        initializeBookingScreen();
        onClick();

    }

    private void init() {
        createBookingPresenter = new CreateBookingPresenter();
        maxPlayersEditText = (EditText) findViewById(R.id.maxPlayersEditText);
        nameTextView = (TextView) findViewById(R.id.nameTextView);
        addressTextView = (TextView) findViewById(R.id.addresTextView);
        dateBookingTextView = (TextView) findViewById(R.id.dateBookingTextView);
        timeBookingTextView = (TextView) findViewById(R.id.timeBookingTextView);
        onlyMeRadioButton = (RadioButton) findViewById(R.id.onlyMeRadioButton);
        everyoneRadioButton = (RadioButton) findViewById(R.id.everyoneRadioButton);
        createBooking = (Button) findViewById(R.id.bookingBtn);
        playgroundPhoto = (ImageView) findViewById(R.id.playgroundPhoto);

    }

    private void initializeBookingScreen() {
//        playgroundPhoto.setBackground(new BitmapDrawable(getApplicationContext().getResources(),ImageHelper.convertStringToBitmap(booking.getPlayground().getPhoto())));
        Optional<Picture> picture = Optional.ofNullable(booking.getPlayground().getPicture());
        picture.ifPresent(picture1 -> Picasso.with(this).load(ServiceGenerator.BASE_URL_IMAGE + picture1.getId()).fit().into(playgroundPhoto));

        everyoneRadioButton.setChecked(true);
        nameTextView.setText(booking.getPlayground().getName());
        addressTextView.setText(booking.getPlayground().getAddres());
        dateBookingTextView.setText(booking.getDate());
        Time startTime = new Time(booking.getStartOrderHour(), booking.getStartOrderMinutes());
        Time endTime = new Time(booking.getEndOrderHour(), booking.getEndOrderMinutes());
        timeBookingTextView.setText(startTime.displayTime() + " - " + endTime.displayTime());

    }

    private void onClick() {
        createBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBooking();
                createBookingPresenter.addBooking(booking, addBookingListener);
            }
        });
    }

    private void setBooking() {
        booking.setLeaderName(PreferencesShared.onReadString(PreferencesSharedKyes.username));
        booking.setAvailable(checkedRadioButton());
        booking.setMaxNumberOfPlayer(Integer.valueOf(maxPlayersEditText.getText().toString()));
    }

    private boolean checkedRadioButton() {
        if (everyoneRadioButton.isChecked())
            return true;
        else
            return false;
    }

    CreateBookingPresenter.AddBookingListener addBookingListener = new CreateBookingPresenter.AddBookingListener() {
        @Override
        public void onSucces() {
            Toast.makeText(CreateBookingActivity.this, "Pomyslnie dodano rezerwacje", Toast.LENGTH_SHORT).show();
            finish();
        }
    };

}
