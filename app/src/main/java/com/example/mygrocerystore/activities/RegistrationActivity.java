package com.example.mygrocerystore.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mygrocerystore.R;
import com.example.mygrocerystore.client.RetrofitClient;
import com.example.mygrocerystore.api.AuthApi;
import com.example.mygrocerystore.models.UserModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import retrofit2.Call;

public class RegistrationActivity extends AppCompatActivity {

    Button signUp;
    EditText firstname,lastname, email, password,phone,role;
    TextView signIn;
    FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        signUp = findViewById(R.id.reg_btn);
        firstname = findViewById(R.id.firstname_reg);
        lastname = findViewById(R.id.lastname_reg);
        email = findViewById(R.id.email_reg);
        password = findViewById(R.id.password_reg);
        phone = findViewById(R.id.phone_reg);
        role = findViewById(R.id.role_reg);
        signIn = findViewById(R.id.sign_in);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
                progressBar.setVisibility(View.VISIBLE);

            }
        });

    }

    private void createUser() {
        String userFirstName = firstname.getText().toString();
        String userLastName = lastname.getText().toString();
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();
        String userPhone = phone.getText().toString();
        String userRole = role.getText().toString();

        if(TextUtils.isEmpty(userFirstName)){
            Toast.makeText(this,"First Name is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userLastName)){
            Toast.makeText(this,"Last Name is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userEmail)){
            Toast.makeText(this,"Email is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userPassword)){
            Toast.makeText(this,"Password is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userPhone)){
            Toast.makeText(this,"Phone is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (userPassword.length()<6){
            Toast.makeText(this,"Password Length must be greater than 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        //create user
//        auth.createUserWithEmailAndPassword(userEmail,userPassword)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(task.isSuccessful()){
//                            UserModel userModel = new UserModel(userName,userEmail,userPassword);
//                            String id = task.getResult().getUser().getUid();
//                            database.getReference().child("Users").child(id).setValue(userModel);
//                            progressBar.setVisibility(View.GONE);
//                            Toast.makeText(RegistrationActivity.this,"Registration Successful", Toast.LENGTH_SHORT).show();
//                        }
//                        else{
//                            progressBar.setVisibility(View.GONE);
//                            Toast.makeText(RegistrationActivity.this,"Error"+task.getException(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });

        progressBar.setVisibility(View.VISIBLE);

        // Create user model
        UserModel userModel = new UserModel(userFirstName,userLastName,userEmail,userPassword,userPhone,userRole);

        // Retrofit instance
        AuthApi authApi = RetrofitClient.getRetrofitInstance().create(AuthApi.class);

        authApi.registerUser(userModel).enqueue(new retrofit2.Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, retrofit2.Response<UserModel> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegistrationActivity.this, "Registration Failed: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(RegistrationActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}