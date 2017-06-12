package com.muhammadhidayah.apps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.muhammadhidayah.apps.model.QuizPlay;
import com.muhammadhidayah.apps.model.Question;
import com.muhammadhidayah.apps.util.Utility;

import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    private Question currentQ;
    private QuizPlay currentGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Quiz");
        setContentView(R.layout.activity_question);
        currentGame = ((QuizApplication) getApplication()).getCurrentQuiz();
        currentQ = currentGame.getNextQuestion();
        Button nextBtn = (Button) findViewById(R.id.nextBtn);
        // nextBtn.setOnClickListener(this);
        setQuestions();
    }

    private void setQuestions() {
        // set the question text from current question
        String question = Utility.capitalise(currentQ.getQuestion());
        TextView qText = (TextView) findViewById(R.id.question);
        qText.setText(question);

        // set the available options
        List<String> answers = currentQ.getQuestionOptions();
        TextView option1 = (TextView) findViewById(R.id.answer1);
        option1.setText(Utility.capitalise(answers.get(0)));

        TextView option2 = (TextView) findViewById(R.id.answer2);
        option2.setText(Utility.capitalise(answers.get(1)));

        TextView option3 = (TextView) findViewById(R.id.answer3);
        option3.setText(Utility.capitalise(answers.get(2)));

        TextView option4 = (TextView) findViewById(R.id.answer4);
        option4.setText(Utility.capitalise(answers.get(3)));
    }

    public void onClickNext(View view) {
        // Make sure a checkbox has been selected
        if (!checkAnswer()) {
            Toast.makeText(getApplicationContext(), "Select an answer",
                    Toast.LENGTH_LONG).show();
            return;
        }
        // Check if it's the end of game
        Log.i("QuizApp", "About to check if game is over");
        if (currentGame.isGameOver()) {
            Log.i("QuizApp", "This game is over");
            Intent i = new Intent(this, EndGameActivity.class);
            startActivity(i);
            finish();
        } else {
            Log.i("QuizApp", "Time for a new question");
            Intent i = new Intent(this, QuestionActivity.class);
            startActivity(i);
            finish();
        }
    }

    public void onClickExit(View view) {
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private boolean checkAnswer() {
        String answer = getSelectedAnswer();
        if (answer == null) {
            return false;
        } else {
            if (currentQ.getAnswer().equalsIgnoreCase(answer)) {
                currentGame.incrementRightAnswers();
            } else {
                currentGame.incrementWrongAnswers();
            }
            return true;
        }
    }

    private String getSelectedAnswer() {
        RadioButton c1 = (RadioButton) findViewById(R.id.answer1);
        RadioButton c2 = (RadioButton) findViewById(R.id.answer2);
        RadioButton c3 = (RadioButton) findViewById(R.id.answer3);
        RadioButton c4 = (RadioButton) findViewById(R.id.answer4);
        if (c1.isChecked()) {
            return c1.getText().toString();
        }
        if (c2.isChecked()) {
            return c2.getText().toString();
        }
        if (c3.isChecked()) {
            return c3.getText().toString();
        }
        if (c4.isChecked()) {
            return c4.getText().toString();
        }

        return null;
    }

}