package com.dilph.bgd.front;

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
import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: pseudo
 * Date: 8/4/13
 * Time: 11:10 PM
 * To change this template use File | Settings | File Templates.
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

        playerList = (ListView) findViewById(R.id.playerList);
        mAdapter = new PlayerListAdapter(this);
        // Load playerList from sharedPreferences.

        playerList.setFocusable(false);
        playerList.setClickable(false);
        playerList.setAdapter(mAdapter);
        playerList.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        playerList.setChoiceMode(1);
    }


    public void addPlayer(View view)
    {
        mAdapter.addNew();
    }

    @Override
    protected void onResume() {
        super.onResume();    //To change body of overridden methods use File | Settings | File Templates.
        if(mAdapter.getTextViews().size() == 0)
        {
            mAdapter.addNew();
        }

    }

    public void save(View view)
    {

        // We save the player list in the settings, such that next time the app is loaded the player list remains (assuming you often play the same people together)
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putStringSet(getString(R.string.playerlist), new HashSet<String>(mAdapter.getPlayerNames()));
        editor.commit();

        Intent myIntent=new Intent(view.getContext(),BoardGameDirector.class);
        startActivity(myIntent);
        finish();
    }


    private class PlayerListAdapter extends ArrayAdapter {


        private final LayoutInflater mInflater;

        public ArrayList<String> playerNames = new ArrayList<String>();
        public ArrayList<EditText> textViews = new ArrayList<EditText>();

        public PlayerListAdapter(Context c) {
            super(c, R.layout.edit_text_item);
            mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public ArrayList<EditText> getTextViews() {
            return textViews;
        }

        public void addNew()
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

        public View getView(int position, View convertView, ViewGroup parent) {
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
    }


}