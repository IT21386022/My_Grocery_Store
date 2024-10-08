package com.example.mygrocerystore.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mygrocerystore.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FeedbackActivity extends AppCompatActivity {

    private EditText firstNameInput, lastNameInput, feedbackInput;
    private RatingBar ratingBar;
    private Button submitButton;

    // Firestore instance
    private FirebaseFirestore db;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        firstNameInput = findViewById(R.id.first_name_input);
        lastNameInput = findViewById(R.id.last_name_input);
        feedbackInput = findViewById(R.id.feedback_input);
        ratingBar = findViewById(R.id.ratingBar);
        submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input data
                String firstName = firstNameInput.getText().toString();
                String lastName = lastNameInput.getText().toString();
                String feedback = feedbackInput.getText().toString();
                int rating = (int) ratingBar.getRating();

                // Validation (optional)
                if (firstName.isEmpty() || lastName.isEmpty() || feedback.isEmpty()) {
                    Toast.makeText(FeedbackActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Prepare data to save
                Map<String, Object> feedbackData = new HashMap<>();
                feedbackData.put("FirstName", firstName);
                feedbackData.put("LastName", lastName);
                feedbackData.put("CustomerFeedbackText", feedback);
                feedbackData.put("Rating", rating);
                feedbackData.put("userId", auth.getCurrentUser().getUid());

                // Save data to Firestore
                db.collection("CustomerFeedbacks")
                        .add(feedbackData)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(FeedbackActivity.this, "Feedback submitted successfully", Toast.LENGTH_SHORT).show();

                                // Clear inputs after submission
                                firstNameInput.setText("");
                                lastNameInput.setText("");
                                feedbackInput.setText("");
                                ratingBar.setRating(0);
                            } else {
                                Toast.makeText(FeedbackActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

}