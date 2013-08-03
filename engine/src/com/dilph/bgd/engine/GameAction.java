package com.dilph.bgd.engine;

/**
 * Created with IntelliJ IDEA.
 * User: pseudo
 * Date: 8/3/13
 * Time: 11:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class GameAction extends BaseEvent implements TurnEvent {


    private TurnEvent nextTurnEvent;
    private CounterAction counterAction;

    public GameAction(String message, TurnEvent nextTurnEvent) {
        super(message);
        this.nextTurnEvent = nextTurnEvent;
    }

    public GameAction(String message, TurnEvent nextTurnEvent, CounterAction counterAction) {
        super(message);
        this.nextTurnEvent = nextTurnEvent;
        this.counterAction = counterAction;
    }

    @Override
    public TurnEvent getNext(boolean response) {
        doAction();
        return nextTurnEvent;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private void doAction() {
        if(counterAction != null)   {
            counterAction.doAction();
        }

    }


}
