package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView scoreText, percentageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Initialize the TextViews
        scoreText = findViewById(R.id.scoreText);
        percentageText = findViewById(R.id.percentageText);

        // Get the score passed from QuizActivity
        int score = getIntent().getIntExtra("SCORE", 0);

        // Set the score text
        scoreText.setText("Your Score: " + score);

        // Calculate the percentage (total score divided by 5)
        int percentage = (score * 100) / 5;

        // Set the percentage text
        percentageText.setText("Percentage: " + percentage + "%");
    }
}
