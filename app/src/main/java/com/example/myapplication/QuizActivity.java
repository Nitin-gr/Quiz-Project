package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView questionText, timerText, scoreText;
    private Button option1, option2, option3, option4;
    private ProgressBar progressBar;
    private List<Question> questions = new ArrayList<>();
    private int currentQuestionIndex = 0;
    private int score = 0;
    private CountDownTimer countDownTimer;
    private String selectedTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        selectedTopic = getIntent().getStringExtra("TOPIC");

        questionText = findViewById(R.id.questionText);
        timerText = findViewById(R.id.timerText);
        scoreText = findViewById(R.id.scoreText);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        progressBar = findViewById(R.id.progressBar);

        initializeQuestions(selectedTopic);
        displayQuestion(currentQuestionIndex);

        // Set onClick listeners for all options
        option1.setOnClickListener(v -> handleAnswerSelection(option1));
        option2.setOnClickListener(v -> handleAnswerSelection(option2));
        option3.setOnClickListener(v -> handleAnswerSelection(option3));
        option4.setOnClickListener(v -> handleAnswerSelection(option4));
    }

    private void initializeQuestions(String topic) {
        // Initialize questions based on the selected topic
        if ("Nature".equalsIgnoreCase(topic)) {
            questions.add(new Question("What is the largest animal on Earth?", "Blue Whale", "Elephant", "Shark", "Giraffe", "Blue Whale"));
            questions.add(new Question("What is the fastest land animal?", "Cheetah", "Lion", "Tiger", "Elephant", "Cheetah"));
            questions.add(new Question("What is the tallest tree?", "Sequoia", "Pine", "Oak", "Maple", "Sequoia"));
            questions.add(new Question("What is the longest river in the world?", "Amazon River", "Nile River", "Yangtze River", "Ganges River", "Amazon River"));
            questions.add(new Question("What is the most abundant gas in Earth's atmosphere?", "Nitrogen", "Oxygen", "Carbon Dioxide", "Hydrogen", "Nitrogen"));
        } else if ("Science".equalsIgnoreCase(topic)) {
            questions.add(new Question("What is the chemical symbol for water?", "H2O", "O2", "CO2", "H2", "H2O"));
            questions.add(new Question("What is the atomic number of hydrogen?", "1", "2", "3", "4", "1"));
            questions.add(new Question("What is the center of an atom called?", "Nucleus", "Electron", "Proton", "Neutron", "Nucleus"));
            questions.add(new Question("What force keeps us on the ground?", "Gravity", "Magnetism", "Friction", "Inertia", "Gravity"));
            questions.add(new Question("What planet is closest to the Sun?", "Mercury", "Venus", "Earth", "Mars", "Mercury"));
        } else if ("Computer Science".equalsIgnoreCase(topic)) {
            questions.add(new Question("What does CPU stand for?", "Central Processing Unit", "Computer Processing Unit", "Central Processor Unit", "Central Programming Unit", "Central Processing Unit"));
            questions.add(new Question("What language is used to build Android apps?", "Java", "C", "C++", "Python", "Java"));
            questions.add(new Question("What does HTML stand for?", "HyperText Markup Language", "Hyper Transfer Markup Language", "HyperText Machine Language", "Hyper Transport Markup Language", "HyperText Markup Language"));
            questions.add(new Question("Who invented the first computer?", "Charles Babbage", "Alan Turing", "John von Neumann", "Bill Gates", "Charles Babbage"));
            questions.add(new Question("What is the primary function of an operating system?", "Manage hardware and software", "Run applications", "Provide security", "Manage the internet", "Manage hardware and software"));
        }
    }

    private void displayQuestion(int index) {
        if (index < questions.size()) {
            Question currentQuestion = questions.get(index);
            questionText.setText(currentQuestion.getQuestion());
            option1.setText(currentQuestion.getOption1());
            option2.setText(currentQuestion.getOption2());
            option3.setText(currentQuestion.getOption3());
            option4.setText(currentQuestion.getOption4());

            // Reset the progress bar and start the timer for the new question
            startTimer();

            // Enable buttons for answer selection
            option1.setEnabled(true);
            option2.setEnabled(true);
            option3.setEnabled(true);
            option4.setEnabled(true);
        }
    }

    private void handleAnswerSelection(Button selectedOption) {
        Question currentQuestion = questions.get(currentQuestionIndex);

        // Disable buttons after answering
        option1.setEnabled(false);
        option2.setEnabled(false);
        option3.setEnabled(false);
        option4.setEnabled(false);

        // Check if the selected answer is correct
        if (selectedOption.getText().toString().equals(currentQuestion.getCorrectAnswer())) {
            score++;
        }

        scoreText.setText("Score: " + score);

        // Move to the next question or finish the quiz if it's the last one
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.size()) {
            displayQuestion(currentQuestionIndex);
        } else {
            finishQuiz();
        }
    }

    private void startTimer() {
        progressBar.setProgress(0);
        countDownTimer = new CountDownTimer(15000, 1000) {  // 15 seconds countdown timer
            @Override
            public void onTick(long millisUntilFinished) {
                // Calculate the progress bar as the timer ticks down
                int progress = (int) ((15000 - millisUntilFinished) / 15000f * 100);
                progressBar.setProgress(progress);
                timerText.setText("Time Left: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                // Automatically move to the next question when time runs out
                handleAnswerSelection(option1);  // You can change this to auto-skip or answer the first option
            }
        }.start();
    }

    private void finishQuiz() {
        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
        intent.putExtra("SCORE", score);
        startActivity(intent);
        finish();
    }
}
