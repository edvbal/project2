package com.example.android.project2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.project2.R.id.playerPTS;

/**
 * Created by Edvinas on 19/02/2017.
 */

public class PlayerAdapter extends ArrayAdapter {
    List list = new ArrayList();
    int pts;
    TextView teamScoreB;
    TextView teamScoreA;
    EditText editTeamA;
    EditText editTeamB;

    MainActivity mainActivity = new MainActivity();
    public PlayerAdapter(Context context, int resource) {super(context,resource);}

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
        View row2;
        row = convertView;
        row2 = convertView;
        final PlayerHolder playerHolder;
        if (row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_player, parent, false);
            LayoutInflater layoutInflater2 = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row2 = layoutInflater2.inflate(R.layout.activity_main,parent,false);
            playerHolder = new PlayerHolder();

            teamScoreB = (TextView) row2.findViewById(R.id.scoreB);
            teamScoreA = (TextView) row2.findViewById(R.id.scoreA);

            editTeamA = (EditText) row2.findViewById(R.id.teamA);
            editTeamB = (EditText) row2.findViewById(R.id.teamB);

            playerHolder.playerNumber = (TextView) row.findViewById(R.id.playerNumber);
            playerHolder.playerPTS = (TextView) row.findViewById(playerPTS);
            playerHolder.plusOne = (Button) row.findViewById(R.id.plusOne);
            playerHolder.plusTwo = (Button) row.findViewById(R.id.plusTwo);
            playerHolder.plusThree = (Button) row.findViewById(R.id.plusThree);
            playerHolder.plusOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pts = Integer.parseInt(playerHolder.playerPTS.getText().toString());
                    pts++;
                    teamScoreB.setText(Integer.toString(pts));
                    playerHolder.playerPTS.setText(Integer.toString(pts));

                }

            });
            playerHolder.plusTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pts = Integer.parseInt(playerHolder.playerPTS.getText().toString());
                    pts += 2;
                    playerHolder.playerPTS.setText(Integer.toString(pts));

                }

            });
            playerHolder.plusThree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pts = Integer.parseInt(playerHolder.playerPTS.getText().toString());
                    pts += 3;
                    playerHolder.playerPTS.setText(Integer.toString(pts));

                }

            });

            row.setTag(playerHolder);
            Player player = (Player) this.getItem(position);
            player.setScore(pts);
        }
        else {
            playerHolder = (PlayerHolder) row.getTag();
        }
        Player player = (Player) this.getItem(position);
        //categoryHolder.tx_id.setText(categories.getId());
        playerHolder.playerNumber.setText( "#"+Integer.toString(player.getNumber()));
        playerHolder.playerPTS.setText(Integer.toString(0));
        //categoryHolder.tx_description.setText(categories.getDescription());

        return row;
    }

    static class PlayerHolder{
        TextView playerNumber, playerPTS;
        Button plusOne, plusTwo, plusThree;
    }
}

