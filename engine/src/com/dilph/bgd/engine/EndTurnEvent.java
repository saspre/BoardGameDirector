package com.dilph.bgd.engine;

/**
 * Created with IntelliJ IDEA.
 * User: pseudo
 * Date: 8/3/13
 * Time: 12:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class EndTurnEvent extends BaseEvent implements TurnEvent {


    public EndTurnEvent() {
        super("Turn ended.");
    }

    @Override
    public TurnEvent getNext(boolean response) {
        return null;
    }
}
