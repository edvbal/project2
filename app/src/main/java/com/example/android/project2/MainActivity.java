package com.example.android.project2;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    /**
     * Declaring all the views, and PlayerAdapter for ListView
     */
    ListView listViewA;

    ListView listViewB;
    PlayerAdapter playerAdapterB;

    EditText editNumberA;
    EditText editNumberB;

    EditText editTeamA;
    EditText editTeamB;

    TextView teamScoreA;
    TextView teamScoreB;

    TextView ptsHeaderA;
    TextView nrHeaderA;

    TextView ptsHeaderB;
    TextView nrHeaderB;

    Button resetScoresA;
    Button resetScoresB;

    Button addPlayerA;
    Button addPlayerB;

    PlayerAdapter playerAdapterA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addPlayerA = (Button) findViewById(R.id.resetScoresA);
        addPlayerB = (Button) findViewById(R.id.resetScoresB);

        resetScoresA = (Button) findViewById(R.id.addPlayerA);
        resetScoresB = (Button) findViewById(R.id.addPlayerB);

        //colorButtons();

        teamScoreA = (TextView) findViewById(R.id.scoreA);
        teamScoreB = (TextView) findViewById(R.id.scoreB);

        editNumberA = (EditText) findViewById(R.id.numberA);
        editNumberB = (EditText) findViewById(R.id.numberB);

        editTeamA = (EditText) findViewById(R.id.teamA);
        editTeamB = (EditText) findViewById(R.id.teamB);

        ptsHeaderA = (TextView) findViewById(R.id.ptsHeaderA);
        nrHeaderA = (TextView) findViewById(R.id.nrHeaderA);

        ptsHeaderB = (TextView) findViewById(R.id.ptsHeaderB);
        nrHeaderB = (TextView) findViewById(R.id.nrHeaderB);

        listViewA = (ListView) findViewById(R.id.teamAList);
        listViewB = (ListView) findViewById(R.id.teamBList);

        playerAdapterA = new PlayerAdapter(this, R.layout.row_player);
        listViewA.setAdapter(playerAdapterA);

        playerAdapterB = new PlayerAdapter(this, R.layout.row_player);
        listViewB.setAdapter(playerAdapterB);

        SharedPreferences sharedPrefScoreA = getSharedPreferences("scoreA", Context.MODE_PRIVATE);
        SharedPreferences sharedPrefScoreB = getSharedPreferences("scoreB", Context.MODE_PRIVATE);

        sharedPrefScoreA.registerOnSharedPreferenceChangeListener(this);
        sharedPrefScoreB.registerOnSharedPreferenceChangeListener(this);

        clearSharedPreferences();
    }
    public void clearSharedPreferences() {
        SharedPreferences sharedPrefScoreA = getSharedPreferences("scoreA", Context.MODE_PRIVATE);
        SharedPreferences sharedPrefScoreB = getSharedPreferences("scoreB", Context.MODE_PRIVATE);
        sharedPrefScoreA.edit().clear().apply();
        sharedPrefScoreB.edit().clear().apply();

        SharedPreferences sharedPrefNameA = getSharedPreferences("nameA", Context.MODE_PRIVATE);
        SharedPreferences sharedPrefNameB = getSharedPreferences("nameB", Context.MODE_PRIVATE);
        sharedPrefNameA.edit().clear().apply();
        sharedPrefNameB.edit().clear().apply();

        SharedPreferences sharedPrefNumberA = getSharedPreferences("numberA", Context.MODE_PRIVATE);
        SharedPreferences sharedPrefNumberB = getSharedPreferences("numberB", Context.MODE_PRIVATE);
        sharedPrefNumberA.edit().clear().apply();
        sharedPrefNumberB.edit().clear().apply();

        SharedPreferences sharedPrefPlayers = getSharedPreferences("players", Context.MODE_PRIVATE);
        sharedPrefPlayers.edit().clear().apply();
    }
    public void colorButtons() {
        resetScoresA.getBackground().setColorFilter(Color.parseColor("#dbf2d7"), PorterDuff.Mode.DARKEN);
        addPlayerA.getBackground().setColorFilter(Color.parseColor("#dbf2d7"), PorterDuff.Mode.DARKEN);
        resetScoresB.getBackground().setColorFilter(Color.parseColor("#d6e1f1"), PorterDuff.Mode.DARKEN);
        addPlayerB.getBackground().setColorFilter(Color.parseColor("#d6e1f1"), PorterDuff.Mode.DARKEN);
    }
    public void addPlayerA(View view) {
        if (editNumberA.getText().length() != 0 && Integer.parseInt(editNumberA.getText().toString()) > 0 && Integer.parseInt(editNumberA.getText().toString()) < 100 && editTeamA.getText().length() != 0) {
            SharedPreferences sharedPrefNameA = getSharedPreferences("nameA", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPrefNameA.edit();
            edit.putString("nameA", editTeamA.getText().toString());
            edit.apply();
            boolean duplicate = false;
            Player player = new Player(Integer.parseInt(editNumberA.getText().toString()), editTeamA.getText().toString(), 0);
            if (playerAdapterA.isEmpty()) {
                nrHeaderA.setText("NR");
                ptsHeaderA.setText("PTS");
                playerAdapterA.add(player);
            } else if (!playerAdapterA.isEmpty()) {
                for (int i = 0; i < playerAdapterA.getCount(); i++) {
                    if (playerAdapterA.getItem(i).equals(player)) {
                        Toast.makeText(this, "Duplicate player number, players must have unique number", Toast.LENGTH_SHORT).show();
                        duplicate = true;
                    }
                }
                if (!duplicate) {
                    playerAdapterA.add(player);
                }
            }
        } else
            Toast.makeText(MainActivity.this, "Please input player number 0-99 and a team name", Toast.LENGTH_SHORT).show();
    }
    public void addPlayerB(View view) {
        if (editNumberB.getText().length() != 0 && Integer.parseInt(editNumberB.getText().toString()) > 0 && Integer.parseInt(editNumberB.getText().toString()) < 100 && editTeamB.getText().length() != 0) {
            SharedPreferences sharedPrefNameB = getSharedPreferences("nameB", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPrefNameB.edit();
            edit.putString("nameB", editTeamB.getText().toString());
            edit.apply();
            boolean duplicate = false;
            Player player = new Player(Integer.parseInt(editNumberB.getText().toString()), editNumberB.getText().toString(), 0);
            if (playerAdapterB.isEmpty()) {
                nrHeaderB.setText("NR");
                ptsHeaderB.setText("PTS");
                playerAdapterB.add(player);
            } else if (!playerAdapterB.isEmpty()) {
                for (int i = 0; i < playerAdapterB.getCount(); i++) {
                    if (playerAdapterB.getItem(i).equals(player)) {
                        Toast.makeText(this, "Duplicate player number, players must have unique number", Toast.LENGTH_SHORT).show();
                        duplicate = true;
                    }
                }
                if (!duplicate) {
                    playerAdapterB.add(player);
                }
            }
        } else
            Toast.makeText(MainActivity.this, "Please input player number 0-99 and a team name", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("scoreA")) {
            teamScoreA.setText(Integer.toString(sharedPreferences.getInt("scoreA", 0)));
        } else if (key.equals("scoreB")) {
            teamScoreB.setText(Integer.toString(sharedPreferences.getInt("scoreB", 0)));
        }
    }
    public void resetB(View view) {
        SharedPreferences scoreB = getSharedPreferences("scoreB", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = scoreB.edit();
        edit.putInt("scoreB", 0);
        edit.apply();
        teamScoreB.setText(Integer.toString(scoreB.getInt("scoreB", 0)));
        nrHeaderB.setText("");
        ptsHeaderB.setText("");
        playerAdapterB.remove();
    }
    public void resetA(View view) {
        SharedPreferences scoreA = getSharedPreferences("scoreA", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = scoreA.edit();
        edit.putInt("scoreA", 0);
        edit.apply();
        teamScoreA.setText(Integer.toString(scoreA.getInt("scoreA", 0)));
        nrHeaderA.setText("");
        ptsHeaderA.setText("");
        playerAdapterA.remove();
    }

}
