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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPrefA = getSharedPreferences("scoreA", Context.MODE_PRIVATE);
        sharedPrefA.edit().clear().commit();

        SharedPreferences sharedPrefB = getSharedPreferences("scoreB", Context.MODE_PRIVATE);
        sharedPrefB.edit().clear().commit();

        SharedPreferences sharedPrefNameA = getSharedPreferences("nameA", Context.MODE_PRIVATE);
        sharedPrefA.edit().clear().commit();

        SharedPreferences sharedPrefNameB = getSharedPreferences("nameB", Context.MODE_PRIVATE);
        sharedPrefB.edit().clear().commit();

        teamScoreA = (TextView)findViewById(scoreA);
        teamScoreB = (TextView)findViewById(R.id.scoreB);

        editNumberA = (EditText) findViewById(R.id.numberA);
        editNumberB = (EditText) findViewById(R.id.numberB);

        editTeamA = (EditText) findViewById(R.id.teamA);
        editTeamB = (EditText) findViewById(R.id.teamB);

        listViewA = (ListView) findViewById(R.id.teamAList);
        playerAdapterA = new PlayerAdapter(this, R.layout.row_player);
        listViewA.setAdapter(playerAdapterA);

        listViewB = (ListView) findViewById(R.id.teamBList);
        playerAdapterB = new PlayerAdapter(this, R.layout.row_player);
        listViewB.setAdapter(playerAdapterB);

        SharedPreferences sharedPrefAs = getSharedPreferences("scoreA", Context.MODE_PRIVATE);
        SharedPreferences sharedPrefAss = getSharedPreferences("scoreB", Context.MODE_PRIVATE);

        sharedPrefAs.registerOnSharedPreferenceChangeListener(this);
        sharedPrefAss.registerOnSharedPreferenceChangeListener(this);
    }


    public void addPlayerA(View view) {
        if (editNumberA.getText().length() != 0 && Integer.parseInt(editNumberA.getText().toString()) > 0 && Integer.parseInt(editNumberA.getText().toString()) < 100 && editTeamA.getText().length() != 0) {
            SharedPreferences sharedPreferences = getSharedPreferences("nameA", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("nameA", editTeamA.getText().toString());
            edit.apply();

            Player player = new Player(Integer.parseInt(editNumberA.getText().toString()), editTeamA.getText().toString(),0);
            playerAdapterA.add(player);
        } else
            Toast.makeText(MainActivity.this, "Please input player number 0-99 and a team name", Toast.LENGTH_SHORT).show();
    }

    public void addPlayerB(View view) {
        if (editNumberB.getText().length() != 0 && Integer.parseInt(editNumberB.getText().toString()) > 0 && Integer.parseInt(editNumberB.getText().toString()) < 100 && editTeamB.getText().length() != 0) {
            SharedPreferences sharedPreferences = getSharedPreferences("nameB", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("nameB", editTeamB.getText().toString());
            edit.apply();

            Player player = new Player(Integer.parseInt(editNumberB.getText().toString()), editTeamB.getText().toString(),0);
            playerAdapterB.add(player);
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
}
