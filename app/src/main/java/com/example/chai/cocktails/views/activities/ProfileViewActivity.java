package com.example.chai.cocktails.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chai.cocktails.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileViewActivity extends AppCompatActivity {

    private TextView navDisplayName;
    private TextView navUserEmail;
    private ImageView navUserPhoto;
    private Button editButton;

    FirebaseAuth mAuth;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileview);

        // initialize FB, obtain an instance
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();


        navDisplayName = findViewById(R.id.navDisplayName);
        navUserEmail = findViewById(R.id.navUserEmail);
        navUserPhoto = findViewById(R.id.navUserPhoto);
        editButton = findViewById(R.id.btnEdit);

        final Intent intent  = new Intent(ProfileViewActivity.this, ProfileActivity.class); //When clicked on Edit Button, it will redirect the user to edit profile page
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

        updateNavHeader();
    }

    public void updateNavHeader(){ //Update the header once the user has successfully picked a display name and picture

        String s1 = currentUser.getEmail(); //Convert the email input into string
        String s2 = currentUser.getDisplayName(); //Convert the name input into string

        navUserEmail.setText(s1);
        navDisplayName.setText(s2);

        //Use Glide to import the user image from FB Storage
        Glide.with(this).load(currentUser.getPhotoUrl().toString()).into(navUserPhoto);

    }
}
