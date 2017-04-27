package com.sitost.courtcounter;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    // A variable that keeps track on the time, that has passed since the Chronometer was started
    long timeWhenStopped, timeStopped;
    private TextView scoreTeamATextView;
    private TextView scoreTeamBTextView;
    private Chronometer simpleChronometer;
    Boolean isTimeStopped = false;
    // Tracks the scores for Team A
    int scoreTeamA = 0;

    // Tracks the scores for Team B
    int scoreTeamB = 0;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("TEAM_A_SCORE", scoreTeamA);
        savedInstanceState.putInt("TEAM_B_SCORE", scoreTeamB);
        savedInstanceState.putLong("timeWhenStopped", simpleChronometer.getBase() - SystemClock.elapsedRealtime());
        savedInstanceState.putLong("timeStopped", timeStopped);
        savedInstanceState.putBoolean("isTimeStopped", isTimeStopped);
        // call superclass to save any view hierarchy
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        scoreTeamATextView.setText(String.valueOf(savedInstanceState.getInt("TEAM_A_SCORE")));
        scoreTeamBTextView.setText(String.valueOf(savedInstanceState.getInt("TEAM_B_SCORE")));

        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initiate a chronometer
        simpleChronometer = (Chronometer) findViewById(R.id.simpleChronometer);

        // initialize TextViews
        scoreTeamATextView = (TextView) findViewById(R.id.team_a_score);
        scoreTeamBTextView = (TextView) findViewById(R.id.team_b_score);

        // recovering the instance state
        if (savedInstanceState != null) {
            isTimeStopped = savedInstanceState.getBoolean("isTimeStopped");
            scoreTeamA = savedInstanceState.getInt("TEAM_A_SCORE");
            scoreTeamB = savedInstanceState.getInt("TEAM_B_SCORE");

//resume the chronometer
            timeWhenStopped = savedInstanceState.getLong("timeWhenStopped");
            timeStopped = savedInstanceState.getLong("timeStopped");
        }
        if (isTimeStopped) {
            simpleChronometer.setBase(SystemClock.elapsedRealtime() + timeStopped);
        } else {
            simpleChronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
            simpleChronometer.start();
        }
    }

    // start a chronometer
    public void startChronometer(View view) {
        simpleChronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
        simpleChronometer.start();
    }

    // stop a chronometer
    public void stopChronometer(View view) {
        timeWhenStopped = simpleChronometer.getBase() - SystemClock.elapsedRealtime();
        simpleChronometer.stop();
    }

    /**
     * Increase the score for Team A by 1 point.
     */
    public void addOneForTeamA(View v) {
        scoreTeamA = scoreTeamA + 1;
        displayForTeamA(scoreTeamA);
    }

    /**
     * Increase the score for Team A by 2 points.
     */
    public void addTwoForTeamA(View v) {
        scoreTeamA = scoreTeamA + 2;
        displayForTeamA(scoreTeamA);
    }

    /**
     * Increase the score for Team A by 3 points.
     */
    public void addThreeForTeamA(View v) {
        scoreTeamA = scoreTeamA + 3;
        displayForTeamA(scoreTeamA);
    }

    /**
     * Increase the score for Team B by 1 point.
     */
    public void addOneForTeamB(View v) {
        scoreTeamB = scoreTeamB + 1;
        displayForTeamB(scoreTeamB);
    }

    /**
     * Increase the score for Team B by 2 points.
     */
    public void addTwoForTeamB(View v) {
        scoreTeamB = scoreTeamB + 2;
        displayForTeamB(scoreTeamB);
    }

    /**
     * Increase the score for Team B by 3 points.
     */
    public void addThreeForTeamB(View v) {
        scoreTeamB = scoreTeamB + 3;
        displayForTeamB(scoreTeamB);
    }

    /**
     * Reset the scores for both teams back to 0.
     */
    public void resetScore(View v) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        simpleChronometer.setBase(SystemClock.elapsedRealtime());
        timeWhenStopped = 0;
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given score for Team B.
     */
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }
}
