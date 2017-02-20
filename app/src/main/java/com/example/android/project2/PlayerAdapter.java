package com.example.android.project2;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.project2.R.id.playerPTS;

/**
 * Created by Edvinas on 19/02/2017.
 */

public class PlayerAdapter extends ArrayAdapter {
    private List list = new ArrayList();
    int pts;
    public PlayerAdapter(Context context, int resource) {super(context,resource);}
    SharedPreferences sharedPref = getContext().getSharedPreferences("score",Context.MODE_PRIVATE);

    public void add(Player object){
        super.add(object);
        list.add(object);
    }
    @Override
    public int getCount() {return list.size();}
    @Override
    public Object getItem(int position) {return list.get(position);}
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row;
        row = convertView;
        final PlayerHolder playerHolder;
        if (row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_player, parent, false);
            playerHolder = new PlayerHolder();

            playerHolder.playerNumber = (TextView) row.findViewById(R.id.playerNumber);
            playerHolder.playerPTS = (TextView) row.findViewById(playerPTS);

            playerHolder.plusOne = (Button) row.findViewById(R.id.plusOne);
            playerHolder.plusTwo = (Button) row.findViewById(R.id.plusTwo);
            playerHolder.plusThree = (Button) row.findViewById(R.id.plusThree);

            Player player = (Player) this.getItem(position);
            pts = player.getScore();
            playerHolder.plusOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pts = Integer.parseInt(playerHolder.playerPTS.getText().toString());
                    pts++;
                    SharedPreferences.Editor edit = sharedPref.edit();
                    edit.putInt("score", sharedPref.getInt("score",0)+pts);
                    edit.apply();
                    playerHolder.playerPTS.setText(Integer.toString(pts));
                }
            });
            playerHolder.plusTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pts = Integer.parseInt(playerHolder.playerPTS.getText().toString());
                    pts += 2;
                    SharedPreferences.Editor edit = sharedPref.edit();
                    edit.putInt("score", sharedPref.getInt("score",0)+pts);
                    edit.apply();
                    playerHolder.playerPTS.setText(Integer.toString(pts));
                }
            });
            playerHolder.plusThree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pts = Integer.parseInt(playerHolder.playerPTS.getText().toString());
                    pts += 3;
                    SharedPreferences.Editor edit = sharedPref.edit();
                    edit.putInt("score", sharedPref.getInt("score",0)+pts);
                    edit.apply();
                    playerHolder.playerPTS.setText(Integer.toString(pts));
                }
            });
            row.setTag(playerHolder);
        }
        else {
            playerHolder = (PlayerHolder) row.getTag();
        }
        Player player = (Player) this.getItem(position);
        playerHolder.playerNumber.setText( "#"+Integer.toString(player.getNumber()));
        //playerHolder.playerPTS.setText(Integer.toString(pts));
        return row;
    }

    static class PlayerHolder{
        TextView playerNumber, playerPTS;
        Button plusOne, plusTwo, plusThree;
        TextView teamA, teamB;
    }
}