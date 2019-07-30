package com.mezcla.chai.cocktails.views.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mezcla.chai.cocktails.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private TextView Register;
    private Button Login;
    private EditText Password;
    private EditText Email;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_login);
        Register = (TextView) findViewById(R.id.txtRegister);


        mAuth = FirebaseAuth.getInstance();

        final Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        Login = (Button) findViewById(R.id.btnLogin);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
    }


    private void userLogin(){
        Password = (EditText) findViewById(R.id.txtPassword);
        Email = (EditText)findViewById(R.id.txtEmail);
        String s1 = Email.getText().toString().trim(); //Eliminate leading and trailing spaces
        String s2 = Password.getText().toString().trim();
        final Intent intent = new Intent(LoginActivity.this, MainActivity.class);

//        if (isValidPassword(s2)) {
//            Toast.makeText(RegistrationActivity.this, "Valid Password", Toast.LENGTH_SHORT).show();
//            if (s2.equals(s3)) {
//                Toast.makeText(RegistrationActivity.this, "Passwords match", Toast.LENGTH_SHORT).show();
        mAuth.signInWithEmailAndPassword(s1, s2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(intent);

                } else {
                    Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
