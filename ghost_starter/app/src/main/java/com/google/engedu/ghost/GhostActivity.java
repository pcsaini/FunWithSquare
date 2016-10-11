package com.google.engedu.ghost;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
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
    private SimpleDictionary simple_fast_dictionary;
    //private FastDictionary simple_fast_dictionary;
    private boolean userTurn = false;
    private Random random = new Random();
    private InputStream is = null;
    private TextView tvGhostText, label;
    private Button bChallenge, bRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ghost);

        tvGhostText = (TextView) findViewById(R.id.ghostText);
        label = (TextView) findViewById(R.id.gameStatus);

        bChallenge = (Button) findViewById(R.id.bChallenge);
        bRestart = (Button) findViewById(R.id.bRestart);

        bChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChallengeMethod();
            }
        });

        bRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestartMethod();
            }
        });

        try {
            is = getAssets().open("words.txt");
            simple_fast_dictionary = new SimpleDictionary(is);
            //simple_fast_dictionary = new FastDictionary(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        onStart(null);

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
        // Do computer turn stuff then make it the user's turn again
        String temp_text = tvGhostText.getText().toString();

        if(temp_text.length()>=4 && simple_fast_dictionary.isWord(temp_text)){
            Toast.makeText(GhostActivity.this,"Computer Wins!",Toast.LENGTH_SHORT).show();
            label.setText("Computer Wins");
        }
        else{
            String word = simple_fast_dictionary.getAnyWordStartingWith(temp_text);
            if(word==null){
                Toast.makeText(GhostActivity.this,"Computer Wins!!!",Toast.LENGTH_SHORT).show();
            }
            else{
                temp_text = temp_text + word.charAt(temp_text.length());
                tvGhostText.setText(temp_text);
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

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        char keyPressed = (char) event.getUnicodeChar();

        if(keyPressed>=97 && keyPressed<=122){
            String currentText = tvGhostText.getText().toString();
            tvGhostText.setText(currentText + keyPressed);
            label.setText(COMPUTER_TURN);
            computerTurn();
            return true;
        }
        else{
            Toast.makeText(GhostActivity.this,"Invalid letter. Enter from a-z only",Toast.LENGTH_SHORT).show();
        }

        return super.onKeyUp(keyCode, event);
    }


    public void ChallengeMethod(){
        String text = tvGhostText.getText().toString();

        if(text.length()>=4 && simple_fast_dictionary.isWord(text))
        {
            Toast.makeText(this,"You win",Toast.LENGTH_LONG).show();
        }
        else
        {
            String word=simple_fast_dictionary.getAnyWordStartingWith(text);

            if(word!=null)
            {
                Toast.makeText(this,"Computer Wins. Word is "+word,Toast.LENGTH_LONG).show();
            }
            else
            {

                Toast.makeText(this,"You win",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void RestartMethod(){
        onStart(null);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putString("WORD_FRAGMENT", tvGhostText.getText().toString());
        savedInstanceState.putString("GAME_STATUS", label.getText().toString());

        Log.d("Ghost 2",savedInstanceState.getString("WORD_FRAGMENT"));
        Log.d("Label 2",savedInstanceState.getString("GAME_STATUS"));

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }


    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            // Restore value of members from saved state

            Log.d("Ghost 1",savedInstanceState.getString("WORD_FRAGMENT"));
            Log.d("Label 1",savedInstanceState.getString("GAME_STATUS"));
            tvGhostText.setText(savedInstanceState.getString("WORD_FRAGMENT"));
            label.setText(savedInstanceState.getString("GAME_STATUS"));
        }
    }

}
