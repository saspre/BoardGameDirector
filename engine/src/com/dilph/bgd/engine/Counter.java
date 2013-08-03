package com.dilph.bgd.engine;

/**
 * Created with IntelliJ IDEA.
 * User: pseudo
 * Date: 8/3/13
 * Time: 5:01 PM
 * To change this template use File | Settings | File Templates.
 */
class Counter
{
    private int count;
    private int initial;
    private boolean resetOnNewTurn;


    public Counter(boolean resetOnNewTurn, int initial) {

        this.initial = count = initial;
        this.resetOnNewTurn = resetOnNewTurn;
    }

    public Counter(boolean resetOnNewTurn) {

        this(resetOnNewTurn,0);
    }


    public Counter()
    {
        this(false,0);
    }

    public void increment()
    {
        ++count;
    }

    public void reset()
    {
        count = initial;
    }

    public void decrement()
    {
        --count;
    }

    public void setCount(int count) {
        this.count = count;
    }



    public int getCount() {
        return count;
    }


    public boolean isResetOnNewTurn() {
        return resetOnNewTurn;
    }
}