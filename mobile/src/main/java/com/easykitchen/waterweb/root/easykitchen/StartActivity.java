package com.easykitchen.waterweb.root.easykitchen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.easykitchen.waterweb.root.easykitchen.Frags.NavigationDrawerFragment;
import com.easykitchen.waterweb.root.easykitchen.Frags.SocialLogin;
import com.easykitchen.waterweb.root.easykitchen.Tools.CategoryDialog;
import com.easykitchen.waterweb.root.easykitchen.Tools.DosAdapter;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;

public class StartActivity extends ActionBarActivity {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private SocialLogin socialFrag;
    private ListView listView;
    private TextView mTextDetails;
    private Intent intAct;

    private CallbackManager callbackManager;
    public AccessTokenTracker accessTokenTracker;
    public ProfileTracker profileTracker;

    /**
     * Used to store the last screen title. For use in {@link #//restoreActionBar()}.
     */
    //private CharSequence mTitle;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        if(trackinTest() != false){
            View b = findViewById(R.id.login_button);
            b.setVisibility(View.GONE); // Quickly removes logout button
            b.destroyDrawingCache(); // Quick memory clean
            intAct = new Intent(StartActivity.this, DisplayActivity.class);
            startActivity(intAct);
        }
        else{
            Toast.makeText(this, "User is logged out", Toast.LENGTH_SHORT).show();
        }

        // Toolbar setup
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);// Set up the drawer.

        // ListView
        listView = (ListView) findViewById(R.id.drawerList);
        listView.setAdapter(new DosAdapter(this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "I guess only a string can be used", Toast.LENGTH_SHORT).show();

                if (position == 0) {
                    intAct = new Intent(StartActivity.this, ProfileActivity.class);
                    startActivity(intAct);
                }

            }

        });

        mTextDetails = (TextView) findViewById(R.id.text_details);

    }

    public boolean trackinTest() {
        FacebookSdk.sdkInitialize(this);
        this.callbackManager = CallbackManager.Factory.create();

        this.accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken accessToken, AccessToken accessToken2) {

            }
        };

        this.profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                //displayWelcomeMessage(newProfile);
            }
        };

        // Testing user state for true
        if (profileTracker != null && accessTokenTracker != null) {
            return true;
        }
        else{
            return false;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
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
            Toast.makeText(this, "Hey you just hit " + item.getTitle(), Toast.LENGTH_SHORT).show();
            return true;
        }

        if(id == R.id.navigate){
            startActivity(new Intent(this, CategoryDialog.class));
        }

        return super.onOptionsItemSelected(item);

    }

}
