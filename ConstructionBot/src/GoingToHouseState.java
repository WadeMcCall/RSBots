import SharedBotLib.State;
import SharedBotLib.StateMachine;
import SharedBotLib.UserAreaService;
import SharedBotLib.Utils;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.Player;
import org.dreambot.api.wrappers.widgets.message.Message;

public class GoingToHouseState extends State<ConstructionActivity> {
    public GoingToHouseState(StateMachine<ConstructionActivity> sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        Area castleWarsBank = UserAreaService.getAreaByName("CastleWarsBank");
        Area rimmingtonPortal = new Area(2951, 3227, 2960, 3218);
        if(castleWarsBank.contains(Players.getLocal())) {
            Inventory.get("Teleport to house").interact("Outside");
            Sleep.sleepUntil(() -> !UserAreaService.getAreaByName("CastleWarsBank").contains(Players.getLocal()), 4000);
            return;
        }
        GameObject portal = GameObjects.closest(15478);
        if (!castleWarsBank.contains(Players.getLocal()) && !rimmingtonPortal.contains(Players.getLocal())) {
            state_machine.switchState(ConstructionStateMachine.States.BUILDING);
            return;
        } else {
            if (portal == null)
                return;
            portal.interact("Build mode");
            Sleep.sleepUntil(() -> GameObjects.closest(4525) != null, 6000);
            return;
        }
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
