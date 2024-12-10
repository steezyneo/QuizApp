package com.example.quizapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quizapp.QuizContract.*;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class QuizDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "quiz_app";
    public static final int VERSION = 1;
    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_QUESTION_TABLE = "CREATE TABLE " +
                QuestionTable.TABLE_NAME + " ( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER + " INTEGER" +
                ")";
        db.execSQL(SQL_CREATE_QUESTION_TABLE);
        fillQuestionsTable();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
        onCreate(db);
    }

    private void addQuestions(Questions questions) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION, questions.getQuestion());
        cv.put(QuestionTable.COLUMN_OPTION1, questions.getOption1());
        cv.put(QuestionTable.COLUMN_OPTION2, questions.getOption2());
        cv.put(QuestionTable.COLUMN_OPTION3, questions.getOption3());
        cv.put(QuestionTable.COLUMN_OPTION4, questions.getOption4());
        cv.put(QuestionTable.COLUMN_ANSWER, questions.getAnswer());

        db.insert(QuestionTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable() {
        Questions q1 = new Questions(
                "What is the capital of France?",
                "Berlin",
                "Madrid",
                "Paris",
                "Rome",
                3 // Correct answer: Paris
        );
        addQuestions(q1);

        Questions q2 = new Questions(
                "Which planet is known as the Red Planet?",
                "Earth",
                "Mars",
                "Jupiter",
                "Venus",
                2 // Correct answer: Mars
        );
        addQuestions(q2);

        Questions q3 = new Questions(
                "Who wrote 'Romeo and Juliet'?",
                "Charles Dickens",
                "William Shakespeare",
                "Mark Twain",
                "Jane Austen",
                2 // Correct answer: William Shakespeare
        );
        addQuestions(q3);

        Questions q4 = new Questions(
                "What is the largest mammal in the world?",
                "Elephant",
                "Blue Whale",
                "Giraffe",
                "Great White Shark",
                2 // Correct answer: Blue Whale
        );
        addQuestions(q4);

        Questions q5 = new Questions(
                "Which element has the chemical symbol 'O'?",
                "Oxygen",
                "Gold",
                "Hydrogen",
                "Iron",
                1 // Correct answer: Oxygen
        );
        addQuestions(q5);

        Questions q6 = new Questions(
                "What is the square root of 64?",
                "6",
                "7",
                "8",
                "9",
                3 // Correct answer: 8
        );
        addQuestions(q6);

        Questions q7 = new Questions(
                "Which country is famous for its maple syrup?",
                "Canada",
                "USA",
                "Australia",
                "Sweden",
                1 // Correct answer: Canada
        );
        addQuestions(q7);

        Questions q8 = new Questions(
                "What is the boiling point of water at sea level (in Celsius)?",
                "90",
                "95",
                "100",
                "105",
                3 // Correct answer: 100
        );
        addQuestions(q8);

        Questions q9 = new Questions(
                "Who painted the Mona Lisa?",
                "Vincent van Gogh",
                "Pablo Picasso",
                "Leonardo da Vinci",
                "Claude Monet",
                3 // Correct answer: Leonardo da Vinci
        );
        addQuestions(q9);

        Questions q10 = new Questions(
                "Which is the smallest prime number?",
                "0",
                "1",
                "2",
                "3",
                3 // Correct answer: 2
        );
        addQuestions(q10);
    }

    @SuppressLint("Range")
    public ArrayList<Questions> getAllQuestions() {
        ArrayList<Questions> questionsList = new ArrayList<>();
        db = getReadableDatabase();
        String Projection[] = {
                QuestionTable._ID,
                QuestionTable.COLUMN_QUESTION,
                QuestionTable.COLUMN_OPTION1,
                QuestionTable.COLUMN_OPTION2,
                QuestionTable.COLUMN_OPTION3,
                QuestionTable.COLUMN_OPTION4,
                QuestionTable.COLUMN_ANSWER
        };
        Cursor c = db.query(QuestionTable.TABLE_NAME,
                Projection,
                null,
                null,
                null,
                null,
                null);
        if (c.moveToFirst()){
            do {
                Questions questions = new Questions();
                questions.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                questions.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                questions.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                questions.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                questions.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                questions.setAnswer(c.getColumnIndex(QuestionTable.COLUMN_ANSWER));
                questionsList.add(questions);
            }while (c.moveToNext());
        }
        c.close();
        return questionsList;
    }
}
