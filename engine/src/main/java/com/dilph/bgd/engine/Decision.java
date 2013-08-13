package com.dilph.bgd.engine;

/**
 * Created with IntelliJ IDEA.
 * User: pseudo
 * Date: 8/3/13
 * Time: 11:09 AM
 * To change this template use File | Settings | File Templates.
 */

/**
 * A Decision represents something  that should be determined either by the counter and conditions or the player.
 */
public class Decision extends BaseEvent implements TurnEvent {



    TurnEvent trueTurnEvent;
    TurnEvent falseTurnEvent;
    CounterCondition counterCondition;

    public Decision(String message, TurnEvent trueTurnEvent, TurnEvent falseTurnEvent)
    {
         super(message);
         this.trueTurnEvent = trueTurnEvent;
         this.falseTurnEvent = falseTurnEvent;
    }

    public Decision(String s) {
        super(s);
    }

    public Decision(CounterCondition counterCondition, TurnEvent trueTurnEvent, TurnEvent falseTurnEvent) {
        super("Not a user decision");
        this.trueTurnEvent = trueTurnEvent;
        this.falseTurnEvent = falseTurnEvent;
        this.counterCondition = counterCondition;
    }

    public Decision(CounterCondition counterCondition) {
        super("not a user decision");
        this.counterCondition = counterCondition;
    }

    public void setTrueTurnEvent(TurnEvent trueTurnEvent) {
        this.trueTurnEvent = trueTurnEvent;
    }

    public void setFalseTurnEvent(TurnEvent falseTurnEvent) {
        this.falseTurnEvent = falseTurnEvent;
    }

    @Override
    public TurnEvent getNext(boolean response) {


        if(!requiresHumanInteraction())
        {
             response = evaluate();
        }

        if(response)
        {
            return this.trueTurnEvent;
        }  else {
            return this.falseTurnEvent;
        }




    }

    private boolean evaluate() {
        if(counterCondition != null)
        {
            return counterCondition.checkCondition();
        }
        return false;
    }

    public boolean requiresHumanInteraction() {
         return counterCondition == null;
    }
}
