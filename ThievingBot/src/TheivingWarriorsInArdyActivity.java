import SharedBotLib.UserAreas;

public class TheivingWarriorsInArdyActivity extends TheivingActivity{
    TheivingWarriorsInArdyActivity() {
        npcName = "Warrior";
        theivingArea = UserAreas.ArdyWarriorsArea;
        bankArea = UserAreas.ArdyEastBank;
        npcTheivingHit = 2;
        numItemsFromTarget = 1;
    }
}
