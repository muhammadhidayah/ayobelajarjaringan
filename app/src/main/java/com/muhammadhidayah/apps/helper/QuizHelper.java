package com.muhammadhidayah.apps.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import com.muhammadhidayah.apps.model.Question;
/**
 * Created by sontoloyo on 5/29/17.
 */

public class QuizHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/com.muhammadhidayah.apps/databases/";
    private static String DB_NAME = "DBAplikasi.db";
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public QuizHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    // Creates a empty database
    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (!dbExist) {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
        Log.i("QuizApp", "Done with createDataBase()");
    }

    // Check if the database already exist
    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            // If database does't exist.
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        Log.i("QuizApp", "got path");
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, 0);
        //SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

//    public List<Question> getQuestionSet(int numQ) {
//        List<Question> questionSet = new ArrayList<Question>();
//        Cursor c = myDataBase.rawQuery(
//                "SELECT * FROM QUIZ"
//                        + " ORDER BY RANDOM() LIMIT 5" + numQ, null);
//        while (c.moveToNext()) {
//            Question q = new Question();
    //           q.setQuestion(c.getString(1));
    //           q.setAnswer(c.getString(2));
    //          q.setOption1(c.getString(3));
    //          q.setOption2(c.getString(4));
    //          q.setOption3(c.getString(5));
    //          questionSet.add(q);
    //      }
    //      return questionSet;
    //  }

    public List<Question> getQuestionSet (int numQ) {
        List<Question> questionSet = new ArrayList<Question>();
        Cursor c = myDataBase.rawQuery("SELECT * FROM QUIZ ORDER BY RANDOM() LIMIT 4" + numQ, null);
        while (c.moveToNext()) {
            Question q = new Question();
            q.setQuestion(c.getString(1));
            q.setAnswer(c.getString(2));
            q.setOption1(c.getString(3));
            q.setOption2(c.getString(4));
            q.setOption3(c.getString(5));
            questionSet.add(q);
        }

        return questionSet;
    }

}