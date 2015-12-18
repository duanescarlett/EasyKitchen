package com.easykitchen.waterweb.root.easykitchen.Tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.easykitchen.waterweb.root.easykitchen.R;

import java.util.ArrayList;

public class IngAdapter extends BaseAdapter {

    ArrayList<SingleRow> list;
    Context context;
    int count = 0; // length test
    String[] ingredient = {
            "null", "null", "null", "null", "null",
            "null", "null", "null", "null", "null",
            "null", "null", "null", "null", "null",
            "null", "null", "null", "null", "null",
    };

    IngAdapter(Context c, String[] s){
        //this.l = s.length;
        this.context = c;

        list = new ArrayList<SingleRow>();

        for(String single : s){

            if(single != "null" && single != ""){
//                Toast toast = Toast.makeText(c, ingredient[count], Toast.LENGTH_SHORT);
//                toast.show();
                list.add(new SingleRow(single));
            }
            count++;
        }

    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) { // This is for the database
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.custom_row_ing, parent, false);
        ImageView img = (ImageView) row.findViewById(R.id.listIcon);
        TextView ing = (TextView) row.findViewById(R.id.listText);

        // Get a single row object
        SingleRow temp = list.get(position); // Create a fresh object to return

        ing.setText(temp.ingredient);
        img.setImageResource(temp.image);

        return row;
    }
}
