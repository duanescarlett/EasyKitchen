package com.easykitchen.waterweb.root.easykitchen;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.easykitchen.waterweb.root.easykitchen.Frags.NavigationDrawerFragment;
import com.easykitchen.waterweb.root.easykitchen.Tools.CategoryDialog;
import com.easykitchen.waterweb.root.easykitchen.Tools.DosAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ProfileActivity extends ActionBarActivity {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private ListView listView;
    Intent intAct;
    private Toolbar toolbar;
    private ImageView proPic;
    private URL img_value = null;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Toolbar
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Navigation Drawer
        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

        // ListView
        listView = (ListView) findViewById(R.id.drawerList);
        listView.setAdapter(new DosAdapter(this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "I guess only a string can be used", Toast.LENGTH_SHORT).show();

                if(position == 0){
                    intAct = new Intent(ProfileActivity.this, ProfileActivity.class);
                    startActivity(intAct);
                }
                else if (position == 1) {
                    ProfileActivity.this.intAct = new Intent(ProfileActivity.this, CategoryDialog.class);
                    startActivity(ProfileActivity.this.intAct);
                }
                else if (position == 2) {
                    ProfileActivity.this.intAct = new Intent(ProfileActivity.this, NewsFeedActivity.class);
                    startActivity(ProfileActivity.this.intAct);
                }
                else if (position == 3) {
                    ProfileActivity.this.intAct = new Intent(ProfileActivity.this, MyFavoritesActivity.class);
                    startActivity(ProfileActivity.this.intAct);
                }
                else if (position == 4) {
                    ProfileActivity.this.intAct = new Intent(ProfileActivity.this, MyRecipesActivity.class);
                    startActivity(ProfileActivity.this.intAct);
                }
                else if (position == 5) {
                    ProfileActivity.this.intAct = new Intent(ProfileActivity.this, MessagesActivity.class);
                    startActivity(ProfileActivity.this.intAct);
                }

            }

        });

        // Access Profile Data
//        Profile profile = Profile.getCurrentProfile();
//
//        if(profile != null){
//            Uri profileUri = profile.getProfilePictureUri(45, 45);
//            // Get the URI string
//            String st = profile.getId();
            //proPic = (ImageView) findViewById(R.id.facebookProImg);

            //img_value = new URL("https://graph.facebook.com/"+st+"/picture");
            //proPic.setImageURI(img_value);
            //Bitmap bit = getBitmapFromURL("https://graph.facebook.com/"+st+"/picture");
            //proPic.setImageBitmap(bit);

            //Toast.makeText(getActivity(), st, Toast.LENGTH_SHORT).show();
//            final Button recipeButton = (Button) findViewById(R.id.createRecipe);
//            recipeButton.setOnClickListener(new View.OnClickListener(){
//
//                @Override
//                public void onClick(View v) {
//                    ProfileActivity.this.intAct = new Intent(ProfileActivity.this, AddRecipeActivity.class);
//                }
//            });

        //}

    }

    private Bitmap getBitmapFromURL(String url){
        try{
            URL u = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) u.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBit = BitmapFactory.decodeStream(input);
            return myBit;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void AddRecipeMeth(View v){

        ProfileActivity.this.intAct = new Intent(ProfileActivity.this, AddRecipeActivity.class);
        startActivity(ProfileActivity.this.intAct);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, "Hey you just hit the arrow" + item.getTitle(), Toast.LENGTH_SHORT).show();
            return true;
        }

        if(id == R.id.navigate){
            startActivity(new Intent(this, AddRecipeActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

}
