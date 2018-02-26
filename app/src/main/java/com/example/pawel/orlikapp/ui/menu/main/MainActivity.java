package com.example.pawel.orlikapp.ui.menu.main;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.Player;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.ui.base.BaseActivity;
import com.example.pawel.orlikapp.ui.login.LoginActivity;
import com.example.pawel.orlikapp.ui.login.LoginPresenter;
import com.example.pawel.orlikapp.ui.menu.find_playground.FindPlaygroundFragment;
import com.example.pawel.orlikapp.ui.menu.my_profile.MyProfileFragment;
import com.example.pawel.orlikapp.ui.menu.settings.SettingsFragment;
import com.example.pawel.orlikapp.ui.menu.myteams.MyTeamsFragment;
import com.example.pawel.orlikapp.ui.menu.my_reservation.MyReservationFragment;
import com.example.pawel.orlikapp.ui.menu.team.TeamFragment;
import com.example.pawel.orlikapp.ui.select_city.SelectCityActicity;
import com.example.pawel.orlikapp.utils.ConstansValues;
import com.example.pawel.orlikapp.utils.ImageHelper;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.drawer)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_action)
    Toolbar toolbar;

    TextView actualCity;

    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private LinearLayout containerHeader;

    private TextView playerName;
    private TextView playerEmail;
    private CircleImageView playerPhoto;

    private MainActivityPresenter mainActivityPresenter;

    private static int TOOLBAR_MARGIN_TITLE = 220;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initialize();
        setSupportActionBar(toolbar);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setUpDrawerContent(navigationView);
        onButtonClick();
        openStartFragment();
        setPlayerDetails();
    }

    @Override
    public void initialize() {
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        navigationView = (NavigationView) findViewById(R.id.navigationDrawer);
        View headerLayout = navigationView.getHeaderView(0); //0-index header
        containerHeader = headerLayout.findViewById(R.id.containerCity);
        actualCity = headerLayout.findViewById(R.id.actualCity);

        playerEmail = headerLayout.findViewById(R.id.userEmailTextView);
        playerName = headerLayout.findViewById(R.id.nameOfUser);
        playerPhoto = headerLayout.findViewById(R.id.playerPhoto);

        actualCity.setText(PreferencesShared.onReadString(PreferencesSharedKyes.city));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitleMarginStart(TOOLBAR_MARGIN_TITLE);


    }

    @Override
    public void onButtonClick() {
        containerHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStartActivity(SelectCityActicity.class, true);
            }
        });
    }

    @Override
    public void setPresenter() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void selectItemDrawer(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass = null;
        switch (menuItem.getItemId()) {
            case R.id.logout:
                logout();
                return;
            case R.id.search:
                fragmentClass = FindPlaygroundFragment.class;
                break;
            case R.id.settings:
                fragmentClass = SettingsFragment.class;
                break;
            case R.id.reservation:
                fragmentClass = MyReservationFragment.class;
                break;
            case R.id.myprofile:
                fragmentClass = MyProfileFragment.class;
                break;
            default:
                fragmentClass = FindPlaygroundFragment.class;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flcontent, fragment).commit();

        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());

        drawerLayout.closeDrawers();
    }

    private void setUpDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return true;
            }
        });
    }

    private void logout() {
        PreferencesShared.onDeleteString(PreferencesSharedKyes.token);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void openStartFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        FindPlaygroundFragment fragment = new FindPlaygroundFragment();
        setTitle("Strona Główna");
        ft.replace(R.id.flcontent, fragment).commit();
    }

    private void setPlayerDetails() {
        MainActivityPresenter mainActivityPresenter = new MainActivityPresenter();
        mainActivityPresenter.getActualPlayer(new LoginPresenter.ActualPlayerListener() {
            @Override
            public void succes(Player player) {
                playerEmail.setText(player.getUsername());
                playerName.setText(player.toString());

//                Picasso.with(getApplicationContext()).load("http://192.168.0.185:8080/picture/get/3")
                Picasso.with(getApplicationContext()).load(ConstansValues.BASE_IMG_URL + player.getPicture().getId())
                        .placeholder(R.drawable.draw_person)
                        .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                        .into(playerPhoto);

//                if (player.getImage() != null) {
//                    playerPhoto.setImageBitmap(ImageHelper.convertStringToBitmap(player.getImage()));
//                }
            }

            @Override
            public void notFound() {
                Toast.makeText(MainActivity.this, "Problem z internemtem/serwerem", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
