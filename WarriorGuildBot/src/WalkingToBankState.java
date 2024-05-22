import SharedBotLib.State;
import SharedBotLib.StateMachine;
import SharedBotLib.UserAreaService;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.widgets.message.Message;

public class WalkingToBankState extends State {
    Area bankArea;

    public WalkingToBankState(StateMachine sm) {
        super(sm);

        bankArea = UserAreaService.getAreaByName("WarriorsGuildBank");
    }

    @Override
    public void doAction() {
        if (bankArea.contains(Players.getLocal())) {
            if (Bank.isOpen()) {
                state_machine.switchState(WarriorStateMachine.States.BANKING);
                return;
            }

            GameObjects.closest(x -> x.hasAction("Bank")).interact();
            Sleep.sleepUntil(() -> Bank.isOpen(), 5000);
        } else {

            Walking.walk(bankArea.getRandomTile());
            Sleep.sleepUntil(() -> !Players.getLocal().isMoving() || Walking.getDestinationDistance() < 3 || bankArea.contains(Players.getLocal()), 10000);
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
