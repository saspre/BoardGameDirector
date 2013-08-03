package com.dilph.bgd.engine;

/**
 * Created with IntelliJ IDEA.
 * User: pseudo
 * Date: 8/3/13
 * Time: 5:10 PM
 * To change this template use File | Settings | File Templates.
 */

/**
 * An action which can be performed by a GameAction to increment or decrement a counter.
 */
public class CounterAction {
        enum Action { INCREMENT, DECREMENT }
        String name;
        Action action;

    public CounterAction(String name, Action action) {
        this.name = name;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void doAction()
    {
        if(action == Action.INCREMENT)
        {
             CounterManager.getInstance().increment(name);
        }   else
        {
            CounterManager.getInstance().decrement(name);
        }
    }
}
