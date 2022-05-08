package Tasks;

import org.osbot.rs07.api.Inventory;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.script.MethodProvider;



public class TInventoryHandler implements ITask{

    private MethodProvider _api = null;
    private Item _pack = null;

    public TInventoryHandler(final MethodProvider api)
    {
        _api = api;
    }

    @Override
    public boolean Valid() {

        _pack = _api.inventory.getItem("Feather pack");

        if(_pack == null)
            return false;


        return false;
    }

    @Override
    public void Execute() {

        _pack.interact("Open");

    }
}
