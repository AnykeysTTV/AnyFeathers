package Tasks;

import nox.scripts.noxscape.util.Sleep;
import nox.scripts.noxscape.util.WidgetActionFilter;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.script.MethodProvider;

public class TBuyFeather implements ITask{


    private NPC _shopkeeper = null;
    private MethodProvider _api = null;
    public  TBuyFeather(final MethodProvider api)
    {
        _api = api;
    }

    @Override
    public boolean Valid() {

        _shopkeeper = _api.npcs.closest(f -> f.getName().equalsIgnoreCase("Gerrant"));

        Sleep.until(() -> _shopkeeper != null, 1000, 1000);

        if(_shopkeeper.exists() && _shopkeeper.isVisible())
            return true;



        return false;
    }

    @Override
    public void Execute() {

        //TO DO add sleep
        _shopkeeper.interact("Trade");

    }
}
