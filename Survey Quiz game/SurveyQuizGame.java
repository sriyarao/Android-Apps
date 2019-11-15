package com.example.sriyarao.surveyquiz;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class QuizActivity extends AppCompatActivity {

    private TextView mQuestion, mGreenFlagView, mRedFlagView;
    private ImageView mGreenFlag, mRedFlag;
    private Button mYesButton, mNoButton, mNAButton, mPreviousButton, mNextButton;

    private boolean mAnswer;
    private int mRedFlagScore = 0;
    private int mGreenFlagScore = 0;
    private int mQuestionNumber = 0;
    int counter =0;
    boolean buttonPress =false;
    ArrayList<String> list = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        mGreenFlagView = (TextView) findViewById(R.id.GreenFlagScore);
        mRedFlagView = (TextView) findViewById(R.id.RedFlagScore);
        mQuestion = (TextView) findViewById(R.id.question);
        mYesButton = (Button) findViewById(R.id.YesButton);
        mNoButton = (Button) findViewById(R.id.NoButton);
        mNAButton = (Button) findViewById(R.id.NAButton);
        mPreviousButton = (Button) findViewById(R.id.PreviousButton);
        mNextButton = (Button) findViewById(R.id.NextButton);
        mGreenFlag = (ImageView) findViewById(R.id.GreenFlag);
        mRedFlag = (ImageView) findViewById(R.id.RedFlag);


        updateQuestion();

        //Logic for true button


        mYesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               /* mNextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {*/
                if(buttonPress == false) {
                    buttonPress = true;


                    if (mAnswer == true) {
                        list.add("green");
                        counter++;
                        mGreenFlagScore++;//updates score
                        Toast.makeText(QuizActivity.this, "Positive", Toast.LENGTH_SHORT).show();
                        mYesButton.setBackgroundColor(Color.GREEN);
                        //updateScore(mGreenFlagScore); //converts int variable to string and adds to the mScoreView


                        //Check this before you update the question
                        //mGreenOriginal = mGreenOriginal + mGreenFlagScore;

                    }


                    //If answer is Negative
                    else {

                        mRedFlagScore++;
                        list.add("red");
                        counter++;
                        mYesButton.setBackgroundColor(Color.RED);
                        Toast.makeText(QuizActivity.this, "Negative", Toast.LENGTH_SHORT).show();
                        //updateScore(mRedFlagScore);
                        //mRedOriginal = mRedOriginal + mRedFlagScore;

                    }
                }

            }
        });


        //Logic for false button

        mNoButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                if(buttonPress == false) {
                    buttonPress = true;



            /* mNextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { */

                    if (mAnswer == false) {
                        list.add("green");
                        counter++;
                        mGreenFlagScore++; //updates score
                        mNoButton.setBackgroundColor(Color.GREEN);

                        Toast.makeText(QuizActivity.this, "Positive", Toast.LENGTH_SHORT).show();
                        //converts int variable to string and adds to the mScoreView
                        //updateScore(mGreenFlagScore);
                        //Check this before you update the question


                    }

                    //If answer is Negative
                    else {
                        mRedFlagScore++;
                        list.add("red");
                        counter++;
                        mNoButton.setBackgroundColor(Color.RED);
                        Toast.makeText(QuizActivity.this, "Negative", Toast.LENGTH_SHORT).show();
                        //updateScore(mRedFlagScore);


                    }
                }

            }

        });


//Logic for N/A button

        mNAButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {

                mNAButton.setBackgroundColor(Color.GRAY);
                if (mQuestionNumber == QuizBook.questions.length) {
                    Intent i = new Intent(QuizActivity.this, ResultsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("FinalRedScore", mRedFlagScore);
                    bundle.putInt("FinalGreenScore", mGreenFlagScore);
                    bundle.putStringArrayList("finalList",list);
                    i.putExtras(bundle);
                    QuizActivity.this.finish();
                    startActivity(i);
                } else {

                    mNAButton.setBackgroundColor(Color.GRAY);
                }

            }
        });


        //Logic for Previous button

        mPreviousButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                buttonPress =false;


                    mYesButton.setBackgroundColor(Color.BLACK);
                    mNoButton.setBackgroundColor(Color.BLACK);
                    mNAButton.setBackgroundColor(Color.BLACK);
                    System.out.println("Counter: " + counter + " Question Number: " + mQuestionNumber);

                    System.out.println("ArrayList: " + list + " Size of List: " + list.size());
                    if (mQuestionNumber == 1) {
                        Intent i = new Intent(QuizActivity.this, MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("FinalRedScore", mRedFlagScore);
                        bundle.putInt("FinalGreenScore", mGreenFlagScore);
                        i.putExtras(bundle);
                        QuizActivity.this.finish();

                    } else {

                        if (list.get(counter - 1).equals("green")) {
                            mGreenFlagScore--;
                            list.remove(counter - 1);
                            counter--;
                        } else {
                            mRedFlagScore--;
                            list.remove(counter - 1);
                            counter--;
                        }
                        mQuestionNumber--;

                        deupdateQuestion();

                        updateScore();
                    }
            mQuestionNumber++;
            }
        });


//Logic for Next button

        mNextButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                buttonPress =false;
                System.out.println("Next button fired");

                mYesButton.setBackgroundColor(Color.BLACK);
                mNoButton.setBackgroundColor(Color.BLACK);
                mNAButton.setBackgroundColor(Color.BLACK);
                if (mQuestionNumber == QuizBook.questions.length) {

                    Intent i = new Intent(QuizActivity.this, ResultsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("FinalRedScore", mRedFlagScore);
                    bundle.putInt("FinalGreenScore", mGreenFlagScore);
                    bundle.putStringArrayList("finalList",list);
                    i.putExtras(bundle);
                    QuizActivity.this.finish();

                    startActivity(i);
                    System.out.println(list.toString());


                } else {
                    updateScore();
                    updateQuestion();

                }
            }
        });
    }

    private void updateQuestion() {
        mQuestion.setText(QuizBook.questions[mQuestionNumber]);
        mAnswer = QuizBook.answers[mQuestionNumber];
        mQuestionNumber++;


    }

    private void deupdateQuestion() {
        mQuestionNumber--;
        mQuestion.setText(QuizBook.questions[mQuestionNumber]);
        mAnswer = QuizBook.answers[mQuestionNumber];

    }

    public void updateScore() {

        mGreenFlagView.setText("" + mGreenFlagScore);
        mRedFlagView.setText("" + mRedFlagScore);

    }

    public ArrayList<String> getList() {
        return list;

    }
}



