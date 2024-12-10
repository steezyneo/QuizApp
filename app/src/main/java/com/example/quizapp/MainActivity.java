package com.example.quizapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RadioGroup options;
    private RadioButton option1;
    private RadioButton option2;
    private RadioButton option3;
    private RadioButton option4;
    private Button next;

    private TextView qnCount;
    private TextView score;
    private TextView correct;
    private TextView wrong;
    private TextView time;
    private TextView question;

    private ArrayList<Questions> questionList;
    private int questionCounter;
    private int totelQuestionCount;
    private Questions currentQuestion;
    private boolean isAnswered;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        setupUI();
        fetchDB();
    }

    private void setupUI() {
        qnCount = findViewById(R.id.txtTotQn);
        score = findViewById(R.id.txtScore);
        correct = findViewById(R.id.txtCorrect);
        wrong = findViewById(R.id.txtWrong);
        time = findViewById(R.id.txtTimer);
        question = findViewById(R.id.question);
        options = findViewById(R.id.options);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        next = findViewById(R.id.next);
    }
    private void fetchDB(){
        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestions();
    }
}