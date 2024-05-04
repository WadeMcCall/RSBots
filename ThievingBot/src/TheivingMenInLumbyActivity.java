import SharedBotLib.UserAreas;

public class TheivingMenInLumbyActivity extends TheivingActivity{
    public TheivingMenInLumbyActivity() {
        npcName = "Man";
        theivingArea = UserAreas.InFrontOfLumbridgeCastleArea;
        bankArea = UserAreas.LumbyUpstairsBankArea;
        npcTheivingHit = 1;
        numItemsFromTarget = 1;
    }
}
