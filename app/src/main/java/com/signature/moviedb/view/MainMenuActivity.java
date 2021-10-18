package com.signature.moviedb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.signature.moviedb.R;

public class MainMenuActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    NavHostFragment navHostFragment;
    //NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        bottomNavigationView = findViewById(R.id.bottom_nav_main_menu);
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_fragment_main_menu);
        //navController = Navigation.findNavController(this,R.id.nav_fragment_main_menu);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.
                nowPlayingFragment, R.id.upcomingFragment).build();
        NavigationUI.setupActionBarWithNavController(MainMenuActivity.this,
                navHostFragment.getNavController(), appBarConfiguration);

        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.getNavController());
    }
}