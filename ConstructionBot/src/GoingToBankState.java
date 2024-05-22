import SharedBotLib.State;
import SharedBotLib.StateMachine;
import SharedBotLib.UserAreaService;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.container.impl.equipment.EquipmentSlot;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.widgets.message.Message;

public class GoingToBankState extends State<ConstructionActivity> {
    public GoingToBankState(StateMachine<ConstructionActivity> sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        if (Bank.isOpen()) {
            state_machine.switchState(ConstructionStateMachine.States.BANKING);
            return;
        }

        if (!UserAreaService.getAreaByName("CastleWarsBank").contains(Players.getLocal())) {
            Equipment.interact(EquipmentSlot.RING, "Castle Wars");
            Sleep.sleepUntil(() -> UserAreaService.getAreaByName("CastleWarsBank").contains(Players.getLocal()), 3000);
            return;
        } else {
            GameObject chest = GameObjects.closest("Bank chest");
            if (chest == null)
                return;
            GameObjects.closest("Bank chest").interact("Use");
            Sleep.sleepUntil(() -> Bank.isOpen(), 10000);
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
