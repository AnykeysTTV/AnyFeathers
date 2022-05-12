package Tasks;

import nox.scripts.noxscape.util.CSleep;
import org.osbot.rs07.api.model.NPC;

import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.ConditionalSleep;

import java.awt.*;


public final class TBuyFeather extends AbstractTask{


    private NPC _shopkeeper = null;


    public  TBuyFeather(final MethodProvider api)
    {
        super(api);
    }


    @Override
    public boolean Valid() {


        _shopkeeper = npcs.filter(n->n.getName().equalsIgnoreCase("Gerrant")).stream().findFirst().orElse(null);

        if(_shopkeeper != null)
            return true;

        return false;
    }

    @Override
    public void Execute() {


      if(!isShopOpen())
            _shopkeeper.interact("Trade");
      else
          if (shopWidget().getItemAmount() == 100)
              shopWidget().interact("Buy 10");

      new CSleep(() -> shopWidget().getItemAmount() == 100, 1000, 1000).sleep();


    }


}
