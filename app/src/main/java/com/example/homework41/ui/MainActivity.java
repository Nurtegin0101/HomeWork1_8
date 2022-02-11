package com.example.homework41.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.homework41.R;
import com.example.homework41.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initNavController();
        navView = findViewById(R.id.nav_view);
        }
    private void initNavController() {
         AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            navController.navigate(R.id.autFragment);
        }

        if (!App.prefs.isBoardShown()) {
            //Открытие board fragment;
            navController.navigate(R.id.boardFragment);
            App.prefs.saveBoardState();
        }
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(R.id.navigation_home);
            list.add(R.id.navigation_dashboard);
            list.add(R.id.navigation_notifications);
            list.add(R.id.navigation_profile);

            if (list.contains(destination.getId())) {
                binding.navView.setVisibility(View.VISIBLE);
            } else {
                binding.navView.setVisibility(View.GONE);
            }
        });
    }
}