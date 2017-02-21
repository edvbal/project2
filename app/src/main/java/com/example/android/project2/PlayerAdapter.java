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
 * Custom ArrayAdapter class to adjust ListView row (row_player) which shows Player stats.
 * credits : PRABEESH R K https://www.youtube.com/watch?v=cyk_ht8z6IA&t=176s
 */

public class PlayerAdapter extends ArrayAdapter{
    private List list = new ArrayList();
    int pts;
    public PlayerAdapter(Context context, int resource) {super(context,resource);}
    SharedPreferences sharedPrefA = getContext().getSharedPreferences("scoreA",Context.MODE_PRIVATE);
    SharedPreferences sharedPrefB = getContext().getSharedPreferences("scoreB",Context.MODE_PRIVATE);
    SharedPreferences sharedPrefName = getContext().getSharedPreferences("nameA", Context.MODE_PRIVATE);
    boolean teamA;


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
            playerHolder.reset = (Button) row.findViewById(R.id.reset);
            playerHolder.undo = (Button) row.findViewById(R.id.undo);

            Player player = (Player) this.getItem(position);
            pts = player.getScore();
            //Toast.makeText(getContext(), player.getTeam(), Toast.LENGTH_SHORT).show();
            //Toast.makeText(getContext(), sharedPrefName.getString("nameB",""), Toast.LENGTH_SHORT).show();

            if (sharedPrefName.getString("nameA","").equals(player.getTeam()))
                teamA = true;
            else teamA = false;

            playerHolder.plusOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pts = Integer.parseInt(playerHolder.playerPTS.getText().toString());
                    pts++;
                    if (teamA){
                        SharedPreferences.Editor edit = sharedPrefA.edit();
                        edit.putInt("scoreA", sharedPrefA.getInt("scoreA",0)+1);
                        edit.apply();
                    }else{
                        SharedPreferences.Editor edit = sharedPrefB.edit();
                        edit.putInt("scoreB", sharedPrefB.getInt("scoreB",0)+1);
                        edit.apply();
                    }
                    playerHolder.playerPTS.setText(Integer.toString(pts));
                }
            });
            playerHolder.plusTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pts = Integer.parseInt(playerHolder.playerPTS.getText().toString());
                    pts += 2;
                    if (teamA){
                        SharedPreferences.Editor edit = sharedPrefA.edit();
                        edit.putInt("scoreA", sharedPrefA.getInt("scoreA",0)+2);
                        edit.apply();
                    }else{
                        SharedPreferences.Editor edit = sharedPrefB.edit();
                        edit.putInt("scoreB", sharedPrefB.getInt("scoreB",0)+2);
                        edit.apply();
                    }
                    playerHolder.playerPTS.setText(Integer.toString(pts));
                }
            });
            playerHolder.plusThree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pts = Integer.parseInt(playerHolder.playerPTS.getText().toString());
                    pts += 3;
                    if (teamA){

                        SharedPreferences.Editor edit = sharedPrefA.edit();
                        edit.putInt("scoreA", sharedPrefA.getInt("scoreA",0)+3);
                        edit.apply();
                    }else{
                        SharedPreferences.Editor edit = sharedPrefB.edit();
                        edit.putInt("scoreB", sharedPrefB.getInt("scoreB",0)+3);
                        edit.apply();
                    }
                    playerHolder.playerPTS.setText(Integer.toString(pts));
                }
            });
            playerHolder.reset.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if (teamA){
                        SharedPreferences.Editor edit = sharedPrefA.edit();
                        edit.putInt("scoreA", sharedPrefA.getInt("scoreA",0)-Integer.parseInt(playerHolder.playerPTS.getText().toString()));
                        edit.apply();
                    }
                    else {
                        SharedPreferences.Editor edit = sharedPrefB.edit();
                        edit.putInt("scoreB", sharedPrefB.getInt("scoreB",0)-Integer.parseInt(playerHolder.playerPTS.getText().toString()));
                        edit.apply();
                    }
                    pts = 0;
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
        //pts = 0;
        return row;
    }

    static class PlayerHolder{
        TextView playerNumber, playerPTS;
        Button plusOne, plusTwo, plusThree, reset, undo;
        TextView teamA, teamB;
    }
    public void test(View view){

    }
}