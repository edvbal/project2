package com.example.android.project2;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.project2.R.id.playerPTS;

/**
 * Created by Edvinas on 19/02/2017.
 * Custom ArrayAdapter class to adjust ListView row (row_player) which shows Player stats.
 * credits : PRABEESH R K https://www.youtube.com/watch?v=cyk_ht8z6IA&t=176s
 */

public class PlayerAdapter extends ArrayAdapter {
    private List list = new ArrayList();
    List<Integer> pts = new ArrayList<>();

    public PlayerAdapter(Context context, int resource) {
        super(context, resource);
    }

    SharedPreferences sharedPrefA = getContext().getSharedPreferences("scoreA", Context.MODE_PRIVATE);
    SharedPreferences sharedPrefB = getContext().getSharedPreferences("scoreB", Context.MODE_PRIVATE);
    SharedPreferences sharedPrefName = getContext().getSharedPreferences("nameA", Context.MODE_PRIVATE);
    boolean teamA;
    //int undoClicks;


    public void add(Player object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    public void remove() {
        list.clear();
        notifyDataSetChanged();
        pts.clear();
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        final PlayerHolder playerHolder;
        if (row == null) {
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
            playerHolder.delete = (Button) row.findViewById(R.id.delete);
            Player player = (Player) this.getItem(position);
            pts.add(player.getScore());
            //Toast.makeText(getContext(), player.getTeam(), Toast.LENGTH_SHORT).show();
            //Toast.makeText(getContext(), sharedPrefName.getString("nameB",""), Toast.LENGTH_SHORT).show();

            if (sharedPrefName.getString("nameA", "").equals(player.getTeam())) {
                teamA = true;
                playerHolder.delete.getBackground().setColorFilter(Color.parseColor("#dbf2d7"), PorterDuff.Mode.DARKEN);
                playerHolder.undo.getBackground().setColorFilter(Color.parseColor("#dbf2d7"), PorterDuff.Mode.DARKEN);
                playerHolder.reset.getBackground().setColorFilter(Color.parseColor("#dbf2d7"), PorterDuff.Mode.DARKEN);

                playerHolder.plusOne.getBackground().setColorFilter(Color.parseColor("#dbf2d7"), PorterDuff.Mode.DARKEN);
                playerHolder.plusTwo.getBackground().setColorFilter(Color.parseColor("#dbf2d7"), PorterDuff.Mode.DARKEN);
                playerHolder.plusThree.getBackground().setColorFilter(Color.parseColor("#dbf2d7"), PorterDuff.Mode.DARKEN);

            } else {
                teamA = false;
                playerHolder.delete.getBackground().setColorFilter(Color.parseColor("#d6e1f1"), PorterDuff.Mode.DARKEN);
                playerHolder.undo.getBackground().setColorFilter(Color.parseColor("#d6e1f1"), PorterDuff.Mode.DARKEN);
                playerHolder.reset.getBackground().setColorFilter(Color.parseColor("#d6e1f1"), PorterDuff.Mode.DARKEN);

                playerHolder.plusOne.getBackground().setColorFilter(Color.parseColor("#d6e1f1"), PorterDuff.Mode.DARKEN);
                playerHolder.plusTwo.getBackground().setColorFilter(Color.parseColor("#d6e1f1"), PorterDuff.Mode.DARKEN);
                playerHolder.plusThree.getBackground().setColorFilter(Color.parseColor("#d6e1f1"), PorterDuff.Mode.DARKEN);
            }
            //undoClicks = 0;
            playerHolder.plusOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //pts.add(Integer.parseInt(playerHolder.playerPTS.getText().toString()));
                    int ptss = Integer.parseInt(playerHolder.playerPTS.getText().toString());
                    pts.add(++ptss);
                    //Toast.makeText(getContext(),Integer.toString(pts.get(pts.size()-1)),Toast.LENGTH_SHORT).show();
                    if (teamA) {
                        SharedPreferences.Editor edit = sharedPrefA.edit();
                        edit.putInt("scoreA", sharedPrefA.getInt("scoreA", 0) + 1);
                        edit.apply();
                    } else {
                        SharedPreferences.Editor edit = sharedPrefB.edit();
                        edit.putInt("scoreB", sharedPrefB.getInt("scoreB", 0) + 1);
                        edit.apply();
                    }
                    /*for (int i = 0; i < pts.size(); i++){
                        Toast.makeText(getContext(),Integer.toString(1+i)+" "+Integer.toString(pts.get(i)),Toast.LENGTH_SHORT).show();
                    }*/
                    playerHolder.playerPTS.setText(Integer.toString(pts.get(pts.size() - 1)));
                }
            });
            playerHolder.plusTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //pts.add(Integer.parseInt(playerHolder.playerPTS.getText().toString()));
                    int ptss = Integer.parseInt(playerHolder.playerPTS.getText().toString());
                    ptss += 2;
                    pts.add(ptss);
                    if (teamA) {
                        SharedPreferences.Editor edit = sharedPrefA.edit();
                        edit.putInt("scoreA", sharedPrefA.getInt("scoreA", 0) + 2);
                        edit.apply();
                    } else {
                        SharedPreferences.Editor edit = sharedPrefB.edit();
                        edit.putInt("scoreB", sharedPrefB.getInt("scoreB", 0) + 2);
                        edit.apply();
                    }
                    /*for (int i = 0; i < pts.size(); i++){
                        Toast.makeText(getContext(),Integer.toString(1+i)+" "+Integer.toString(pts.get(i)),Toast.LENGTH_SHORT).show();
                    }*/
                    playerHolder.playerPTS.setText(Integer.toString(pts.get(pts.size() - 1)));
                }
            });
            playerHolder.plusThree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //pts.add(Integer.parseInt(playerHolder.playerPTS.getText().toString()));
                    int ptss = Integer.parseInt(playerHolder.playerPTS.getText().toString());
                    ptss += 3;
                    pts.add(ptss);
                    if (teamA) {
                        SharedPreferences.Editor edit = sharedPrefA.edit();
                        edit.putInt("scoreA", sharedPrefA.getInt("scoreA", 0) + 3);
                        edit.apply();
                    } else {
                        SharedPreferences.Editor edit = sharedPrefB.edit();
                        edit.putInt("scoreB", sharedPrefB.getInt("scoreB", 0) + 3);
                        edit.apply();
                    }
                    /*for (int i = 0; i < pts.size(); i++){
                        Toast.makeText(getContext(),Integer.toString(1+i)+" "+Integer.toString(pts.get(i)),Toast.LENGTH_SHORT).show();
                    }*/
                    playerHolder.playerPTS.setText(Integer.toString(pts.get(pts.size() - 1)));
                }
            });
            playerHolder.reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (teamA) {
                        SharedPreferences.Editor edit = sharedPrefA.edit();
                        edit.putInt("scoreA", sharedPrefA.getInt("scoreA", 0) - Integer.parseInt(playerHolder.playerPTS.getText().toString()));
                        edit.apply();
                    } else {
                        SharedPreferences.Editor edit = sharedPrefB.edit();
                        edit.putInt("scoreB", sharedPrefB.getInt("scoreB", 0) - Integer.parseInt(playerHolder.playerPTS.getText().toString()));
                        edit.apply();
                    }
                    pts.clear();
                    playerHolder.playerPTS.setText(Integer.toString(0));
                }
            });
            playerHolder.undo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Feature coming soon", Toast.LENGTH_SHORT).show();
                   /* //Toast.makeText(getContext(),Integer.toString(pts.size()),Toast.LENGTH_SHORT).show();

                    if (pts.size() - undoClicks <= 0){
                        Toast.makeText(getContext(),"There is nothing to undo",Toast.LENGTH_SHORT).show();
                    }else if (pts.size() - undoClicks > 0){
                        undoClicks +=1;
                        if (teamA){
                            SharedPreferences.Editor edit = sharedPrefA.edit();
                            edit.putInt("scoreA", pts.get(pts.size()-undoClicks-1));
                            edit.apply();
                        }
                        else {
                            SharedPreferences.Editor edit = sharedPrefB.edit();
                            edit.putInt("scoreB", pts.get(pts.size()-undoClicks-1));
                            edit.apply();
                        }
                        playerHolder.playerPTS.setText(Integer.toString(pts.get(pts.size()-undoClicks-1)));
                        //undoClicks = 0;
                    }*/
                }
            });
            playerHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Feature coming soon", Toast.LENGTH_SHORT).show();
                    /*list.remove(position);
                    pts.remove(position);
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
                    if (list.size() == 0)
                        playerHolder.playerPTS.setText("0");
                    else if (list.size() > 0){
                        playerHolder.playerPTS.setText(Integer.toString(pts.get(position+1)));
                    }
                    //Toast.makeText(getContext(),Integer.toString(list.size()),Toast.LENGTH_SHORT).show();
                    //playerHolder.playerPTS.setText("0");
                    notifyDataSetChanged();*/
                }
            });
            row.setTag(playerHolder);
        } else {
            playerHolder = (PlayerHolder) row.getTag();
        }
        Player player = (Player) this.getItem(position);
        playerHolder.playerNumber.setText("#" + Integer.toString(player.getNumber()));
        //pts = 0;
        return row;
    }

    public static class PlayerHolder {
        TextView playerNumber, playerPTS;
        Button plusOne, plusTwo, plusThree, reset, undo, delete;
    }
}