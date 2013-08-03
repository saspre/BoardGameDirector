package com.dilph.bgd.engine;

/**
 * Created with IntelliJ IDEA.
 * User: pseudo
 * Date: 8/3/13
 * Time: 11:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class GameManager {


    Turn  turn;

    public void start()
    {
        CounterManager.getInstance().put("draws", new Counter(true,0));


        GameAction lastActions = new GameAction("Discard to 7 Card. \n Infect cities", new EndTurnEvent());
        Decision isSecondDrawDecision = new Decision(new CounterCondition("draws", CounterCondition.Condition.GTE,2));
        GameAction   pull1cardAction  = new GameAction("Pull 1 card from the player deck",
                                                          new Decision("Is Epidemic?",
                                                                    new GameAction("Raise Inf. Rate \nDraw buttom IC \nShuffle Deck",isSecondDrawDecision),
                                                                  isSecondDrawDecision),
                                                          new CounterAction("draws", CounterAction.Action.INCREMENT)

                                                       );


        isSecondDrawDecision.setTrueTurnEvent(lastActions);
        isSecondDrawDecision.setFalseTurnEvent(pull1cardAction);



        turn = new Turn( new GameAction("Do 4 Actions", pull1cardAction) );

        turn.start();
    }

    public void proceed()
    {
        turn.next(false);
    }

    public void response(boolean response)
    {
        turn.next(response);
    }

    public TurnEvent getCurrentEvent()
    {
        TurnEvent current = turn.getCurrentTurnEvent();
        if(current == null)
        {
            turn.reset();
        }

        while(current instanceof Decision && !((Decision)current).requiresHumanInteraction())
        {
            turn.next(false);
            current = turn.getCurrentTurnEvent();
        }


        return turn.getCurrentTurnEvent();
    }
}
