package com.muhammadhidayah.apps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.muhammadhidayah.apps.model.QuizPlay;
import com.muhammadhidayah.apps.util.Utility;

public class AnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Kunci Jawaban");
        setContentView(R.layout.activity_answer);
        QuizPlay currentGame = ((QuizApplication)getApplication()).getCurrentQuiz();
        TextView results = (TextView)findViewById(R.id.answers);
        String answers = Utility.getAnswers(currentGame.getQuestions());
        results.setText(answers);
        Button finishBtn = (Button) findViewById(R.id.finishBtn);
    }

    public void onClickBack(View view) {
        finish();
    }
}
