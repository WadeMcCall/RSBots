package SharedBotLib;

import jdk.jshell.spi.ExecutionControl;
import org.dreambot.api.script.event.impl.ExperienceEvent;
import org.dreambot.api.wrappers.widgets.message.Message;

public abstract class State<A extends Activity> {
    public StateMachine<A> state_machine;

    public State(StateMachine<A> sm) {
        state_machine = sm;
    }

    public abstract void doAction();
    public abstract void Enter();
    public abstract void Exit();
    public abstract void chatMessageRecieved(Message message);

    public void onGameTick()  { }
    public void onPreTick()  { }
    public void onXPGained(ExperienceEvent event)  { }
    public void onLevelChange(ExperienceEvent event)  { }
    public void onLevelUp(ExperienceEvent event)  { }
}
