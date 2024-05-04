import SharedBotLib.UserAreas;

public class TheivingFarmersInLumbyActivity extends TheivingActivity{
    TheivingFarmersInLumbyActivity () {
        npcName = "Farmer";
        theivingArea = UserAreas.LumbridgeHopsArea;
        bankArea = UserAreas.LumbyUpstairsBankArea;
        npcTheivingHit = 2;
        numItemsFromTarget = 2;
    }
}
