package com.example.shailesh.tic_tac_toe;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String pname1,pname2;
    TextView score1,score2,lblp1,lblp2;
    int activePlayer = 0;
    boolean gameIsActive = false;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void dropin(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameIsActive) {
            gameState[tappedCounter] = activePlayer;
            //counter.setTranslationY(-1000f);
            counter.setAlpha(0f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.right);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.wrong);
                activePlayer = 0;
            }

            counter.animate().alpha(1f).setDuration(200);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {

                        String winner = pname2;
                        if (gameState[winningPosition[0]] == 0) {
                            int i = Integer.parseInt(score2.getText().toString().trim());
                            i++;
                            score2.setText(""+i);
                            winner = pname1;
                        } else if(gameState[winningPosition[0]] == 1){
                            int i = Integer.parseInt(score1.getText().toString().trim());
                            i++;
                            score1.setText(""+i);
                        }
                    gameIsActive = false;
                    TextView winnermessage = (TextView) findViewById(R.id.winnerMessage);
                    winnermessage.setText(winner+ " Winns ");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                    //Toast.makeText(this, winner+ "wins", Toast.LENGTH_SHORT).show();
                } else {
                    boolean gameIsOver = true;
                    for(int counterState : gameState){
                        if(counterState == 2) gameIsOver = false;
                    }
                    if(gameIsOver) {
                        TextView winnermessage = (TextView) findViewById(R.id.winnerMessage);
                        winnermessage.setText(" Game draw ");
                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                    }
                }

            }
        }
    }

    public void login(View view) {
        gameIsActive = true;
        LinearLayout layout = (LinearLayout)findViewById(R.id.login);
        layout.setVisibility(View.INVISIBLE);

        LinearLayout scoreLayout = (LinearLayout)findViewById(R.id.scoreLayout);
        scoreLayout.setVisibility(View.VISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        gridLayout.setVisibility(View.VISIBLE);

        activePlayer = 0;

        lblp1 = (TextView) findViewById(R.id.playerName1);
        lblp2 = (TextView) findViewById(R.id.playerName2);

        TextView playerName1= (TextView) findViewById(R.id.playerNameInp1);
        TextView playerName2= (TextView) findViewById(R.id.playerNameInp2);

        pname1 = playerName1.getText().toString().trim();
        if(pname1.isEmpty()) {
            pname1="Player 1";
        }
        lblp1.setText(pname1);

        pname2 = playerName2.getText().toString().trim();
        if(pname2.isEmpty()) {
            pname2="Player 2";
        }
        lblp2.setText(pname2);
    }

    public void playAgain(View view) {
        gameIsActive = true;
        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0;

        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        for (int i = 0; i< gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        score1 = (TextView) findViewById(R.id.playerValue1);
        score2 = (TextView) findViewById(R.id.playerValue2);

    }
}
