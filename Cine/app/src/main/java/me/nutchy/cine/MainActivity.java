package me.nutchy.cine;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import me.nutchy.cine.fragment.LoginFragment;


public class MainActivity extends FragmentActivity {


    private LoginFragment loginFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        loginFragment.onActivityResult(requestCode, resultCode, data);
    }
}
