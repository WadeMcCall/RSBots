import SharedBotLib.StateMachine;

public class WarriorStateMachine extends StateMachine {

    WarriorStateMachine() {
        addState(States.ENTRY_STATE, new EntryState(this));
        addState(States.BANKING, new BankingState(this));
        addState(States.WALKING_TO_BANK, new WalkingToBankState(this));
        addState(States.LOOTING, new LootingState(this));
        addState(States.SPAWNING_NEW_ARMOR, new SpawningNewArmorState(this));
        addState(States.KILLING_ANIMATED_ARMOR, new KillingAnimatedArmorState(this));
        addState(States.WALKING_TO_ANIMATED_ARMOR, new WalkingToAnimatedArmorState(this));
        addState(States.WALKING_TO_CYCLOPS, new WalkingToCyclopsState(this));


        switchState(States.WALKING_TO_BANK);
    }

    public enum States {
        ENTRY_STATE,
        BANKING,
        WALKING_TO_BANK,
        WALKING_TO_ANIMATED_ARMOR,
        SPAWNING_NEW_ARMOR,
        KILLING_ANIMATED_ARMOR,
        LOOTING,
        WALKING_TO_CYCLOPS,
        LOOTING_CYCLOPS,
        KILLING_CYCLOPS
    }
}
