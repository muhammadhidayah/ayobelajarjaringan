package com.muhammadhidayah.apps;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.IOException;
import java.util.List;
import com.muhammadhidayah.apps.helper.QuizHelper;
import com.muhammadhidayah.apps.model.QuizPlay;
import com.muhammadhidayah.apps.model.Question;


public class Quiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Quiz");
        setContentView(R.layout.activity_quiz);
    }

    public void onClickStartQuiz(View view) {
        Intent i;
        // Get Question set
        List<Question> questions = getQuestionSetFromDb();
        QuizPlay quiz = new QuizPlay();
        quiz.setQuestions(questions);
        quiz.setNumRounds(getNumQuestions());
        ((QuizApplication) getApplication()).setCurrentQuiz(quiz);
        i = new Intent(this, QuestionActivity.class);
        startActivity(i);
    }

    public void onClickExit(View view) {
        finish();
    }

    private List<Question> getQuestionSetFromDb() throws Error {
        int numQuestions = getNumQuestions();
        QuizHelper myDbHelper = new QuizHelper(this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }
        List<Question> questions = myDbHelper
                .getQuestionSet(numQuestions);
        myDbHelper.close();
        return questions;
    }


    private int getNumQuestions() {

        return 5;
    }
}
