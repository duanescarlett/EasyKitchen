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

public class StoryDialog extends DialogFragment implements View.OnClickListener{
    Button addStoryButton;
    EditText story;
    View view;
    ListView list;
    int counter = 0;
    //    static int i = 0;
    int i = 0;
    private static SharedPreferences sharedPreferences;
    private static final String DEFAULT = "Empty";
    Context c;

    public StoryDialog(){}

    public void StoryDialogInit(Context c){
        this.c = c;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.view = inflater.inflate(R.layout.story_dialog, null);
        this.addStoryButton = (Button) this.view.findViewById(R.id.addStory);
        this.story = (EditText) this.view.findViewById(R.id.story);
        this.list = (ListView) this.view.findViewById(R.id.inStoryList); // Object of the listView
        this.addStoryButton.setOnClickListener(this);
        this.sharedPreferences = getActivity().getSharedPreferences("Stories", Context.MODE_PRIVATE);
        return this.view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.addStory){
            String con = this.story.getText().toString();
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
        for(Map.Entry<String, ?> entry : keys.entrySet()){
            Toast.makeText(c, entry.getKey() + ": " + entry.getValue().toString(), Toast.LENGTH_SHORT).show();
            ingre[i] = entry.getValue().toString();
            i++;
        }
        list.setAdapter(new IngAdapter(c, ingre));
    }
}

