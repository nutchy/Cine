package me.nutchy.cine.Api;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by nutchy on 30/11/2017 AD.
 */

public class FirebaseObserver {
    private static FirebaseObserver instance;
    private FirebaseObserver(){

    }

    public static FirebaseObserver getInstance() {
        if(instance == null){
            instance = new FirebaseObserver();
        }
        return instance;
    }
}
