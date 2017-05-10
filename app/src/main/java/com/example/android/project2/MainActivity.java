package com.example.android.project2;

import android.content.Context;
import android.content.SharedPreferences;
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

    Button addPlayerAButton;
    Button addPlayerBButton;

    PlayerAdapter playerAdapterA;
    PlayerAdapter playerAdapterB;

    SharedPreferences sharedPrefScoreA;
    SharedPreferences sharedPrefScoreB;
    SharedPreferences sharedPrefTeamName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        setPlayerAdapters();

        setSharedPreferences();

        registerSharedPrefChangeListener();

        clearSharedPreferences();

        addPlayerAOnClick();
        addPlayerBOnClick();
    }

    private void findViews() {
        addPlayerAButton = (Button) findViewById(R.id.addPlayerA);
        addPlayerBButton = (Button) findViewById(R.id.addPlayerB);

        resetScoresA = (Button) findViewById(R.id.resetScoresA);
        resetScoresB = (Button) findViewById(R.id.resetScoresB);

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
    }

    private void setPlayerAdapters() {
        playerAdapterA = new PlayerAdapter(this, R.layout.row_player);
        listViewA.setAdapter(playerAdapterA);

        playerAdapterB = new PlayerAdapter(this, R.layout.row_player);
        listViewB.setAdapter(playerAdapterB);
    }

    private void setSharedPreferences() {
        sharedPrefScoreA = getSharedPreferences("scoreA", Context.MODE_PRIVATE);
        sharedPrefScoreB = getSharedPreferences("scoreB", Context.MODE_PRIVATE);

    }

    private void registerSharedPrefChangeListener() {
        sharedPrefScoreA.registerOnSharedPreferenceChangeListener(this);
        sharedPrefScoreB.registerOnSharedPreferenceChangeListener(this);
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

    private void addPlayerAOnClick() {
        addPlayerAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playerTeam = "A";
                if (validateAddPlayerInfo(playerTeam)) {
                    Player player = getAndCreatePlayer(playerTeam);
                    if (playerAdapterA.isEmpty()) {
                        addPlayerEmptyAdapter(player, playerTeam);
                    } else if (!playerAdapterA.isEmpty())
                        addPlayerANotEmptyAdapter(player, playerTeam);
                } else
                    Toast.makeText(MainActivity.this, "Please input player number 0-99 and a team name", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void addPlayerBOnClick() {
        addPlayerBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playerTeam = "B";
                if (validateAddPlayerInfo(playerTeam)) {
                    Player player = getAndCreatePlayer(playerTeam);
                    if (playerAdapterB.isEmpty()) {
                        addPlayerEmptyAdapter(player, playerTeam);
                    } else if (!playerAdapterB.isEmpty())
                        addPlayerANotEmptyAdapter(player, playerTeam);
                } else
                    Toast.makeText(MainActivity.this, "Please input player number 0-99 and a team name", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateAddPlayerInfo(String playerTeam) {
        if (playerTeam.equals("A")) {
            if (editNumberA.getText().length() != 0 &&
                    Integer.parseInt(editNumberA.getText().toString()) > 0 &&
                    Integer.parseInt(editNumberA.getText().toString()) < 100 &&
                    editTeamA.getText().length() != 0)
                return true;
            else return false;
        } else {
            if (editNumberB.getText().length() != 0 &&
                    Integer.parseInt(editNumberB.getText().toString()) > 0 &&
                    Integer.parseInt(editNumberB.getText().toString()) < 100 &&
                    editTeamB.getText().length() != 0)
                return true;
            else return false;
        }
    }

    public Player getAndCreatePlayer(String playerTeam) {
        if (playerTeam.equals("A")) {
            setSharedPrefTeamName("nameA", editTeamA.getText().toString());
            Player player = new Player(Integer.parseInt(editNumberA.getText().toString()),
                    editTeamA.getText().toString(), 0);
            return player;
        } else {
            Player player = new Player(Integer.parseInt(editNumberB.getText().toString()),
                    editTeamB.getText().toString(), 0);
            return player;

        }
    }

    private void addPlayerEmptyAdapter(Player player, String playerTeam) {
        if (playerTeam.equals("A")) {
            setPtsNrHeadersIfEmpty(playerTeam);
            playerAdapterA.add(player);
        } else {
            setPtsNrHeadersIfEmpty(playerTeam);
            playerAdapterB.add(player);
        }
    }

    private void setPtsNrHeadersIfEmpty(String playerTeam) {
        if (playerTeam.equals("A")) {
            if (nrHeaderA.getText().toString().isEmpty() || ptsHeaderA.getText().toString().isEmpty()) {
                nrHeaderA.setText(R.string.NR);
                ptsHeaderA.setText(R.string.PTS);
            }
        } else {
            if (nrHeaderB.getText().toString().isEmpty() || ptsHeaderB.getText().toString().isEmpty()) {
                nrHeaderB.setText(R.string.NR);
                ptsHeaderB.setText(R.string.PTS);
            }
        }
    }

    private void addPlayerANotEmptyAdapter(Player player, String playerTeam) {
        if (playerTeam.equals("A")) {
            for (int i = 0; i < playerAdapterA.getCount(); i++) {
                if (playerAdapterA.getItem(i).equals(player)) {
                    Toast.makeText(MainActivity.this, "Duplicate player number, players must have unique number", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            playerAdapterA.add(player);
        } else {
            for (int i = 0; i < playerAdapterB.getCount(); i++) {
                if (playerAdapterB.getItem(i).equals(player)) {
                    Toast.makeText(MainActivity.this, "Duplicate player number, players must have unique number", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            playerAdapterB.add(player);
        }
    }

    private void setSharedPrefTeamName(String name, String value) {
        sharedPrefTeamName = getSharedPreferences(name, Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPrefTeamName.edit();
            edit.putString(name, value);
            edit.apply();
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
        resetEverythingBSide(scoreB);
    }

    private void resetEverythingBSide(SharedPreferences scoreB) {
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
        resetEverythingASide(scoreA);
    }

    private void resetEverythingASide(SharedPreferences scoreA) {
        teamScoreA.setText(Integer.toString(scoreA.getInt("scoreA", 0)));
        nrHeaderA.setText("");
        ptsHeaderA.setText("");
        playerAdapterA.remove();
    }

}
