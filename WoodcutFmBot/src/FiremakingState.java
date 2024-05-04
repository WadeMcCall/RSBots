import SharedBotLib.Activity;
import SharedBotLib.State;
import SharedBotLib.StateMachine;
import SharedBotLib.Utils;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.api.wrappers.widgets.message.Message;

public class FiremakingState extends State<Activity> {
    boolean moveSpots;

    public FiremakingState(StateMachine<Activity> sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        if (Dialogues.canContinue())
            Dialogues.continueDialogue();

        if (Players.getLocal().isAnimating()) {
            return;
        }
        if (!Inventory.contains(n -> !n.getName().contains("logs") && !n.getName().contains("Logs"))) {
            state_machine.switchState(WoodcutFMStateMachine.States.FINDING_TREE);
            return;
        }

        if (moveSpots) {
            Tile randomTile = Utils.getRandomEmptyNearbyTile();
            Walking.walk(randomTile);
            Sleep.sleepUntil(() -> !Players.getLocal().isMoving(), 1200);
            Sleep.sleep(50, 800);
            moveSpots = false;
            return;
        }

        Item logs = Inventory.get(x -> x.getName().contains("logs") || x.getName().contains("Logs"));

        if (logs == null) {
            return;
        }

        Inventory.interact(logs, "Use");
        Inventory.interact("Tinderbox", "Use");
        Sleep.sleep(200,800);

        Sleep.sleepUntil(() -> !Players.getLocal().isAnimating(), 1000, 50);
        Sleep.sleep(50,700);
    }

    @Override
    public void Enter() {
        moveSpots = false;
    }

    @Override
    public void Exit() {

    }

    @Override
    public void chatMessageRecieved(Message message) {
        Logger.log(Logger.LogType.INFO, message.getMessage());
        if (message.getMessage().contains("can't light a fire")) {
            moveSpots = true;
        }
    }
}
