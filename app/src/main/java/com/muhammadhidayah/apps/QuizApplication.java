package com.muhammadhidayah.apps;

import android.app.Application;
import com.muhammadhidayah.apps.model.QuizPlay;
/**
 * Created by sontoloyo on 5/29/17.
 */


public class QuizApplication extends Application {

    private QuizPlay currentQuiz;

    public void setCurrentQuiz(QuizPlay currentQuiz) {
        this.currentQuiz = currentQuiz;
    }

    public QuizPlay getCurrentQuiz() {
        return currentQuiz;
    }

}
