import com.sun.xml.internal.bind.v2.model.annotation.Quick;
import nox.scripts.noxscape.core.api.QuickExchange;
import nox.scripts.noxscape.util.Sleep;
import nox.scripts.noxscape.util.Trader;

import org.osbot.rs07.api.GrandExchange;
import org.osbot.rs07.api.Inventory;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;



@ScriptManifest(author = "anykeys", name="Feathers", version = 0.0, info = "", logo = "")
public class AnyFeathers  extends Script {


    private QuickExchange ge;
    private Trader trader;
    private int state = 0;

    private final Position[] locations = { new Position(3013,3223, 0), new Position(3164, 3485, 0)};
    @Override
    public void onStart() throws InterruptedException {
        ge = new QuickExchange(this);
        trader = new Trader(this);
    }

    @Override
    public int onLoop() throws InterruptedException {

        Item coins = getInventory().getItem("Coins");
        Item packs = getInventory().getItem("Feather pack");
        Item featherpack = getInventory().getItem("Feather pack");
        NPC gerrant = getNpcs().closest("Gerrant");
        RS2Widget shop = getWidgets().get(300, 16, 9);



        if(coins == null)
            stop(true);

        if(coins.getAmount() > 200 && state != 1)
            state = 1;




        if(state == 0 ) // at GE
        {

            if( !myPlayer().getArea(5).contains( new Position(3164, 3485, 0)))
            {

                if(featherpack != null)
                    featherpack.interact("Open");

                Sleep.until(() -> featherpack == null || featherpack.getAmount() < 1, 3000);

                if(featherpack == null)
                    walking.webWalk(new Position(3164, 3485, 0));
            }
            else
                state = 2;

        }else if( state == 1) // at Shop
        {
             if(shop == null) {
                if(gerrant != null && featherpack == null) {
                    gerrant.interact("Trade");
                    Sleep.until(() -> !myPlayer().isMoving() && packs == null, 3000);

                }else {

                    if(packs != null)
                    packs.interact("Open");
                    Sleep.until(() -> packs == null, 5000);
                }
                if(!myPosition().getArea(10).contains(new Position(3013, 3223, 0)))
                    walking.webWalk(new Position(3013, 3223, 0));

            }else
            {
                if(inventory.isFull())
                {
                    if(shop != null)
                        getWidgets().get(300, 1, 11).interact("Close");

                    if( packs != null )
                        Sleep.until(() -> packs.interact("Open"), 5000);

                }
                else
                {

                    if(coins.getAmount() > 200 && shop.getItemAmount() == 100)
                        shop.interact("Buy 10");
                    else
                    {
                        Sleep.until(()-> getWidgets().get(300, 1 ,11).interact("Close"), 1000);
                        state = 0;
                    }

                }
            }
        }
        else if ( state == 2) // at ge get bank
        {
            if(bank.closest() == null )
                return 500;
            if(!bank.isOpen())
                bank.open();

            if(bank.open()) {
                Sleep.until(() -> bank.withdrawAll("Feather"), 1000, 1000);


                bank.close();

                state = 3;
            }
        }
        else if ( state == 3 ){ // sell on ge

            if (!ge.isOpen())
                ge.open();

            if (ge.isOpen()) {
                Item feather = getInventory().getItem("Feather");
                if (feather != null)
                    Sleep.until(() -> {
                        return grandExchange.sellItem(feather.getId(), 3, feather.getAmount());
                    }, 5000);



                Sleep.until(()->grandExchange.collect(), 1000, 1000);
                state = 1;
                if(ge.isOpen())
                    grandExchange.close();
            }
        }

        return 1000;
    }
}
