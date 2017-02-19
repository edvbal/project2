package com.example.android.project2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView listViewA;
    PlayerAdapter playerAdapterA;
    PlayerAdapter playerAdapterB;
    ListView listViewB;

    EditText editNumberA;
    EditText editNumberB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNumberA = (EditText) findViewById(R.id.numberA);
        editNumberB = (EditText) findViewById(R.id.numberB);

        listViewA = (ListView) findViewById(R.id.teamAList);
        playerAdapterA = new PlayerAdapter(this, R.layout.row_player);
        listViewA.setAdapter(playerAdapterA);

        listViewB = (ListView) findViewById(R.id.teamBList);
        playerAdapterB = new PlayerAdapter(this, R.layout.row_player);
        listViewB.setAdapter(playerAdapterB);

    }

    public void addPlayerA(View view){
        if (editNumberA.getText().length() != 0){
            Toast.makeText(MainActivity.this,editNumberA.getText().toString(), Toast.LENGTH_SHORT).show();
            Player player = new Player(Integer.parseInt(editNumberA.getText().toString()));
            playerAdapterA.add(player);
        }
        else Toast.makeText(MainActivity.this,"Please input player number", Toast.LENGTH_SHORT).show();
    }
    
    public void addPlayerB(View view){
        if (editNumberB.getText().length() != 0){
            Toast.makeText(MainActivity.this,editNumberB.getText().toString(), Toast.LENGTH_SHORT).show();
            Player player = new Player(Integer.parseInt(editNumberB.getText().toString()));
            playerAdapterB.add(player);
        }
        else Toast.makeText(MainActivity.this,"Please input player number", Toast.LENGTH_SHORT).show();
    }
}
