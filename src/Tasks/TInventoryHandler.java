package Tasks;

import nox.scripts.noxscape.util.CSleep;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.script.MethodProvider;

import java.awt.*;


public final class TInventoryHandler extends AbstractTask{

    private Item _pack = null;

    public TInventoryHandler(final MethodProvider api)
    {
        super(api);
    }

    @Override
    public boolean Valid() {

        _pack = inventory.getItem("Feather pack");

        _pack = inventory.getItem(i -> i.getName().equalsIgnoreCase("Feather pack") && i.getAmount() > 0);

        if(_pack == null)
            return false;


        return true;
    }

    @Override
    public void Execute() {


        if(isShopOpen())
            new CSleep(() -> widgets.closeOpenInterface(), 500, 500).sleep();

        _pack.interact("Open");
        new CSleep(()-> _pack == null, 1000, 1000).sleep();

    }

}
