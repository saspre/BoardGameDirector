package com.dilph.bgd.engine;

/**
 * Created with IntelliJ IDEA.
 * User: pseudo
 * Date: 8/3/13
 * Time: 11:58 AM
 * To change this template use File | Settings | File Templates.
 */

/**
 * A TurnEvent represents events during a turn, such as GameActions and Decisions (or ifs).
 */
public interface TurnEvent {

    String getMessage();


    TurnEvent getNext(boolean response);
}
