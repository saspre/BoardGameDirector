package com.dilph.bgd.front;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.dilph.bgd.engine.Player;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: pseudo
 * Date: 8/4/13
 * Time: 11:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerManagement extends Activity {

    public class MyAdapter extends ArrayAdapter {
        private LayoutInflater mInflater;
        public ArrayList<ListItem> myItems = new ArrayList<ListItem>();
        public ArrayList<EditText> textViews = new ArrayList<EditText>();
        private int highestPosition = 0;

        public MyAdapter(Context c) {
            super(c, R.layout.edit_text_item);
            mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

              //  addNew(true);


        }

        public ArrayList<EditText> getTextViews() {
            return textViews;
        }

        public void addNew()
        {

            addNew(false);

        }
        public void addNew(boolean isFirst)
        {

            ListItem listItem = new ListItem();
            listItem.caption = "" + highestPosition++;
            myItems.add(listItem);

            notifyDataSetChanged();

            try {
           // textViews.get(highestPosition -1).requestFocus();
            } catch (IndexOutOfBoundsException e)
            {
                Log.e("DDDD", "Index", e);
            }

        }

        public int getCount() {
            return myItems.size();
        }

        public Object getItem(int position) {
            return myItems.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
           // highestPosition = Math.max(highestPosition, position);
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.edit_text_item, null);

                holder.caption = (EditText) convertView.findViewById(R.id.ItemCaption);

                convertView.setTag(holder);

            //    convertView.setTag(holder);
               // holder.caption.requestFocus();
              //  playerList.scrollTo();
            } else {
                holder = (ViewHolder) convertView.getTag();

            }
          //  if(position == highestPosition -1)
            //    textViews.add(position,holder.caption);


            try {

                textViews.set(position, holder.caption);
            } catch (IndexOutOfBoundsException e) {


                textViews.add(position,holder.caption);
            }


            holder.caption.setText(myItems.get(position).caption);

            holder.caption.setId(position);
            Log.d("DDDD","Highest Position " + highestPosition + " position: " + position + " caption: " + holder.caption.getText().toString());
            Log.d("DDDD", " Text Views: " + textViews);

            Log.d("DDDD", " Text View 1: " + textViews.get(0).getText());
  //          Log.d("DDDD", " Text Views 2: " + textViews.get(1).getText());
            /*
            if(position == highestPosition -1 )
            {
                Log.d("DDDD","Position " + position);
                holder.caption.requestFocus();
            } */



            //Fill EditText with the value you have in data source

          //  holder.caption.requestFocus();
            //we need to update adapter once we finish with editing
            holder.caption.setOnFocusChangeListener(new View.OnFocusChangeListener() {
               public void onFocusChange(View v, boolean hasFocus) {
                   if (!hasFocus)
                   {
                        final int position = v.getId();
                        final EditText caption = (EditText) v;
                        myItems.get(position).caption = caption.getText().toString();
                        Log.d("DDDD", "In focus position is: " + position + " caption: " + caption.getText().toString() );

                   }
                }
            });

            return convertView;
        }
    }

    class ViewHolder {
        EditText caption;

    }

    class ListItem {
        String caption;

    }

    private ListView playerList;
    private MyAdapter mAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.playersetup);


        playerList = (ListView) findViewById(R.id.playerList);
         mAdapter = new MyAdapter(this);//this,android.R.layout.simple_list_item_1);



        playerList.setClickable(true);
        playerList.setAdapter(mAdapter);
        playerList.setChoiceMode(1);


    }


    public void addPlayer(View view)
    {






        mAdapter.addNew();
 //       playerList.setSelection(mAdapter.getCount() -1);
        playerList.smoothScrollToPosition(mAdapter.getCount() -1);
//        Log.d("DDDD", "LAST visible position " + playerList.getLastVisiblePosition());

       // playerList.setSelection(playerList.getAdapter().getCount()-1);
       // ((ListItem)playerList.getViewAtPosition(mAdapter.getCount() -1)).caption;
     //   ((View)playerList.getSelectedItem()).requestFocus();
        //playerList.getSelectedView().requestFocus();
 //       try {
            //Log.d("DDDD", "mAdapter.getCount() -1: " + (mAdapter.getCount() -1) );
       //     Log.d("DDDD", "Childat: " + (mAdapter.getCount() -1) + " caption: " + ((EditText)((LinearLayout)(mAdapter.getCount() -1)).getChildAt(0)).getText());
            //((EditText)((LinearLayout)playerList.getChildAt(playerList.getChildCount() -1)).getChildAt(0)).requestFocus();

  //      }   catch(Exception e)
   //     {
     //       Log.e("DDDD", "Error", e);
   //     }
       // playerList.getChildAt(playerList.getChildCount() -1)
       // int wantedPosition = playerList.getLastVisiblePosition(); // Whatever position you're looking for
       // int firstPosition = playerList.getFirstVisiblePosition() - playerList.getHeaderViewsCount(); // This is the same as child #0
      //  int wantedChild = wantedPosition - firstPosition;
// Say, first visible position is 8, you want position 10, wantedChild will now be 2
// So that means your view is child #2 in the ViewGroup:

// Could also check if wantedPosition is between listView.getFirstVisiblePosition() and listView.getLastVisiblePosition() instead.
      //  View wantedView = playerList.getChildAt(playerList.getHeaderViewsCount() -1);
      //  wantedView.requestFocus();
    }


    public void save(View view)
    {
        ListView playerListView = (ListView) findViewById(R.id.playerList);
        ArrayList<Player> playerList = new ArrayList<Player>();
        for(int i = 1; i < playerListView.getChildCount(); i++)
        {
            playerList.add(new Player( ((EditText)playerListView.getChildAt(i)).getText().toString()));
        }



        Intent myIntent=new Intent(view.getContext(),BoardGameDirector.class);
    //    myIntent.put
        startActivity(myIntent);
        finish();
    }
}