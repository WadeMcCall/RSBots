package SharedBotLib;

import org.dreambot.api.utilities.Logger;
import org.dreambot.api.wrappers.widgets.message.Message;

import java.util.HashMap;
import java.util.Map;

public class StateMachine<A extends Activity> {
    public StateMachine() {}

    private State currentState;
    public A activity;

    private Map<Object, State> stateMap = new HashMap<>();

    public void addState(Object stateId, State state) {
        stateMap.put(stateId, state);
    }

    public void switchState(Object newStateId) {
        State newState = stateMap.get(newStateId);
        if (newState == null) {
            throw new IllegalArgumentException("State not found: " + newStateId);
        }
        if (currentState != null) {
            currentState.Exit();
        }

        Logger.log(Logger.LogType.INFO, "new state: " + newStateId.toString());
        currentState = newState;
        currentState.Enter();
    }

    public void chatMessageRecieved(Message message) {
        currentState.chatMessageRecieved(message);
    }


    public void doAction() {
        currentState.doAction();
    }
}
