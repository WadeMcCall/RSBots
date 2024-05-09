import SharedBotLib.UserAreas;

public class MasterFarmerInDraynorActivity extends TheivingActivity {
    MasterFarmerInDraynorActivity() {
        bankArea = UserAreas.DraynorBank;
        theivingArea = UserAreas.DraynorMarket;
        npcTheivingHit = 3;
        npcName = "Master Farmer";
        numItemsFromTarget = 35;
    }
}
