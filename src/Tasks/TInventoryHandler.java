package Tasks;

import nox.scripts.noxscape.util.Sleep;
import nox.scripts.noxscape.util.WidgetActionFilter;
import org.osbot.rs07.api.Inventory;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.script.MethodProvider;



public class TInventoryHandler implements ITask{

    private MethodProvider _api = null;
    private Item _pack = null;
    WidgetActionFilter _shopwindow = new WidgetActionFilter("Close");

    public TInventoryHandler(final MethodProvider api)
    {
        _api = api;
    }

    @Override
    public boolean Valid() {

        _pack = _api.inventory.getItem("Feather pack");

        if(_pack == null)
            return false;


        return true;
    }

    @Override
    public void Execute() {

        RS2Widget window = _api.getWidgets().get(300, 1, 11);

        if(window != null)
            window.interact("Close");

        Sleep.until(()-> window == null, 1000, 1000);

        _pack.interact("Open");
        Sleep.until(() -> _pack == null, 10000, 1000);

    }
}
