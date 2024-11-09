package com.example.myapplication;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView questionText, scoreText, timerText;
    private Button option1, option2, option3, option4;
    private int score = 0;
    private int currentQuestionIndex = 0;
    private List<Question> questionList = new ArrayList<>();
    private String topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Get topic from Intent
        topic = getIntent().getStringExtra("TOPIC");

        // Initialize UI components
        questionText = findViewById(R.id.questionText);
        scoreText = findViewById(R.id.scoreText);
        timerText = findViewById(R.id.timerText);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        // Hardcoded questions based on topic
        loadQuestions();

        // Load the first question
        loadQuestion(currentQuestionIndex);

        // Set answer selection logic
        option1.setOnClickListener(v -> checkAnswer(option1));
        option2.setOnClickListener(v -> checkAnswer(option2));
        option3.setOnClickListener(v -> checkAnswer(option3));
        option4.setOnClickListener(v -> checkAnswer(option4));
    }

    private void loadQuestions() {
        // Hardcoded questions for each topic
        if ("Nature".equals(topic)) {
            questionList.add(new Question("What is the tallest mountain on Earth?", "Mount Everest", "K2", "Kangchenjunga", "Mount Kilimanjaro", "Mount Everest"));
            questionList.add(new Question("What is the largest ocean?", "Atlantic", "Indian", "Arctic", "Pacific", "Pacific"));
        } else if ("Science".equals(topic)) {
            questionList.add(new Question("What is the chemical symbol for water?", "H2O", "O2", "CO2", "H2O2", "H2O"));
            questionList.add(new Question("What planet is known as the Red Planet?", "Earth", "Mars", "Jupiter", "Venus", "Mars"));
        } else if ("Computer Science".equals(topic)) {
            questionList.add(new Question("What does CPU stand for?", "Central Processing Unit", "Central Power Unit", "Computer Processing Unit", "Central Processor Unit", "Central Processing Unit"));
            questionList.add(new Question("Who is known as the father of computers?", "Charles Babbage", "Alan Turing", "John von Neumann", "Bill Gates", "Charles Babbage"));
        }
    }

    private void loadQuestion(int index) {
        if (index < questionList.size()) {
            Question question = questionList.get(index);
            questionText.setText(question.getQuestion());
            option1.setText(question.getOption1());
            option2.setText(question.getOption2());
            option3.setText(question.getOption3());
            option4.setText(question.getOption4());
            startTimer();
        } else {
            Toast.makeText(this, "Quiz Finished! Your Score: " + score, Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void startTimer() {
        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerText.setText("Time left: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                currentQuestionIndex++;
                loadQuestion(currentQuestionIndex);
            }
        }.start();
    }

    private void checkAnswer(Button selectedOption) {
        Question currentQuestion = questionList.get(currentQuestionIndex);
        if (selectedOption.getText().toString().equals(currentQuestion.getCorrectAnswer())) {
            score++;
            scoreText.setText("Score: " + score);
        }
        currentQuestionIndex++;
        loadQuestion(currentQuestionIndex);
    }
}
