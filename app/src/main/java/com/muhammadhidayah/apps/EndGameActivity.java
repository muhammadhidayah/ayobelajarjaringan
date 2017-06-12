package com.muhammadhidayah.apps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.muhammadhidayah.apps.model.Constants;
import com.muhammadhidayah.apps.model.QuizPlay;
import com.muhammadhidayah.apps.model.Results;

public class EndGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("QuizApp", "Created EndGameActivity");
        super.onCreate(savedInstanceState);
        this.setTitle("Quiz");
        setContentView(R.layout.activity_end_game);
        QuizPlay currentGame = ((QuizApplication) getApplication())
                .getCurrentQuiz();
        String result = "Skormu adalah " + currentGame.getRight() + "/"
                + currentGame.getNumRounds() + ".. ";
        String comment = Results.getResultComment(currentGame.getRight(),
                currentGame.getNumRounds(), getDifficultySettings());

        TextView results = (TextView) findViewById(R.id.endgameResult);
        results.setText(result + comment);

        Button finishBtn = (Button) findViewById(R.id.finishBtn);
        Button answerBtn = (Button) findViewById(R.id.answerBtn);
    }

    private int getDifficultySettings() {
        SharedPreferences settings = getSharedPreferences(Constants.SETTINGS, 0);
        int diff = settings.getInt(Constants.DIFFICULTY, 2);
        return diff;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void onClickAnswers(View view){
        Intent i = new Intent(this, AnswerActivity.class);
        startActivityForResult(i, Constants.PLAYBUTTON);
    }

    public void onClickMenu(View view) {
        finish();
    }

}