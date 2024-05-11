package Quest.Actions;

import SharedBotLib.Utils;
import org.dreambot.api.methods.container.impl.Shop;
import org.dreambot.api.utilities.Sleep;

import java.util.function.Predicate;

public class BuyQuestAction extends QuestAction{
    String itemToBuy;
    int amount;

    public BuyQuestAction(String _item, int _amt, Predicate<BuyQuestAction> completionCheck) {
        super(a -> completionCheck.test((BuyQuestAction) a));
        itemToBuy = _item;
        amount = _amt;
    }

    @Override
    public ActionResult doAction() {
        if (!Shop.isOpen()) {
            return ActionResult.ERROR;
        }

        Shop.purchase(itemToBuy, amount);
        Sleep.sleep((int) Utils.getRandomGuassianDistNotNegative(600, 300));


        if (isComplete()) {
            return ActionResult.FINISH;
        }

        return ActionResult.CONTINUE;
    }
}
