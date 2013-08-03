package com.dilph.bgd.engine;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: pseudo
 * Date: 8/3/13
 * Time: 4:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class CounterManager {


    static private CounterManager counterManager;

    private HashMap<String, Counter> counters;


    public static CounterManager getInstance()
    {
        if(counterManager == null)
        {
            counterManager = new CounterManager();
        }
        return counterManager;
    }


    public CounterManager()
    {
        counters = new HashMap<String,Counter>();
    }

    public Counter put(String key, Counter value) {
        return counters.put(key, value);
    }

    public void increment(String counterName)
    {
        counters.get(counterName).increment() ;
    }

    public void decrement(String counterName)
    {
        counters.get(counterName).decrement() ;
    }

    public void setCount(String counterName, int i)
    {
        counters.get(counterName).setCount(i);
    }

    public int getCount(String counterName)
    {
        return counters.get(counterName).getCount();
    }

    public void resetCounter(String counterName)
    {
        setCount(counterName, 0);
    }


    public void resetTurnCounters() {
        for(Counter counter : counters.values())
        {
            if(counter.isResetOnNewTurn())
            {
                counter.reset();
            }
        }
    }
}
