package com.google.engedu.ghost;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;


public class GhostActivity extends ActionBarActivity {
    private static final String COMPUTER_TURN = "Computer's turn";
    private static final String USER_TURN = "Your turn";
    private GhostDictionary dictionary;
    //private SimpleDictionary simple_fast_dictionary;
    private FastDictionary simple_fast_dictionary;
    private boolean userTurn = false;
    private Random random = new Random();
    private InputStream is = null;
    private TextView tvGhostText, label;
    private Button bChallenge, bRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost);

        tvGhostText = (TextView)findViewById(R.id.ghostText);
        label = (TextView) findViewById(R.id.gameStatus);
        bChallenge = (Button) findViewById(R.id.bChallenge);
        bRestart = (Button) findViewById(R.id.bRestart);

        bChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChallengeMethod();
            }
        });
        bRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RestartMethod();
            }
        });
        try {
            is = getAssets().open("words.txt");
            simple_fast_dictionary = new FastDictionary(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void ChallengeMethod(){
        String currentword = tvGhostText.getText().toString();
        if (currentword.length()>=4 && simple_fast_dictionary.isWord(currentword)){
            Toast.makeText(GhostActivity.this,"You Win", Toast.LENGTH_SHORT).show();
        }
        else{
            String newword = simple_fast_dictionary.getAnyWordStartingWith(currentword);
            if (newword==null){
                Toast.makeText(GhostActivity.this,"Computer Wins. WOrd is " +newword, Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(GhostActivity.this,"You Wins", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void RestartMethod(){
        onStart(null);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        char c = (char) event.getUnicodeChar();
        if((c >= 'a' && c <= 'z') ||(c >= 'A' && c <= 'Z'))
        {
            tvGhostText.setText(tvGhostText.getText().toString()+c);
            label.setText(COMPUTER_TURN);
            computerTurn();
            return true;
        }
        else {
            Toast.makeText(GhostActivity.this,"Invalid letter. Enter from a-z or A-Z only", Toast.LENGTH_SHORT).show();
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ghost, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void computerTurn() {
        TextView label = (TextView) findViewById(R.id.gameStatus);
        String currentString = tvGhostText.getText().toString();
        String newstring;
        if (currentString.length() >= 4 && simple_fast_dictionary.isWord(currentString)){
            Toast.makeText(GhostActivity.this,"Computer Wins!", Toast.LENGTH_SHORT).show();
            label.setText("Restart!");
        }
        else{
            newstring = simple_fast_dictionary.getAnyWordStartingWith(currentString);
            if(newstring == null){
                Toast.makeText(GhostActivity.this,"Computer Wins!", Toast.LENGTH_SHORT).show();
                label.setText("Restart!");
            }
            else{
                newstring = newstring + currentString.charAt(newstring.length());
                tvGhostText.setText(newstring);
            }
        }
        userTurn = true;
        label.setText(USER_TURN);
    }

    /**
     * Handler for the "Reset" button.
     * Randomly determines whether the game starts with a user turn or a computer turn.
     * @param view
     * @return true
     */
    public boolean onStart(View view) {
        userTurn = random.nextBoolean();
        TextView text = (TextView) findViewById(R.id.ghostText);
        text.setText("");
        TextView label = (TextView) findViewById(R.id.gameStatus);
        if (userTurn) {
            label.setText(USER_TURN);
        } else {
            label.setText(COMPUTER_TURN);
            computerTurn();
        }
        return true;
    }
}
