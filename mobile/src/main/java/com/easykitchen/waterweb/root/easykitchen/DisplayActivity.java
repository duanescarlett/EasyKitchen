package com.easykitchen.waterweb.root.easykitchen;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.easykitchen.waterweb.root.easykitchen.Frags.NavigationDrawerFragment;
import com.easykitchen.waterweb.root.easykitchen.Tools.DosAdapter;


public class DisplayActivity extends ActionBarActivity {

    private NavigationDrawerFragment navFrag;
    //private CharSequence mTitle;
    private Toolbar toolBar;
    private ListView navList;
    private Intent intAct;
    private TextView mTextDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        View b = (View) findViewById(R.id.login_button);
        b.setVisibility(View.GONE); // Qickly removes logout button
        b.destroyDrawingCache(); // Quick memory clean

        // Toolbar setup
        toolBar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        navFrag = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        navFrag.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolBar);// Set up the drawer.

        // ListView
        navList = (ListView) findViewById(R.id.drawerList);
        navList.setAdapter(new DosAdapter(this));
        navList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getApplicationContext(), "I guess only a string can be used", Toast.LENGTH_SHORT).show();

            if (position == 0) {
                DisplayActivity.this.intAct = new Intent(DisplayActivity.this, ProfileActivity.class);
                startActivity(intAct);
            }
            else if (position == 1) {
                DisplayActivity.this.intAct = new Intent(DisplayActivity.this, StartActivity.class);
                startActivity(intAct);
            }
            else if (position == 2) {
                DisplayActivity.this.intAct = new Intent(DisplayActivity.this, NewsFeedActivity.class);
                startActivity(DisplayActivity.this.intAct);
            }
            else if (position == 3) {
                DisplayActivity.this.intAct = new Intent(DisplayActivity.this, MyFavoritesActivity.class);
                startActivity(DisplayActivity.this.intAct);
            }
            else if (position == 4) {
                DisplayActivity.this.intAct = new Intent(DisplayActivity.this, MyRecipesActivity.class);
                startActivity(DisplayActivity.this.intAct);
            }
            else if (position == 5) {
                DisplayActivity.this.intAct = new Intent(DisplayActivity.this, MessagesActivity.class);
                startActivity(DisplayActivity.this.intAct);
                }

            }

        });

        this.mTextDetails = (TextView) findViewById(R.id.text_details);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!navFrag.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            //restoreActionBar();
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
