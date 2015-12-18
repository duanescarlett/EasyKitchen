package com.easykitchen.waterweb.root.easykitchen.Tools;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.easykitchen.waterweb.root.easykitchen.Models.ListData;
import com.easykitchen.waterweb.root.easykitchen.R;

import java.util.ArrayList;

public class DosAdapter extends BaseAdapter {

    private ArrayList<ListData> list;
    private Context context;

    public DosAdapter(Context c) {
        this.context = c;
        this.list = new ArrayList<>();
        Resources res = c.getResources();
        String[] title = res.getStringArray(R.array.mainList);
        int[] image = {R.drawable.profile, R.drawable.categories, R.drawable.news, R.drawable.favorite, R.drawable.recipes, R.drawable.message};

        for(int i = 0; i < 6; i++){
            this.list.add(new ListData(title[i], image[i]));
        }
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        // Returns object's index value
        return this.list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 1 get the root view
        // 2 use the root view to find other views
        // 3 set the values

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.custom_row, parent, false);
        ImageView icon = (ImageView) row.findViewById(R.id.listIcon);
        TextView title = (TextView) row.findViewById(R.id.listText);
        ListData temp = list.get(position);
        title.setText(temp.title);
        icon.setImageResource(temp.iconId);

        row.callOnClick();

        return row; // Return the root view of custom_row.xml
    }


}
