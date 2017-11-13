package me.nutchy.cine.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import me.nutchy.cine.R;

public class LoginFragment extends Fragment {

    // Tag for debug
    private static String TAG = LoginFragment.class.getSimpleName();

    LoginButton loginButton;
    CallbackManager callbackManager;
    TextView tvStatus;

    public static LoginFragment newInstance(){
        return new LoginFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootview = inflater.inflate(R.layout.fragment_login, container, false);

        tvStatus = rootview.findViewById(R.id.login_status);
        loginButton = rootview.findViewById(R.id.login_button);

        loginButton.setReadPermissions("email");
        loginButton.setReadPermissions("public_profile");

        // If using in a fragment
        loginButton.setFragment(this);

        callbackManager = CallbackManager.Factory.create();

        // Callback registration
        // Use Fragment don't need to Register Callback Manager on Login Manager
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String accessToken= loginResult.getAccessToken().getToken();
                Log.d(TAG, ""+loginResult);
                tvStatus.setText(accessToken);
            }

            @Override
            public void onCancel() {
                tvStatus.setText("Status : Cancel");
            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        return rootview;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
