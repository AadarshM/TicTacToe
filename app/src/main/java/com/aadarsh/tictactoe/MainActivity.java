package com.aadarsh.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int player = 0;

    int[] state = {2,2,2,2,2,2,2,2,2};
    boolean gameActive = true;
    int[][] winningStates = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void makeVisible(View view){
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (state[tappedCounter] == 2 && gameActive) {
            state[tappedCounter] = player;
            counter.setAlpha(0f);
            if (player == 0) {
                counter.setImageResource(R.drawable.yellow);
                player = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                player = 0;
            }
            counter.animate().alpha(1f).rotation(360).setDuration(1000);
        }

        for (int[] winningState : winningStates){
            if (state[winningState[0]] == state[winningState[1]] &&
                    state[winningState[1]] == state[winningState[2]] &&
                    state[winningState[0]] != 2){
                TextView winnningMessage = (TextView) findViewById(R.id.winningMessage);
                int winner = state[winningState[0]] +1;
                winnningMessage.setText(" Player " + winner + " has won! ");
                winnningMessage.setVisibility(View.VISIBLE);
                gameActive = false;

            } else {
                boolean gameOver = true;
                for (int i : state){
                    if (i==2){
                        gameOver = false;
                    }
                }
                if (gameOver){
                    TextView winnningMessage = (TextView) findViewById(R.id.winningMessage);
                    winnningMessage.setText("No winner!!!");
                    winnningMessage.setVisibility(View.VISIBLE);
                    gameActive = false;
                }
            }
        }
    }

    public void reset(View view){
        player = 0;
        for (int i = 0; i<state.length; i++){
            state[i] = 2;
        }
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i<9; i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
        TextView winnningMessage = (TextView) findViewById(R.id.winningMessage);
        winnningMessage.setVisibility(View.INVISIBLE);
        gameActive = true;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
