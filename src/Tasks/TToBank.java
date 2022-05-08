package Tasks;

import nox.scripts.noxscape.util.Sleep;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.script.MethodProvider;

public class TToBank implements ITask{

    private MethodProvider _api = null;
    private final Position _bankPosition = new Position(3164, 3485, 0);
    public TToBank(final MethodProvider api)
    {
        _api = api;
    }


    @Override
    public boolean Valid() {

        if(_api.inventory.isFull())
            return true;

        if(_api.inventory.getItem("Coins").getAmount() < 200)
            return true;

        if(_bankPosition.getArea(10).contains(_api.myPosition()))
            return true;

        return false;
    }

    @Override
    public void Execute() {

        Sleep.until(()-> !_api.myPlayer().isMoving() , 3000);

        _api.walking.webWalk(_bankPosition);

    }
}
