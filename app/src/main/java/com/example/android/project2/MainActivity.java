package com.example.android.project2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.project2.R.id.numberA;
import static com.example.android.project2.R.id.scoreA;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

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

    List<Player> playerList = new ArrayList<Player>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        teamScoreA = (TextView)findViewById(scoreA);
        teamScoreB = (TextView)findViewById(R.id.scoreB);

        editNumberA = (EditText) findViewById(numberA);
        editNumberB = (EditText) findViewById(R.id.numberB);

        editTeamA = (EditText) findViewById(R.id.teamA);
        editTeamB = (EditText) findViewById(R.id.teamB);

        listViewA = (ListView) findViewById(R.id.teamAList);
        playerAdapterA = new PlayerAdapter(this, R.layout.row_player);
        listViewA.setAdapter(playerAdapterA);

        listViewB = (ListView) findViewById(R.id.teamBList);
        playerAdapterB = new PlayerAdapter(this, R.layout.row_player);
        listViewB.setAdapter(playerAdapterB);

        sharedPrefScoreA.registerOnSharedPreferenceChangeListener(this);
        sharedPrefScoreB.registerOnSharedPreferenceChangeListener(this);
    }


    public void addPlayerA(View view) {
        if (editNumberA.getText().length() != 0 && Integer.parseInt(editNumberA.getText().toString()) > 0 && Integer.parseInt(editNumberA.getText().toString()) < 100 && editTeamA.getText().length() != 0) {
            SharedPreferences sharedPrefNameA = getSharedPreferences("nameA", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPrefNameA.edit();
            edit.putString("nameA", editTeamA.getText().toString());
            edit.apply();
            boolean duplicate = false;
            Player player = new Player(Integer.parseInt(editNumberA.getText().toString()), editTeamA.getText().toString(),0);

            if (playerAdapterA.isEmpty()){
                playerAdapterA.add(player);
                playerList.add(player);
            }
            else if (!playerAdapterA.isEmpty()){
                for (int i = 0; i < playerAdapterA.getCount(); i++){
                    if (playerAdapterA.getItem(i).equals(player)){
                        Toast.makeText(this, "Duplicate player number, players must have unique number", Toast.LENGTH_SHORT).show();
                        duplicate = true;
                    }
                }
                if (!duplicate){
                    playerAdapterA.add(player);
                    playerList.add(player);
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
            Player player = new Player(Integer.parseInt(editNumberB.getText().toString()), editNumberB.getText().toString(),0);

            if (playerAdapterB.isEmpty()){
                playerAdapterB.add(player);
                playerList.add(player);
            }
            else if (!playerAdapterB.isEmpty()){
                for (int i = 0; i < playerAdapterB.getCount(); i++){
                    if (playerAdapterB.getItem(i).equals(player)){
                        Toast.makeText(this, "Duplicate player number, players must have unique number", Toast.LENGTH_SHORT).show();
                        duplicate = true;
                    }
                }
                if (!duplicate){
                    playerAdapterB.add(player);
                    playerAdapterB.remove(player);
                    playerList.add(player);
                }
            }
        } else
            Toast.makeText(MainActivity.this, "Please input player number 0-99 and a team name", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key){
        if(key.equals("scoreA")){
            teamScoreA.setText(Integer.toString(sharedPreferences.getInt("scoreA", 0)));
        }else if (key.equals("scoreB")){
            teamScoreB.setText(Integer.toString(sharedPreferences.getInt("scoreB", 0)));
        }
    }
    public void reset(View view){
        playerAdapterB.remove(playerList.get(0));
        listViewB.setAdapter(playerAdapterB);
    }
}
