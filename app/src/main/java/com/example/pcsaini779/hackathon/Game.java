package com.example.pcsaini779.hackathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class Game extends AppCompatActivity {

    TextView txtView, CorrectNum, Score, Round,nextRound;
    Boolean flag = false;
    Button btnNext, btnSolve, OK;
    EditText editText;
    Random r;
    String Square = "YES";
    int randomNumber, size = 0, a, start = 3, control = 0, score = 0, x = 0, k = 0, turn = 1, firstRoundScore = 0, secondRoundScore = 0, thirdRoundScore = 0;
    ArrayList<Integer> y;
    PatriciaTrieTest ptt = new PatriciaTrieTest();
    PatriciaTrie pt = new PatriciaTrie();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        r = new Random();
        y = new ArrayList<Integer>();
        txtView = (TextView) findViewById(R.id.txtView);
        Round = (TextView) findViewById(R.id.round);
        Score = (TextView) findViewById(R.id.score);
        CorrectNum = (TextView) findViewById(R.id.corrNum);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnSolve = (Button) findViewById(R.id.btnSolve);
        editText = (EditText) findViewById(R.id.pickNumber);
        OK = (Button) findViewById(R.id.btnSubmit);
        nextRound = (TextView) findViewById(R.id.nextRound);


        pickNumber(start);

        btnSolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = true;
                //txtView.setText(""+randomNumber);
                CorrectNum.setText((int)Math.sqrt(randomNumber) + " : " + randomNumber);
                OK.setEnabled(false);
                btnSolve.setEnabled(false);

            }
        });
        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRound();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OK.setEnabled(true);
                if (flag == true) {
                    if (control < 4) {
                        control++;
                        turn++;
                        Round.setText("Round " + (start - 2) +" : Turn " + turn);
                        flag = false;
                        pickNumber(start);
                        CorrectNum.setText("_");

                    } else {
                        if (score > 10) {
                            nextRound.setVisibility(View.VISIBLE);
                            nextRound.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    nextRound();
                                    nextRound.setVisibility(View.INVISIBLE);
                                }
                            });
                        } else {
                            Toast.makeText(Game.this,"Your Score is Low Please Replay",Toast.LENGTH_LONG).show();
                            nextRound.setVisibility(View.VISIBLE);
                            nextRound.setText("Replay");
                            nextRound.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    replayRound();
                                    nextRound.setVisibility(View.INVISIBLE);
                                }
                            });
                        }
                    }
                }
            }
        });
    }

    public void pickNumber(int start) {
        y = ptt.game(pt, start, Square);
        size = y.size();
        k = size;
        x = r.nextInt(y.size());
        randomNumber = y.get(x);
        y.remove(x);
        //size = size - 1;
        a = randomNumber / 10;
        txtView.setText("" + a);
    }

    public void setRound() {
        String s = editText.getText().toString();
        if (s != "") {
            String s1 = randomNumber + "";
            String s2 = a + "";
            String s3 = s2 + s;
            flag = true;
            if (s1.equals(s3)) {
                txtView.setText("" + s3);
                score += 5;
                Toast.makeText(Game.this, "You Won Press Next", Toast.LENGTH_LONG).show();
            } else {
                txtView.setText("" + s3);
                Toast.makeText(Game.this, "You Loss Press Next", Toast.LENGTH_LONG).show();
                score -= 2;
            }
            CorrectNum.setText((int)Math.sqrt(randomNumber) + " : " + randomNumber);
            editText.setText("");
            Score.setText("" + score);
        } else {
            flag = false;
        }
        OK.setEnabled(false);
        if(start == 3){
            firstRoundScore +=score;
        }
        else if (start == 4){
            secondRoundScore +=score;
        }
        else if(start == 5){
            thirdRoundScore +=score;
        }
    }
    public void nextRound() {
        Round.setText("Round " + (start - 1));
        pt.makeEmpty();
        start++;
        pickNumber(start);
        control = 0;
        //score = 0;
        turn = 0;
        CorrectNum.setText("0");
    }

    public void replayRound() {
        pt.makeEmpty();
        control = 0;
        turn = 1;
        score = 0;
        pickNumber(start);
    }
    public void firstRound(){
        pt.makeEmpty();
        start = 3;
        pickNumber(start);
    }
}
