package com.dilph.bgd.front;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: pseudo
 * Date: 8/4/13
 * Time: 11:10 PM
 * Activity to manage the players before starting a game.
 */
public class PlayerManagement extends Activity {



    class ViewHolder
    {
        EditText caption;

    }



    private ListView playerList;
    private PlayerListAdapter mAdapter;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playersetup);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Load playerList from sharedPreferences
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.sharedpreffile), Context.MODE_PRIVATE);
        Set<String> players = sharedPref.getStringSet(getString(com.dilph.bgd.front.R.string.playerlist), new HashSet<String>());

        mAdapter = new PlayerListAdapter(this,players);

        playerList = (ListView) findViewById(R.id.playerList);
        playerList.setFocusable(false);
        playerList.setClickable(false);
        playerList.setAdapter(mAdapter);
        playerList.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        playerList.setChoiceMode(1);



    }




    @Override
    protected void onResume() {
        super.onResume();    //To change body of overridden methods use File | Settings | File Templates.
        if(mAdapter.getTextViews().size() == 0)
        {
            mAdapter.addNew();
        }

    }

    public void addPlayer(View view)
    {
        mAdapter.saveAll();
        mAdapter.addNew();
    }

    public void clearPlayers(View view)
    {
        mAdapter.clearPlayers();
        mAdapter.addNew();
    }

    public void save(View view)
    {
        mAdapter.saveAll();
        HashSet<String> finalPlayerSet = new HashSet<String>();
        for(String pl : mAdapter.getPlayerNames())
        {
             if(!pl.isEmpty())    {
                finalPlayerSet.add(pl);
             }
        }
        if(finalPlayerSet.isEmpty())
        {
            Toast.makeText(this,"You need to add at least one player!",Toast.LENGTH_SHORT).show();
            return;

        }
        // We save the player list in the settings, such that next time the app is loaded the player list remains (assuming you often play the same people together)
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.sharedpreffile), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putStringSet(getString(R.string.playerlist), finalPlayerSet);
        editor.commit();


        Intent myIntent = new Intent(view.getContext(), BoardGameDirector.class);
        startActivity(myIntent);
        finish();
    }


    private  class PlayerListAdapter extends ArrayAdapter {


        private final LayoutInflater mInflater;

        public ArrayList<String> playerNames = new ArrayList<String>();
        public ArrayList<EditText> textViews = new ArrayList<EditText>();

        public PlayerListAdapter(Context c, Set<String> playerNames ) {
            super(c, R.layout.edit_text_item);
            this.playerNames = new ArrayList<String>(playerNames);
            mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        private ArrayList<EditText> getTextViews() {
            return textViews;
        }

        public synchronized void addNew()
        {
            playerNames.add("");
            notifyDataSetChanged();
        }

        public int getCount() {
            return playerNames.size();
        }

        public Object getItem(int position) {
            return playerNames.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public ArrayList<String> getPlayerNames() {
            return playerNames;
        }

        public synchronized View  getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.edit_text_item, null);

                holder.caption = (EditText) convertView.findViewById(R.id.ItemCaption);
                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();

            }


            try {
                textViews.set(position, holder.caption);
            } catch (IndexOutOfBoundsException e) {
                textViews.add(position,holder.caption);
                holder.caption.requestFocus();    // If it is added it is new, and we set in focus.
                playerList.smoothScrollToPosition(mAdapter.getCount() -1);   // Scroll to newly added position
            }

            holder.caption.setText(playerNames.get(position));
            holder.caption.setId(position);


            holder.caption.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus)
                    {
                        final int position = v.getId();
                        final EditText caption = (EditText) v;
                        playerNames.set(position, caption.getText().toString());
                    }
                }
            });

            return convertView;
        }

        public synchronized void clearPlayers()
        {
            textViews.clear();
            this.playerNames.clear();
            notifyDataSetChanged();
        }

        public synchronized void saveAll()
        {
            for(EditText et : this.getTextViews())
            {
                final int position = et.getId();
                String text =     et.getText().toString();
                playerNames.set(position,text);
            }
        }
    }


}