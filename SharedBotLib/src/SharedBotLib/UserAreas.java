package SharedBotLib;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;

public class UserAreas {
    public static final Area ArdyWarriorsArea = new Area(2625, 3303, 2638, 3296);
    public static final Area ArdyEastBank = new Area(2652, 3287, 2655, 3280);

    public static final Area AlKharidFishingArea = new Area(3265, 3150, 3277, 3140);
    public static final Area AlKharidBank = new Area(3265, 3173, 3271, 3161);
    public static final Area DraynorFishingArea = new Area(3080, 3237, 3087, 3226);
    public static final Area DraynorBank = new Area(3092, 3245, 3096, 3241);

    public static final Area EdgevilleMenArea = new Area(3091, 3513, 3099, 3507);
    public static final Area EdgevillBankArea = new Area(3097, 3498, 3091, 3494);
    public static final Area WestVarrockBankArea = new Area(3179, 3448, 3191, 3432);
    public static final Area WestVarrockNormalTreeArea = new Area(3149, 3465, 3171, 3449);
    public static final Area WestVarrockMiningArea = new Area(3182, 3376, 3177, 3369);
    public static final Area SouthGrandExchange = new Area(3159, 3494, 3170, 3484);
    public static final Area LumbyOakTreeArea = new Area(
            new Tile[] {
                    new Tile(3198, 3213, 0),
                    new Tile(3192, 3213, 0),
                    new Tile(3192, 3248, 0),
                    new Tile(3207, 3248, 0),
                    new Tile(3207, 3237, 0),
                    new Tile(3200, 3236, 0)
            }
    );
    public static final Area LumbyUpstairsBankArea = new Area(3207, 3219, 3210, 3218, 2);
    public static final Area LumbyRangeArea = new Area(3205, 3216, 3209, 3213);
    public static final Area InFrontOfLumbridgeCastleArea = new Area(3231, 3210, 3235, 3224);
    public static final Area LumbridgeHopsArea = new Area(3225, 3302, 3232, 3309);

    public static final Area HillGiantsFoundryArea = new Area(3369, 3156, 3383, 3148);

    public static final Area LargeGuardianRemainsSouth = new Area(3637, 9506, 3642, 9498);
    public static final Area GuardiansWorkbench = new Area(3610, 9486, 3612, 9489);
    public static final Area GuardianAltars = new Area(3608, 9510, 3622, 9496);
    public static final Area GuardianRemainsEast = new Area(3624, 9493, 3630, 9488);
    public static final Area GuardianRemainsWest = new Area(3603, 9490, 3601, 9492);
    public static final Area GuardiansFullArea = new Area(3585, 9520, 3645, 9461);
    public static final Area GuardianPortalEastArea = new Area(3629, 9505, 3632, 9501);
    public static final Area GuardianPortalWestArea = new Area(3600, 9505, 3597, 9501);
    public static final Area GuardianPortalSouthArea = new Area(3613, 9486, 3617, 9490);
    public static final Area HugeGuardianRemains = new Area(3592, 9498, 3588, 9507);

    public static final Area SeersMapleTrees = new Area(2730, 3499, 2720, 3503);
    public static final Area SeersBank = new Area(2730, 3499, 2720, 3503);

    public static final Area WestVarrockGuards = new Area(3172, 3424, 3177, 3430);
    public static final Area EdgevilleMossGiants = new Area(3153, 9909, 3165, 9898);
    public static final Area EdgevilleChillMossGiants = new Area(3161, 9876, 3168, 9882);
}
