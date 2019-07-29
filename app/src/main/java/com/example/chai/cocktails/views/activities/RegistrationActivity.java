package com.example.chai.cocktails.views.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chai.cocktails.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText Username;
    private EditText Password;
    private EditText ConfirmPassword;
    private EditText Email;
    private Button Register;
    private TextView LoggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();

        Register = (Button) findViewById(R.id.btnRegister);
        LoggedIn = (TextView) findViewById(R.id.txtLogin);

        final Intent intent = new Intent(this, LoginActivity.class);
        LoggedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser(v);
            }
        });
    }

    public boolean isValidPassword(String password) {
        Pattern passwordPattern;
        Matcher passwordMatcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=]).{8,16}$";

        passwordPattern = Pattern.compile(PASSWORD_PATTERN);
        passwordMatcher = passwordPattern.matcher(password);

        return passwordMatcher.matches();
    }

    private void registerUser(View v) {
        final EditText Password = (EditText) findViewById(R.id.txtRegPassword);
        final EditText Email = (EditText) findViewById(R.id.txtRegEmail);
        final EditText ConfirmPassword = (EditText) findViewById(R.id.txtRegCPassword);

        final Intent intent = new Intent(RegistrationActivity.this, ProfileActivity.class);

        String s1 = Email.getText().toString().trim(); //Eliminate leading and trailing spaces
        String s2 = Password.getText().toString().trim();
        String s3 = ConfirmPassword.getText().toString().trim();

        if (isValidPassword(s2)) {
            Toast.makeText(RegistrationActivity.this, "Valid Password", Toast.LENGTH_SHORT).show();
            if (s2.equals(s3)) {
                Toast.makeText(RegistrationActivity.this, "Passwords match", Toast.LENGTH_SHORT).show();
                mAuth.createUserWithEmailAndPassword(s1, s2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegistrationActivity.this, "You have registered successfully", Toast.LENGTH_SHORT).show();
                            startActivity(intent);

                        } else {
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(RegistrationActivity.this, "You are already registered", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(RegistrationActivity.this, "Invalid email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            } else {
                Toast.makeText(RegistrationActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(RegistrationActivity.this, "Please enter a password between 8-16 characters with at least ONE lowercase and uppercase character, ONE digit, and ONE special character", Toast.LENGTH_LONG).show();
        }
    }
}
