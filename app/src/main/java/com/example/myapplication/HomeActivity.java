package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private Button natureButton, scienceButton, csButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        natureButton = findViewById(R.id.natureButton);
        scienceButton = findViewById(R.id.scienceButton);
        csButton = findViewById(R.id.csButton);

        natureButton.setOnClickListener(v -> startQuiz("Nature"));
        scienceButton.setOnClickListener(v -> startQuiz("Science"));
        csButton.setOnClickListener(v -> startQuiz("Computer Science"));
    }

    private void startQuiz(String topic) {
        Intent intent = new Intent(HomeActivity.this, QuizActivity.class);
        intent.putExtra("TOPIC", topic);
        startActivity(intent);
    }
}
