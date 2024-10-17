package com.example.mygrocerystore.activities;

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
import com.example.mygrocerystore.api.AuthApi;
import com.example.mygrocerystore.client.ApiClient;
import com.example.mygrocerystore.client.RetrofitClient;
import com.example.mygrocerystore.api.ApiService;
import com.example.mygrocerystore.models.LoginModel;
import com.example.mygrocerystore.models.UserModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button signIn;
    EditText email, password;
    TextView signUp;
    FirebaseAuth auth;
    FirebaseDatabase db;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        auth = FirebaseAuth.getInstance();
//        db = FirebaseDatabase.getInstance();
        signIn = findViewById(R.id.login_btn);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        email = findViewById(R.id.email_login);
        password = findViewById(R.id.password_login);
        signUp = findViewById(R.id.sign_up);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }


    private void loginUser() {
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();

        if (TextUtils.isEmpty(userEmail)) {
            Toast.makeText(this, "Email is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userPassword)) {
            Toast.makeText(this, "Password is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (userPassword.length() < 6) {
            Toast.makeText(this, "Password Length must be greater than 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        // Show progress bar
        progressBar.setVisibility(View.VISIBLE);

        // Query the Firebase Realtime Database to check if the email exists
//        FirebaseDatabase.getInstance().getReference().child("Users")
//                .orderByChild("email").equalTo(userEmail)
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        // If snapshot exists, email exists in the database
//                        if (snapshot.exists()) {
//                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
//                                String storedPassword = userSnapshot.child("password").getValue(String.class);
//
//                                // Check if the password matches
//                                if (storedPassword != null && storedPassword.equals(userPassword)) {
//                                    // If email and password match, proceed with Firebase Authentication
//                                    auth.signInWithEmailAndPassword(userEmail, userPassword)
//                                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                                                @Override
//                                                public void onComplete(@NonNull Task<AuthResult> task) {
//                                                    progressBar.setVisibility(View.GONE);
//
//                                                    if (task.isSuccessful()) {
//                                                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
//                                                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//                                                        startActivity(intent);
//                                                        finish();  // Close LoginActivity
//                                                    } else {
//                                                        Toast.makeText(LoginActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                                                    }
//                                                }
//                                            });
//                                } else {
//                                    // Password mismatch
//                                    progressBar.setVisibility(View.GONE);
//                                    Toast.makeText(LoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        } else {
//                            // Email does not exist in the database
//                            progressBar.setVisibility(View.GONE);
//                            Toast.makeText(LoginActivity.this, "Email not registered", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        progressBar.setVisibility(View.GONE);
//                        Toast.makeText(LoginActivity.this, "Database Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });

        // Make API call using Retrofit
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        LoginModel loginModel = new LoginModel(userEmail, userPassword);

        apiService.loginUser(loginModel).enqueue(new Callback<LoginModel>() {
//            @Override
//            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
//                progressBar.setVisibility(View.GONE);
//                if (response.isSuccessful()) {
//                    LoginModel loggedInUser = response.body();
//                    if (loggedInUser != null) {
//                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//                        startActivity(intent);
//                        finish();  // Close LoginActivity
//                    }
//                } else {
//                    Toast.makeText(LoginActivity.this, "Login Failed: " + response.message(), Toast.LENGTH_SHORT).show();
//                }
//            }

            @Override 
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                progressBar.setVisibility(View.GONE);  
                // Hide progress bar 
                if(response.isSuccessful()) { 
                    LoginModel loggedInUser =response.body(); 
                    if (loggedInUser != null) { 
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show(); 
                        // Navigate to HomeActivity on successful login 
                        navigateToHome(); 
                    } else { 
                        Toast.makeText(LoginActivity.this, "Login Failed: Empty Response", Toast.LENGTH_SHORT).show(); 
                    } 
                } else { 
                    // Log the error message for better debugging 
                    Toast.makeText(LoginActivity.this, "Login Failed: " + response.message(), Toast.LENGTH_SHORT).show(); 
                } 
            }
            @Override 
            public void onFailure(Call<LoginModel> call, Throwable t) { 
                progressBar.setVisibility(View.GONE);  
                // Hide progress bar 
                Toast.makeText(LoginActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show(); 
            }

           
            
        });
    }

    private void navigateToHome() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}

