package com.easykitchen.waterweb.root.easykitchen.Frags;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.easykitchen.waterweb.root.easykitchen.ProfileActivity;
import com.easykitchen.waterweb.root.easykitchen.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SocialLogin#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SocialLogin extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private TextView mTextDetails;
    public AccessTokenTracker accessTokenTracker;
    public ProfileTracker profileTracker;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SocialLogin.
     */
    // TODO: Rename and change types and number of parameters
    public static SocialLogin newInstance(String param1, String param2) {
        SocialLogin fragment = new SocialLogin();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SocialLogin() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        FacebookSdk.sdkInitialize(getActivity());
        this.callbackManager = CallbackManager.Factory.create();

        this.accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken accessToken, AccessToken accessToken2) {

            }
        };

        this.profileTracker = new ProfileTracker(){
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                //displayWelcomeMessage(newProfile);
            }
        };

        // User Tracking
        this.accessTokenTracker.startTracking();
        this.profileTracker.startTracking();
    }

    @Override
    public void onStop(){
        super.onStop();
        // Stop User Tracking
        this.accessTokenTracker.stopTracking();
        this.profileTracker.stopTracking();
    }

    @Override
    public void onResume(){
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_social_login, container, false);

        loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_friends");
        loginButton.setFragment(SocialLogin.this);
        // Other app specific settings

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                View b = getActivity().findViewById(R.id.login_button);
                b.setVisibility(View.GONE); // Qickly removes logout button
                b.destroyDrawingCache(); // Quick memory clean

                // Access Profile Data
                Profile profile = Profile.getCurrentProfile();
                if(profile != null){
                    Uri profileUri = profile.getProfilePictureUri(45, 45);
                    String st = profileUri.getPath();// Get the URI string
                    //Toast.makeText(getActivity(), st, Toast.LENGTH_SHORT).show();
                    Intent go = new Intent(getActivity(), ProfileActivity.class);
                    startActivity(go);
                }

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {

            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
