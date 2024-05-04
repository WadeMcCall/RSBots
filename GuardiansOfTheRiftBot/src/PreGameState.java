import SharedBotLib.Activity;
import SharedBotLib.State;
import SharedBotLib.StateMachine;
import SharedBotLib.UserAreas;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.methods.widget.Widget;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.Player;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.script.listener.ChatListener;
import org.dreambot.api.wrappers.widgets.WidgetChild;
import org.dreambot.api.wrappers.widgets.message.Message;

import java.util.Objects;

public class PreGameState extends State<Activity> {
    private int agilityLevel;
    private boolean gameStarted;

    public PreGameState(StateMachine<Activity> sm) {
        super(sm);
    }

    @Override
    public void doAction() {
        if (gameStarted) {
            state_machine.switchState(GuardiansStateMachine.States.MINING_FRAGMENTS_PRE_GAME);
            return;
        }

        if (UserAreas.HugeGuardianRemains.contains(Players.getLocal())) {
            GameObjects.closest("Portal").interact();
            Sleep.sleepUntil(() -> !UserAreas.HugeGuardianRemains.contains(Players.getLocal()), 5000);
            return;
        }

        Area startArea = agilityLevel >= 56 ? UserAreas.LargeGuardianRemainsSouth : UserAreas.GuardianRemainsEast;
        Player me = Players.getLocal();

        if (startArea == UserAreas.GuardianRemainsEast) {
            if (startArea.contains(me) || me.isMoving()) {
                return;
            }
        }


        if (agilityLevel >= 56) {
            Tile standingTile = new Tile(3639, 9500, 0);
            if (startArea.contains(me) && standingTile.distance() != 0) {
                Walking.walkExact(standingTile);
                Sleep.sleepUntil(() -> standingTile.distance() == 0, 4000);
                return;
            } else if (startArea.contains(me)){
                return;
            }
            if (!GameObjects.closest("Rubble").interact()) {
                Walking.walk(UserAreas.GuardianRemainsEast);
                Sleep.sleepUntil(() -> startArea.contains(me), 10000);
                return;
            }
        } else {

            Walking.walk(startArea);
        }
    }

    @Override
    public void Enter() {
        agilityLevel = Skills.getRealLevel(Skill.AGILITY);
        gameStarted = false;
    }

    @Override
    public void Exit() {

    }

    @Override
    public void chatMessageRecieved(Message message) {
        onGameMessage(message);
    }

    public void onGameMessage(Message message) {
        if (message.getMessage().contains(GuardiansWidgetTextureIDs.gameBeginsText)) {
            Logger.log(Logger.LogType.INFO, message.getMessage());
            gameStarted = true;
        }
    }
}
