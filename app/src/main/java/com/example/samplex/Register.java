package com.example.samplex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    EditText emaiId, password,mName,mPhone;
    Button btnSignUp;
    TextView tvSignIn;
    FirebaseAuth mFirebaseAuth;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emaiId = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        btnSignUp = findViewById(R.id.button);
        tvSignIn = findViewById(R.id.textView3);
        mName = findViewById(R.id.fullName);
        mPhone = findViewById(R.id.phoneNumber);
        progressBar = findViewById(R.id.progressBar);
        mFirebaseAuth = FirebaseAuth.getInstance();


        btnSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String email = emaiId.getText().toString();
                String pwd = password.getText().toString();
                String name = mName.getText().toString();
                String phone = mPhone.getText().toString();

                if(email.isEmpty()){
                    emaiId.setError("Email is required");
                    emaiId.requestFocus();

                }
                if(pwd.isEmpty()){
                    password.setError("password is required");
                    password.requestFocus();
                }if(pwd.length()<6){
                    password.setError("password must be >= 6 characters");
                    password.requestFocus();
                }if(name.isEmpty()){
                    mName.setError("Name is required");
                    mName.requestFocus();
                }
                if(!(phone.length()==10)){
                    mPhone.setError("It must contain 10 digits ");
                    mPhone.requestFocus();
                }



                if(email.isEmpty() && pwd.isEmpty() && name.isEmpty() && phone.isEmpty()){
                    Toast.makeText(Register.this,"Fields are Empty!",Toast.LENGTH_SHORT).show();

                }
                progressBar.setVisibility(View.VISIBLE);
                if(!(email.isEmpty() && pwd.isEmpty() && name.isEmpty() && phone.isEmpty() )){
                    mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(Register.this,"Sign up unsuccessful, Please connect your device with internet and try again",Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }else{
                                Toast.makeText(Register.this, "Register Successful", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Register.this, MainActivity.class);
                                startActivity(i);

                            }
                        }
                    });
                }
                else{
                    Toast.makeText(Register.this,"Error occurred!",Toast.LENGTH_SHORT).show();

                }

            }
        }
        );
        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });


    }


}