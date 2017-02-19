package com.example.android.project2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edvinas on 19/02/2017.
 */

public class PlayerAdapter extends ArrayAdapter {
    List list = new ArrayList();
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
        row = convertView;
        PlayerHolder playerHolder;
        if (row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_player, parent, false);
            playerHolder = new PlayerHolder();
            playerHolder.playerNumber = (TextView) row.findViewById(R.id.playerNumber);
            row.setTag(playerHolder);
        }
        else {
            playerHolder = (PlayerHolder) row.getTag();
        }
        Player player = (Player) this.getItem(position);
        //categoryHolder.tx_id.setText(categories.getId());
        playerHolder.playerNumber.setText( "#"+Integer.toString(player.getNumber()));
        //categoryHolder.tx_description.setText(categories.getDescription());

        return row;
    }

    static class PlayerHolder{
        TextView playerNumber;
    }
}

