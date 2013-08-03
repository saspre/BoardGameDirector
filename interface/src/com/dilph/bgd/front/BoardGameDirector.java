package com.dilph.bgd.front;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
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
        updateMessage();
    }

    public void proceedAction(View view)
    {
        gameManager.proceed();
        updateMessage();
    }

    public void responseTrue(View view)
    {
        gameManager.response(true);
        updateMessage();
    }
    public void responseFalse(View view)
    {
        gameManager.response(false);
        updateMessage();
    }

    private void updateMessage()
    {
        ((TextView)findViewById(R.id.messageField)).setText(gameManager.getCurrentEvent().getMessage());
        if(gameManager.getCurrentEvent() instanceof Decision)
        {
            if(btnViewSwitcher.getCurrentView().getId() == R.id.actionBtnView )
            {
                btnViewSwitcher.showNext();
            }
        }  else
        {
            // either GameAction or EndOfTurnEvent
            if(btnViewSwitcher.getCurrentView().getId() == R.id.decisionBtnView )
            {
                btnViewSwitcher.showNext();
            }
        }

        
        //showNext();
    }
}
