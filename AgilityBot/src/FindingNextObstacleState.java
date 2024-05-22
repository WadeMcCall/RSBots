import SharedBotLib.State;
import SharedBotLib.StateMachine;
import SharedBotLib.Utils;
import org.dreambot.api.methods.combat.Combat;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Map;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.tabs.Tabs;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.script.ScriptManager;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.Player;
import org.dreambot.api.wrappers.items.GroundItem;
import org.dreambot.api.wrappers.widgets.message.Message;

import java.util.Comparator;
import java.util.Optional;
import java.util.Random;

public class FindingNextObstacleState extends State<AgilityActivity> {

    public FindingNextObstacleState(StateMachine<AgilityActivity> sm) {
        super(sm);
    }
    private int previousHealthPercent = 100;
    Random rand = new Random();
    private static final Area wildernessSpikesArea = new Area(2987, 10367, 3008, 10338);


    @Override
    public void doAction() {
        Player me = Players.getLocal();
        if (state_machine.activity.isWilderness && wildernessSpikesArea.contains(me)) {
            GameObjects.closest("Ladder").interact();
            Sleep.sleepUntil(() -> !wildernessSpikesArea.contains(me), 7000);
            GameObjects.closest(23132).interact();
            Sleep.sleepUntil(() -> !me.isMoving() && !me.isAnimating(), 7000);
            return;
        }

        if (Dialogues.canContinue()) {
            Dialogues.continueDialogue();
            return;
        }

        int healthPercent = Combat.getHealthPercent();
        if (healthPercent != previousHealthPercent) {
            previousHealthPercent = healthPercent;
            healthChanged();
        }

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
            Area startingArea = state_machine.activity.agilityObstacles.get(0).area;
            GameObject startingObstacle = GameObjects.closest(state_machine.activity.agilityObstacles.get(0).nextObstacleID);
            if (startingArea.contains(me)) {
                if (state_machine.activity.isWilderness) {
                    if (Players.all().size() > 1) {
                        Tabs.logout();
                        ScriptManager.getScriptManager().stop();
                    }
                    GameObjects.closest(53224).interact("Tag");
                    Sleep.sleepUntil(() -> !me.isMoving(), (int)Utils.getRandomGuassianDistNotNegative(7000, 1000));
                    Sleep.sleep((int)Utils.getRandomGuassianDistNotNegative(100, 30));
                }
                startingObstacle.interact();

                Sleep.sleep(50,400);
                Sleep.sleepUntil(() -> !startingArea.contains(me), 6000);
            } else {
                if (state_machine.activity.isWilderness) {
                    GameObjects.closest(53224).interact("Tag");
                    Sleep.sleep((int)Utils.getRandomGuassianDistNotNegative(600, 30));
                    Sleep.sleepUntil(() -> !me.isMoving(), (int)Utils.getRandomGuassianDistNotNegative(7000, 1000));
                    Sleep.sleep((int)Utils.getRandomGuassianDistNotNegative(100, 30));
                    startingObstacle.interact();
                    return;
                }
                Walking.walk(startingArea.getRandomTile());
                Sleep.sleepUntil(() -> startingArea.contains(me) || Walking.getDestinationDistance() < 3, 9000);
            }

            return;
        }

        GameObject nextClick = GameObjects.closest(obstacle.nextObstacleID);
        if (nextClick == null)
            return;
        if (nextClick.distance() > 15) {
            Walking.walk(nextClick);
            return;
        }
        if (!nextClick.interact()) {
            Walking.walk(nextClick);
            return;
        }

        Sleep.sleepUntil(() -> !obstacle.area.contains(me) && !me.isAnimating(), (int)Utils.getRandomGuassianDistNotNegative(7000,1000));
        Sleep.sleep((int)Utils.getRandomGuassianDistNotNegative(100,30));
    }


    private void healthChanged() {

        double rangeMin = 0.0;
        double rangeMax = 1.0;

        if (Utils.getCurrentHealthAsInt() <= (10)) {
            Utils.Eat();
            return;
        }
        if (previousHealthPercent < 70) {
            double rD = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
            if (rD > .7) {
                Utils.Eat();
                return;
            }
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
