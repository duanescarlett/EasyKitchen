package com.easykitchen.waterweb.root.easykitchen.Tools;

import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.easykitchen.waterweb.root.easykitchen.R;

import java.util.Map;

public class DirectionDialog extends DialogFragment implements View.OnClickListener {

    View view;
    Button addDirectionButton;
    EditText direction;
    ListView list;
    static int i;
    private static SharedPreferences sharedPreferences;
    private static final String DEFAULT = "Empty";
    Context c;

    public DirectionDialog() {}

    public void DirectionDialogInit(Context c) {this.c = c;}

    @Override
    public void onClick(View v) {

        this.i++;

        if(v.getId() == R.id.adddirection){
            String con = direction.getText().toString();
            String key = "" + i;

            SharedPreferences.Editor editor = this.sharedPreferences.edit();
            editor.putString(key, con);

            editor.commit();
            dismiss();

        }
        else{
            dismiss();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        ListView ing = (ListView) getActivity().findViewById(R.id.inList);
        String count;
        String[] ingre = {
                "null", "null", "null", "null", "null",
                "null", "null", "null", "null", "null",
                "null", "null", "null", "null", "null",
                "null", "null", "null", "null", "null",
        };

        Map<String, ?> keys = this.sharedPreferences.getAll();

        if(keys != null){
            for(Map.Entry<String, ?> entry : keys.entrySet()){
//                Toast.makeText(c, entry.getKey() + ": " + entry.getValue().toString(), Toast.LENGTH_SHORT).show();
                ingre[i] = entry.getValue().toString();
                i++;
            }
        }
        list.setAdapter(new IngAdapter(c, ingre));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.view = inflater.inflate(R.layout.direction_dialog, null);
        this.addDirectionButton = (Button) this.view.findViewById(R.id.adddirection);
        this.direction = (EditText) this.view.findViewById(R.id.direction);
        this.list = (ListView) this.view.findViewById(R.id.inDirList); // Object of the listView
        this.addDirectionButton.setOnClickListener(this);
        this.sharedPreferences = getActivity().getSharedPreferences("Directions", Context.MODE_PRIVATE);
        return this.view;
    }

}
