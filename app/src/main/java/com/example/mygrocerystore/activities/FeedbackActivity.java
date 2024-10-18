package com.example.mygrocerystore.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mygrocerystore.R;

public class FeedbackActivity extends AppCompatActivity {

    private EditText firstNameInput, lastNameInput, feedbackInput;
    private RatingBar ratingBar;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        // Initialize views
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

                // Validation
                if (firstName.isEmpty() || lastName.isEmpty() || feedback.isEmpty()) {
                    Toast.makeText(FeedbackActivity.this, "Error!Try Again", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FeedbackActivity.this, "Feedback saved successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
