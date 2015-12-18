package com.easykitchen.waterweb.root.easykitchen;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.easykitchen.waterweb.root.easykitchen.Frags.NavigationDrawerFragment;
import com.easykitchen.waterweb.root.easykitchen.Network.VolleySingleton;
import com.easykitchen.waterweb.root.easykitchen.Tools.CategoryDialog;
import com.easykitchen.waterweb.root.easykitchen.Tools.DirectionDialog;
import com.easykitchen.waterweb.root.easykitchen.Tools.MyDialog;
import com.easykitchen.waterweb.root.easykitchen.Tools.StoryDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddRecipeActivity extends ActionBarActivity {

    Context c = this;
    private NavigationDrawerFragment navFrag;
    String[] ingredientArr = new String[30];
    private Toolbar toolBar;
    private ListView navList;
    private Intent intention;
    private String url = "http://roadofenlightenment.com/";
    SharedPreferences sharedPreferencesIngredients;
    SharedPreferences sharedPreferencesDirections;
    SharedPreferences sharedPreferencesStories;
    SharedPreferences sharedPreferencesCategory;
    SharedPreferences sharedPreferencesType;
    private static final String DEFAULT = "Empty";
    int i = 0;
    private CategoryDialog categoryDialog;

    String[] ingre = {
            "null", "null", "null", "null", "null",
            "null", "null", "null", "null", "null",
            "null", "null", "null", "null", "null",
            "null", "null", "null", "null", "null",
    };
    String[] dir = {
            "null", "null", "null", "null", "null",
            "null", "null", "null", "null", "null",
            "null", "null", "null", "null", "null",
            "null", "null", "null", "null", "null",
    };
    String[] story = {
            "null", "null", "null", "null", "null",
            "null", "null", "null", "null", "null",
            "null", "null", "null", "null", "null",
            "null", "null", "null", "null", "null",
    };
    String[] cat = {
            "null", "null", "null", "null", "null",
            "null", "null", "null", "null", "null",
            "null", "null", "null", "null", "null",
            "null", "null", "null", "null", "null",
    };

    static int counter = 0;

    @Override
    protected void onSaveInstanceState(Bundle state){
        super.onSaveInstanceState(state);
        state.putInt("counter", counter);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.get("counter");
    }

    @Override
    protected void onResume(){
        super.onResume();
        counter++;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(counter == 0){
            //**** Ingredients ****//
            sharedPreferencesIngredients = getSharedPreferences("Ingredients", 0);
            SharedPreferences.Editor editor = sharedPreferencesIngredients.edit();
            editor.clear();
            editor.commit();

            //**** Directions ****//
            sharedPreferencesDirections = getSharedPreferences("Directions", 0);
            SharedPreferences.Editor editorDir = sharedPreferencesDirections.edit();
            editorDir.clear();
            editorDir.commit();

            //**** Directions ****//
            sharedPreferencesStories = getSharedPreferences("Stories", 0);
            SharedPreferences.Editor editorStory = sharedPreferencesStories.edit();
            editorStory.clear();
            editorStory.commit();

            //**** Type ****//
            sharedPreferencesType = getSharedPreferences("DishType", 0);
            SharedPreferences.Editor editorType = sharedPreferencesType.edit();
            editorType.clear();
            editorType.commit();
        }

        setContentView(R.layout.activity_add_recipe);

        // Toolbar setup
        toolBar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setHomeButtonEnabled(true);
        // This sets the return or back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Disable Keyboard
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id == R.id.cat_navigate){
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

    public void showDialog(View v){
        Thread t1 = new Thread(new ThreadOne());
        t1.start();
    }

    public void showDirDialog(View v){
        Thread t1 = new Thread(new ThreadTwo());
        t1.start();
    }

    public void showStoryDialog(View v){
        Thread t1 = new Thread(new ThreadThree());
        t1.start();
    }

    public void showCategoryDialog(View v){
        Thread t1 = new Thread(new ThreadFour());
        t1.start();
    }

    // Insert into database
    private boolean dbInsert(){

        url = "http://volleyHelp.org/post";

        Map<String, ?> keys = this.sharedPreferencesIngredients.getAll();
        for(Map.Entry<String, ?> entry : keys.entrySet()){
            //Toast.makeText(c, entry.getKey() + ": " + entry.getValue().toString(), Toast.LENGTH_SHORT).show();
            ingre[i] = entry.getValue().toString();
            i++;
        }

        i = 0;
        keys = this.sharedPreferencesDirections.getAll();
        for(Map.Entry<String, ?> entry : keys.entrySet()){
            dir[i] = entry.getValue().toString();
            i++;
        }

        i = 0;
        keys = this.sharedPreferencesStories.getAll();
        for(Map.Entry<String, ?> entry : keys.entrySet()){
            story[i] = entry.getValue().toString();
            i++;
        }

        i = 0;
        keys = this.sharedPreferencesCategory.getAll();
        for(Map.Entry<String, ?> entry : keys.entrySet()){
            cat[i] = entry.getValue().toString();
            i++;
        }

        RequestQueue requesQueue = VolleySingleton.getsInstance().getmRequestQueue();
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
            new Response.Listener<String>()
            {
                @Override
                public void onResponse(String response) {
                    // response
                    Log.d("Response", response);
                    try {
                        JSONObject jsonResponse = new JSONObject(response).getJSONObject("form");
                        String site = jsonResponse.getString("site"),
                                network = jsonResponse.getString("network");
                        System.out.println("Site: "+site+"\nNetwork: "+network);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Log.d("Error.Response", "something");
                }
            }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", "Alif");
                params.put("domain", AddRecipeActivity.this.url);

                return params;
            }
        };
        requesQueue.add(postRequest);
        //Volley.newRequestQueue(this).add(postRequest);
        return true;

    }
    
    // A Thread for the dialog
    private class ThreadOne implements Runnable{
        @Override
        public void run() {
            FragmentManager manager = getFragmentManager();
            MyDialog myDialog = new MyDialog();
            myDialog.MyDialogInit(c);
            myDialog.show(manager, "MyDialog");
        }
    }

    private class ThreadTwo implements Runnable{
        @Override
        public void run(){
            FragmentManager manager = getFragmentManager();
            DirectionDialog dialog = new DirectionDialog();
            dialog.DirectionDialogInit(c);
            dialog.show(manager, "DirectionDialog");
        }
    }

    private class ThreadThree implements Runnable{
        @Override
        public void run(){
            FragmentManager manager = getFragmentManager();
            StoryDialog storyDialog = new StoryDialog();
            storyDialog.StoryDialogInit(c);
            storyDialog.show(manager, "StoryDialog");
        }
    }

    private class ThreadFour implements Runnable{
        @Override
        public void run(){
            AddRecipeActivity.this.categoryDialog = new CategoryDialog();
            //categoryDialog.CategoryDialogInit(c);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.add_recipe, categoryDialog, "expandList");
            fragmentTransaction.commit();
        }
    }

}
