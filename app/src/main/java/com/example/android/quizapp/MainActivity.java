package com.example.android.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int mQuestionCounter = 1;
    private int maxQuestions = 5;
    private Button mButtonNext;
    private TextView mScore;
    private LinearLayout mScoreTittle;
    private LinearLayout mQuestionRadio;
    private LinearLayout mQuestionChkBox1;
    private LinearLayout mQuestionChkBox2;
    private LinearLayout mQuestionFree;

    private RadioGroup radio1;
    private RadioButton q1correct1;
    private CheckBox q2correct1;
    private CheckBox q2correct2;
    private CheckBox q3correct1;
    private CheckBox q3correct2;
    private CheckBox q2false1;
    private CheckBox q2false2;
    private CheckBox q3false1;
    private CheckBox q3false2;
    private EditText userAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Question 1 answer which is IDE
        q1correct1 = findViewById(R.id.q1correct1);
        //Question 2 answers which is Linked list and Arrays
        q2correct1 = findViewById(R.id.q2correct1);
        q2correct2 = findViewById(R.id.q2correct2);
        //Question 3 answers which is Java and Kotlin
        q3correct1 = findViewById(R.id.q3correct1);
        q3correct2 = findViewById(R.id.q3correct2);
        //False answers
        q2false1 = findViewById(R.id.q2false1);
        q2false2 = findViewById(R.id.q2false2);
        q3false1 = findViewById(R.id.q3false1);
        q3false2 = findViewById(R.id.q3false2);
        mScore = findViewById(R.id.score);
        mScoreTittle = findViewById(R.id.score_tittle);
        mQuestionRadio = findViewById(R.id.question_radio);
        mQuestionChkBox1 = findViewById(R.id.question_chkbox1);
        mQuestionChkBox2 = findViewById(R.id.question_chkbox2);
        mQuestionFree = findViewById(R.id.question_free);
        userAnswer = findViewById(R.id.free_text);
        mButtonNext = findViewById(R.id.btn_next);
        mButtonNext = findViewById(R.id.btn_next);
        radio1 = findViewById(R.id.radio_q1);
        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mQuestionCounter++;
                if (mQuestionCounter == maxQuestions + 1) {
                    mButtonNext.setText(R.string.next);
                    mQuestionCounter = 1;
                    mScoreTittle.setVisibility(View.INVISIBLE);
                    mQuestionRadio.setVisibility(View.VISIBLE);
                }
                else if (mQuestionCounter == 2) {
                    mQuestionRadio.setVisibility(View.INVISIBLE);
                    mQuestionChkBox1.setVisibility(View.VISIBLE);
                }
                else if (mQuestionCounter == 3) {
                    mQuestionChkBox1.setVisibility(View.INVISIBLE);
                    mQuestionChkBox2.setVisibility(View.VISIBLE);
                }
                else if (mQuestionCounter == 4) {
                    mButtonNext.setText(R.string.submit);
                    mQuestionChkBox2.setVisibility(View.INVISIBLE);
                    mQuestionFree.setVisibility(View.VISIBLE);
                }
                else if (mQuestionCounter == 5) {
                    mButtonNext.setText(R.string.retry);
                    mQuestionFree.setVisibility(View.INVISIBLE);
                    mScoreTittle.setVisibility(View.VISIBLE);
                    int score = calculateScore();
                    mScore.setText(getString(R.string.score, score,"/4"));
                    Toast.makeText(MainActivity.this, getString(R.string.score, score,"/4"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private int calculateScore() {
        int score = 0;
        if(q1correct1.isChecked()){
            score++;
        }
        if(q2correct1.isChecked() && q2correct2.isChecked() && !q2false1.isChecked() &&
                !q2false2.isChecked()){
            score++;
            q2correct1.toggle();
            q2correct2.toggle();
        }
        if(q3correct1.isChecked() && q3correct2.isChecked() && !q3false1.isChecked() &&
                !q3false2.isChecked()){
            score++;
            q3correct1.toggle();
            q3correct2.toggle();
        }
        String usertext = userAnswer.getText().toString().toLowerCase();
        if(usertext.equals("google")){
            score++;
        }
        q2correct1.setChecked(false);
        q2correct2.setChecked(false);
        q2false1.setChecked(false);
        q2false2.setChecked(false);
        q2correct1.setChecked(false);
        q2correct1.setChecked(false);
        q3false1.setChecked(false);
        q3false2.setChecked(false);
        radio1.clearCheck();
        userAnswer.setText("");
        return score;
    }
}
