package com.dilph.bgd.engine;

/**
 * Created with IntelliJ IDEA.
 * User: pseudo
 * Date: 8/3/13
 * Time: 11:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class Turn {


    private final TurnEvent initialTurnEvent;
    TurnEvent currentTurnEvent;

    public Turn(TurnEvent turnEvent) {
        initialTurnEvent = currentTurnEvent = turnEvent;
    }
    

    public TurnEvent getCurrentTurnEvent() {
        return currentTurnEvent;
    }

    public void start() {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void next(boolean response) {
        currentTurnEvent = (currentTurnEvent).getNext(response);


    }

    /**
     * Resets the turn to the initialturnEvent
     */
    public void reset() {
           currentTurnEvent = initialTurnEvent;
           CounterManager.getInstance().resetTurnCounters();
    }
}
