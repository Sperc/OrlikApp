package com.example.pawel.orlikapp.ui.create_player;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.Player;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.ui.base.BaseActivity;
import com.example.pawel.orlikapp.ui.login.LoginActivity;
import com.example.pawel.orlikapp.ui.select_city.SelectCityActicity;
import com.example.pawel.orlikapp.utils.ImageHelper;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class CreatePlayerActivity extends BaseActivity implements CreatePlayerView {
    private CreatePlayerPresenter createPlayerPresenter;
    ImageView imageView;
    Button cameraBtn;
    Button galeryBtn;
    Button createPlayer;
    EditText firstName;
    EditText lastName;
    EditText birthDay;


    private String token;
    private static final int CAMERA_REQUEST = 1888;
    private static final int SELECT_FILE = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_player);
        Intent intent = getIntent();
        token = intent.getExtras().getString("token");
        setPresenter();
        initialize();
        onButtonClick();
    }


    private void selectImg() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent.createChooser(intent, "SelectFile"), SELECT_FILE);
    }

    private void getCamera() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        } else if (requestCode == SELECT_FILE) {
            try {
                Uri selectImageUri = data.getData();
                imageView.setImageURI(selectImageUri);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void initialize() {
        imageView = (ImageView) findViewById(R.id.imageView);
        cameraBtn = (Button) findViewById(R.id.cameraBtn);
        galeryBtn = (Button) findViewById(R.id.galeryBtn);
        createPlayer = (Button) findViewById(R.id.createBtn);
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        birthDay = (EditText) findViewById(R.id.birthDay);


    }

    @Override
    public void onButtonClick() {
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCamera();
            }
        });
        galeryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImg();
            }
        });
        createPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Player player = new Player();
                player.setFirstName(firstName.getText().toString());
                player.setLastName(lastName.getText().toString());
                player.setBirthDate(birthDay.getText().toString());
                imageView.buildDrawingCache();
                createPlayerPresenter.createPlayer(player, token,ImageHelper.convertImageToString(imageView));

            }
        });
    }

    @Override
    public void setPresenter() {
        createPlayerPresenter = new CreatePlayerPresenter(this);
    }

    @Override
    public void onPlayerCreate() {
        Toast.makeText(this, "Dodano Gracza", Toast.LENGTH_SHORT).show();
        PreferencesShared.onStoreData(PreferencesSharedKyes.token,token);
        Intent intent = new Intent(getApplicationContext(), SelectCityActicity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onConntectionError() {
        Toast.makeText(this, getString(R.string.connectionError), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void badRequest() {
        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void unuthorized() {
        Toast.makeText(this, getString(R.string.authorizationProblem), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void emptyFirstName() {
        firstName.setError(getString(R.string.emptyBox));
    }

    @Override
    public void emptyLastName() {
        lastName.setError(getString(R.string.emptyBox));
    }

    @Override
    public void emptyDate() {
        birthDay.setError(getString(R.string.emptyBox));
    }

    @Override
    public void incrrectDate() {
        birthDay.setError(getString(R.string.badDataInput));
    }
}
