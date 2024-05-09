import SharedBotLib.Utils;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;

public class SimpleCombatQuestAction extends QuestAction{
    public int enemyMaxHit;
    public String enemyName;

    SimpleCombatQuestAction(int _enemyMaxHit, String _enemyName) {
        enemyMaxHit = _enemyMaxHit;
        enemyName = _enemyName;
    }

    @Override
    public ActionResult doAction() {
        if (!Players.getLocal().isInCombat()) {
            return ActionResult.FINISH;
        }
        if (Utils.getCurrentHealthAsInt() < (int)(enemyMaxHit * 1.5)) {
            Utils.Eat();
        }
        NPCs.closest(enemyName).interact("Attack");
        return ActionResult.CONTINUE;
    }
}
