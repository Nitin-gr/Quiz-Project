package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizActivity extends AppCompatActivity {

    private TextView questionTextView;
    private List<String> questions;
    private int currentQuestionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionTextView = findViewById(R.id.questionTextView);

        String topic = getIntent().getStringExtra("TOPIC");
        loadQuestions(topic);
        showQuestion();
    }

    private void loadQuestions(String topic) {
        Map<String, List<String>> questionBank = new HashMap<>();
        questionBank.put("Nature", List.of("What is the tallest tree?", "What is the largest animal?"));
        questionBank.put("Science", List.of("What is the chemical symbol for water?", "What planet is known as the Red Planet?"));
        questionBank.put("Computer Science", List.of("What is RAM?", "What does CPU stand for?"));

        questions = questionBank.getOrDefault(topic, new ArrayList<>());
    }

    private void showQuestion() {
        if (currentQuestionIndex < questions.size()) {
            questionTextView.setText(questions.get(currentQuestionIndex));
            currentQuestionIndex++;
        }
    }
}