package me.nutchy.cine;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import me.nutchy.cine.fragment.LoginFragment;


public class MainActivity extends FragmentActivity {


    private LoginFragment loginFragment;
    CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callbackManager = CallbackManager.Factory.create();
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            // Add the fragment on initial activity setup
            loginFragment = new LoginFragment();


            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, new LoginFragment())
                    .commit();
        } else {

            // Or set the fragment from restored state info
            loginFragment = (LoginFragment) fragmentManager.findFragmentById(R.id.fragment_container);
        }

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                System.out.println("*"+ loginResult.getAccessToken().getToken()+" "+loginResult.getAccessToken().getUserId());

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        loginFragment.onActivityResult(requestCode, resultCode, data);
    }
}
