package com.example.android.project2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView listViewA;
    PlayerAdapter playerAdapterA;

    ListView listViewB;
    PlayerAdapter playerAdapterB;

    EditText editNumberA;
    EditText editNumberB;

    EditText editTeamA;
    EditText editTeamB;

    TextView teamScoreA;
    TextView teamScoreB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNumberA = (EditText) findViewById(R.id.numberA);
        editNumberB = (EditText) findViewById(R.id.numberB);

        editTeamA = (EditText) findViewById(R.id.teamA);
        editTeamB = (EditText) findViewById(R.id.teamB);

        teamScoreA = (TextView) findViewById(R.id.scoreA);
        teamScoreB = (TextView) findViewById(R.id.scoreB);

        listViewA = (ListView) findViewById(R.id.teamAList);
        playerAdapterA = new PlayerAdapter(this, R.layout.row_player);
        listViewA.setAdapter(playerAdapterA);

        listViewB = (ListView) findViewById(R.id.teamBList);
        playerAdapterB = new PlayerAdapter(this, R.layout.row_player);
        listViewB.setAdapter(playerAdapterB);

        displayScores();
    }

    public void displayScores() {

        for (int i = 0; i < playerAdapterB.getCount(); i++) {
            Player player = (Player) playerAdapterB.getItem(i);
            teamScoreB.setText(Integer.toString(player.getScore()));
        }
        for (int i = 0; i < playerAdapterA.getCount(); i++) {
            Player player = (Player) playerAdapterA.getItem(i);
            teamScoreA.setText(Integer.toString(player.getScore()));
        }
    }

    public void addPlayerA(View view) {
        if (editNumberA.getText().length() != 0 && Integer.parseInt(editNumberA.getText().toString()) > 0 && Integer.parseInt(editNumberA.getText().toString()) < 100 && editTeamA.getText().length() != 0) {
            Player player = new Player(Integer.parseInt(editNumberA.getText().toString()), editTeamA.getText().toString());
            playerAdapterA.add(player);
            displayScores();
        } else
            Toast.makeText(MainActivity.this, "Please input player number 0-99 and a team name", Toast.LENGTH_SHORT).show();
    }

    public void addPlayerB(View view) {
        if (editNumberB.getText().length() != 0 && Integer.parseInt(editNumberB.getText().toString()) > 0 && Integer.parseInt(editNumberB.getText().toString()) < 100 && editTeamB.getText().length() != 0) {
            Player player = new Player(Integer.parseInt(editNumberB.getText().toString()), editTeamB.getText().toString());
            playerAdapterB.add(player);
            displayScores();

        } else
            Toast.makeText(MainActivity.this, "Please input player number 0-99 and a team name", Toast.LENGTH_SHORT).show();
    }
}
