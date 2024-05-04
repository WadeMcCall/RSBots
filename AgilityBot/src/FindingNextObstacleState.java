import SharedBotLib.State;
import SharedBotLib.StateMachine;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.map.Map;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.Player;
import org.dreambot.api.wrappers.items.GroundItem;
import org.dreambot.api.wrappers.widgets.message.Message;

import java.util.Comparator;
import java.util.Optional;

public class FindingNextObstacleState extends State<AgilityActivity> {

    public FindingNextObstacleState(StateMachine<AgilityActivity> sm) {
        super(sm);
    }


    @Override
    public void doAction() {
        if (Dialogues.canContinue()) {
            Dialogues.continueDialogue();
            return;
        }

        Player me = Players.getLocal();

        if (Walking.getDestinationDistance() >= 7) {
            return;
        }

        GroundItem mark = GroundItems.closest("Mark of grace");
        if (mark != null && Map.canReach(mark.getTile())) {
            mark.interact("Take");
            Sleep.sleep(50,1200);
            Sleep.sleepUntil(() -> !me.isMoving(), 5000);
            return;
        }

        AgilityObstacleArea obstacle = state_machine.activity.agilityObstacles.stream().filter(area -> area.area.contains(me)).findFirst().orElse(null);

        if (obstacle == null) {
            GameObject startingObstacle = GameObjects.closest(state_machine.activity.agilityObstacles.get(0).nextObstacleID);
            if (startingObstacle != null && startingObstacle.isOnScreen()) {
                startingObstacle.interact();

                Sleep.sleep(50,400);
                Sleep.sleepUntil(() -> !state_machine.activity.agilityObstacles.get(0).area.contains(me), 6000);
                return;
            }
            if(me.canReach(state_machine.activity.agilityObstacles.get(0).area.getRandomTile())) {
                Walking.walk(state_machine.activity.agilityObstacles.get(0).area.getRandomTile());
            }

            return;
        }

        GameObject nextClick = GameObjects.closest(obstacle.nextObstacleID);
        if (nextClick =1= null)
            return;
        if (nextClick.distance() > 15) {
            Walking.walk(nextClick);
            return;
        }
        if (!nextClick.interact()) {
            Walking.walk(nextClick);
            return;
        }
        Sleep.sleepUntil(() -> !obstacle.area.contains(me), 15000);
        Sleep.sleep(50,1200);
    }6+3
    

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
