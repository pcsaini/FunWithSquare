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

    TextView txtView,CorrectNum,Score,Round;
    Boolean flag=false;
    Button btnNext,btnSolve,OK;
    EditText editText;
    Random r;
    String Square="YES";
    int randomNumber,size=0,a,start=3,control=0,score=0,x=0,k=0;
    ArrayList<Integer> y;
    PatriciaTrieTest ptt =new PatriciaTrieTest();
    PatriciaTrie pt =new PatriciaTrie();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        r=new Random();
        y=new ArrayList<Integer>();
        txtView = (TextView) findViewById(R.id.txtView);
        Round = (TextView) findViewById(R.id.round);
        Score = (TextView) findViewById(R.id.score);
        CorrectNum = (TextView) findViewById(R.id.corrNum);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnSolve = (Button) findViewById(R.id.btnSolve);
        editText=(EditText)findViewById(R.id.pickNumber);
        OK=(Button)findViewById(R.id.btnSubmit);


        y = ptt.game(pt, start, Square);
        size = y.size();
        k = size;
        x = r.nextInt(y.size());
        randomNumber = y.get(x);
        y.remove(x);
        //size = size - 1;
        a = randomNumber / 10;
        txtView.setText("" + a);



        btnSolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = true;
                //txtView.setText(""+randomNumber);
                CorrectNum.setText(Math.sqrt(randomNumber)+" : "+randomNumber);

            }
        });
        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=editText.getText().toString();
                if(s!=""){
                    String s1=randomNumber+"";
                    String s2=a+"";
                    String s3=s2+s;
                    flag=true;
                    if(s1.equals(s3)){
                        txtView.setText(""+s3);
                        score+=5;
                        Score.setText("Current Score : "+score);

                        //Toast.makeText(this,"You Won Press Next",Toast.LENGTH_LONG).show();
                    }
                    else {
                        txtView.setText(""+s3);
                        if(score>2){
                            score-=2;
                        }}
                    CorrectNum.setText(Math.sqrt(randomNumber)+" : "+randomNumber);
                    editText.setText("");
                    Score.setText("Current Score : "+score);
                }
                else
                {
                    flag=false;
                }

            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if (score > 50) {
                    //txtView.setText("Your are WINNER and  You will Succeed in your life");
                    btnNext.setEnabled(false);

                }*/
                if (flag == true) {
                    if (control < 5) {
                        control++;
                        flag = false;
                        int x = r.nextInt(y.size());
                        randomNumber = y.get(x);
                        y.remove(x);
                        size = size - 1;
                        a = randomNumber / 10;
                        txtView.setText("" + a);
                        CorrectNum.setText("0");


                    } else {
                        if (score > 10) {
                            Round.setText("Round "+(start-1));
                            pt.makeEmpty();
                            start++;
                            y = ptt.game(pt, start, Square);
                            size = y.size();
                            k = size;
                            x = r.nextInt(y.size());
                            randomNumber = y.get(x);

                            y.remove(x);
                            //size = size - 1;
                            a = randomNumber / 10;
                            txtView.setText("" + a);
                            control = 0;
                            score = 0;
                            CorrectNum.setText("0");
                        } else {
                            pt.makeEmpty();

                            y = ptt.game(pt, start, Square);
                            size = y.size();
                            k = size;
                            x = r.nextInt(y.size());
                            randomNumber = y.get(x);
                            y.remove(x);
                            //size = size - 1;
                            a = randomNumber / 10;
                            txtView.setText("" + a);
                            control = 0;
                            score = 0;


                        }
                    }
                }
            }
        });
    }
}
