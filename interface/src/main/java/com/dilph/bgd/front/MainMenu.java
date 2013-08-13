package com.dilph.bgd.front;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created with IntelliJ IDEA.
 * User: pseudo
 * Date: 8/3/13
 * Time: 8:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainMenu extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }

    public void newGame(View view)
    {
        Intent myIntent=new Intent(view.getContext(),PlayerManagement.class);
        startActivity(myIntent);
        finish();
    }
}