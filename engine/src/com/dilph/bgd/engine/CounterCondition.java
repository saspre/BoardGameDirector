package com.dilph.bgd.engine;

/**
 * Created with IntelliJ IDEA.
 * User: pseudo
 * Date: 8/3/13
 * Time: 5:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class CounterCondition {
    enum Condition { EQUAL, LTE,GTE, LT, GT }
    Condition condition;
    String counterName;
    int value ;

    public CounterCondition(String counterName, Condition condition, int value) {
        this.condition = condition;
        this.counterName = counterName;
        this.value = value;
    }

    public boolean checkCondition()
    {
         int otherValue = CounterManager.getInstance().getCount(counterName);
         switch(condition)
         {
             case EQUAL:
                return otherValue == value;

             case GTE:
                return otherValue >= value;

             default:
                 return false;
         }
    }
}
