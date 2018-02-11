package com.example.pawel.orlikapp.ui.create_player;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.Player;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.ui.base.BaseActivity;

public class CreatePlayerActivity extends BaseActivity implements CreatePlayerView {
    private CreatePlayerPresenter createPlayerPresenter;
    ImageView imageView;
    Button button;
    String token;
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
//        createPlayerPresenter.createPlayer(new Player("Pawel", "Sosnowka","test@gmail.com"),token);
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
//        imageView = findViewById(R.id.imageCamera);
//        button = findViewById(R.id.galeryBtn);
    }

    @Override
    public void onButtonClick() {
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                selectImg();
//            }
//        });
    }

    @Override
    public void setPresenter() {
        createPlayerPresenter = new CreatePlayerPresenter(this);
    }

    @Override
    public void onPlayerCreate() {
        Toast.makeText(this, "Dodano Gracza", Toast.LENGTH_SHORT).show();
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
}
