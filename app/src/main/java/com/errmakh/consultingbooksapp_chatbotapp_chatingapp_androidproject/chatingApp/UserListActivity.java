package com.errmakh.consultingbooksapp_chatbotapp_chatingapp_androidproject.chatingApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.errmakh.consultingbooksapp_chatbotapp_chatingapp_androidproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class UserListActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ImageButton searchButton;

    ChatFragment chatFragment;
    ProfileFragment profileFragment;
    UserListFragment userListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        chatFragment = new ChatFragment();
        profileFragment = new ProfileFragment();
        userListFragment=new UserListFragment();
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        searchButton = findViewById(R.id.main_search_btn);

        searchButton.setOnClickListener((v)->{
            startActivity(new Intent(UserListActivity.this,SearchUserActivity.class));
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.menu_chat){
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout,chatFragment).commit();
                }
                if(item.getItemId()==R.id.menu_profile){
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout,profileFragment).commit();
                }
                if(item.getItemId()==R.id.menu_users){
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout,userListFragment).commit();
                }
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.menu_chat);

        getFCMToken();

    }

    void getFCMToken(){
//        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
//            if(task.isSuccessful()){
//                String token = task.getResult();
//                FirebaseUtil.currentUserDetails().update("fcmToken",token);
//
//            }
//        });
    }
}