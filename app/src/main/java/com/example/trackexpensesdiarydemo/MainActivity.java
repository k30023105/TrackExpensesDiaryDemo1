package com.example.trackexpensesdiarydemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import com.example.trackexpensesdiarydemo.dao.UserDao;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.example.trackexpensesdiarydemo.ui.home.HomeFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trackexpensesdiarydemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private AppBarConfiguration mAppBarConfiguration;
    SharedPreferences memberDataPre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Uri uri = Uri.parse("mailto:k30023105@gmail.com");
                    Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
                    PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(),0);
                    String subject = getString(R.string.app_name) + "(" +packageInfo.versionName + ")";
                    intent.putExtra(Intent.EXTRA_SUBJECT,subject);
                    startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "沒有可撰寫郵件的APP", Toast.LENGTH_SHORT).show();
                }
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_trackexpenses, R.id.nav_diary)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_logout:
                AlertDialog.Builder logoutbtn = new AlertDialog.Builder(this);
                logoutbtn.setTitle("登出");
                logoutbtn.setMessage("確定要登出嗎?");
                logoutbtn.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        memberDataPre = getSharedPreferences("memberDataPre",MODE_PRIVATE);
                        SharedPreferences.Editor editor = memberDataPre.edit();
                        editor.remove("nickname");
                        editor.remove("useremail");
                        editor.apply();
                        Intent intent = new Intent(MainActivity.this,HomeFragment.class);
                        startActivity(intent);
                    }
                });
        logoutbtn.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = logoutbtn.create();
        dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}