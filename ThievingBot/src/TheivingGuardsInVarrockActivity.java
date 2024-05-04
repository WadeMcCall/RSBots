import SharedBotLib.UserAreas;

public class TheivingGuardsInVarrockActivity extends TheivingActivity {

    TheivingGuardsInVarrockActivity () {
        npcName = "Guard";
        theivingArea = UserAreas.WestVarrockGuards;
        bankArea = UserAreas.WestVarrockBankArea;
        npcTheivingHit = 2;
        numItemsFromTarget = 1;
        minHealingFood = 3;
    }
}
