import SharedBotLib.State;
import SharedBotLib.StateMachine;
import SharedBotLib.UserAreaService;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.prayer.Prayer;
import org.dreambot.api.methods.prayer.Prayers;
import org.dreambot.api.wrappers.widgets.message.Message;

public class WalkingToCyclopsState extends State {
    Area cyclopsArea;

    public WalkingToCyclopsState(StateMachine sm) {
        super(sm);

        cyclopsArea = UserAreaService.getAreaByName("WarriorsGuildCyclopsEntrance");
        
    }

    @Override
    public void doAction() {
        Prayers.toggle(!Prayers.isActive(Prayer.PROTECT_FROM_MAGIC), Prayer.PROTECT_FROM_MAGIC);
    }

    @Override
    public void Enter() {

    }

    @Override
    public void Exit() {

    }

    @Override
    public void chatMessageRecieved(Message message) {

    }
}
