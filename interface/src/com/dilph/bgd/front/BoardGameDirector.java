package com.dilph.bgd.front;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;
import com.dilph.bgd.engine.Decision;
import com.dilph.bgd.engine.GameManager;

public class BoardGameDirector extends Activity {

    GameManager gameManager;
    private ViewSwitcher btnViewSwitcher;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btnViewSwitcher = ((ViewSwitcher)findViewById(R.id.viewSwitcher));        // We will need this often, so let's spare the lookup.
        gameManager = new GameManager();
        gameManager.start();
        Toast.makeText(this, "Click anywhere for next action", Toast.LENGTH_SHORT).show();
        updateMessage();
    }

    public void proceedAction(View view)
    {
        if(gameManager.getCurrentEvent() instanceof Decision){
            return;
        }
        gameManager.proceed();
        updateMessage();
    }

    public void responseTrue(View view)
    {
        gameManager.response(true);
        updateMessage();
    }


    private void updateMessage()
    {
        String textMessage =  gameManager.getCurrentEvent().getMessage();
        ((TextView)findViewById(R.id.messageField)).setText(textMessage);
        if(gameManager.getCurrentEvent() instanceof Decision)
        {
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            gameManager.response(true);

                            break;
                        case DialogInterface.BUTTON_NEGATIVE:
                            gameManager.response(false);

                            break;
                    }
                    updateMessage();

                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(textMessage).setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();

        }  else {

            ((TextView)findViewById(R.id.currentPlayerText)).setText(gameManager.getCurrentPlayer().getName());
        }
    }

    public void backToMenu(final View view)
    {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        Intent myIntent=new Intent(view.getContext(),MainMenu.class);
                        startActivity(myIntent);
                        finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked, no action:)
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    public void endTurn(View view)
    {
        gameManager.endTurn();
        updateMessage();
    }
}
