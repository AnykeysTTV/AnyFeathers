package Tasks;

import nox.scripts.noxscape.util.Sleep;
import nox.scripts.noxscape.util.WidgetActionFilter;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.script.MethodProvider;

public class TBuyFeather implements ITask{


    private NPC _shopkeeper = null;
    private MethodProvider _api = null;
    private RS2Widget _shopwindow = null;
    public  TBuyFeather(final MethodProvider api)
    {
        _api = api;
    }


    @Override
    public boolean Valid() {

        _shopkeeper = _api.npcs.closest(f -> f.getName().equalsIgnoreCase("Gerrant"));

        Sleep.until(() -> _shopkeeper != null, 1000, 1000);
        Item _pack = _api.inventory.getItem("Feather pack");

        if(_api.myPlayer().isMoving())
            return false;


        if(_pack == null)
            return true;

        if( _shopkeeper == null)
            return true;

        return false;
    }

    @Override
    public void Execute() {

        _shopwindow = _api.widgets.get(300, 16, 9);

        if(_shopwindow == null )
            _shopkeeper.interact("Trade");
        else {


            if (_shopwindow.getItemAmount() == 100)
                _shopwindow.interact("Buy 10");
            Sleep.until(() -> _shopwindow.getItemAmount() == 100, 5000, 1000);
        }

    }
}
