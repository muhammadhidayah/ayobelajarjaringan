package com.muhammadhidayah.apps.util;

import java.util.List;
import com.muhammadhidayah.apps.model.Question;
/**
 * Created by sontoloyo on 5/29/17.
 */
public class Utility {

    public static String capitalise(String s){
        if (s==null || s.length()==0) return s;
        String s1 = s.substring(0, 1).toUpperCase() + s.substring(1);
        return s1;
    }

    public static String getAnswers(List<Question> questions) {
        int question = 1;
        StringBuffer sb = new StringBuffer();
        for (Question q : questions){
            sb.append("").append(question).append(") ").append(q.getQuestion()).append("\n");
            sb.append("Jawaban: ").append(q.getAnswer()).append("\n\n");
            question ++;
        }
        return sb.toString();

    }


}
