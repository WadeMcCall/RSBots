import org.dreambot.api.methods.map.Area;
import SharedBotLib.UserAreas;
import SharedBotLib.Utils;

public class FightingMenInEdgevilleActivity extends FightingActivity{
    FightingMenInEdgevilleActivity() {
        fightingArea = UserAreas.EdgevilleMenArea;
        bankArea = UserAreas.EdgevillBankArea;
        enemyMaxHit = 1;
        enemyName = "Man";
        shouldLoot = false;
    }
}
