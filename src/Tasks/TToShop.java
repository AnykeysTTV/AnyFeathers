package Tasks;

import nox.scripts.noxscape.util.CSleep;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.script.MethodProvider;

import java.awt.*;

public final class TToShop extends AbstractTask {

    private final Position _shopPosition = new Position(3014, 3225, 0);
    public TToShop(final MethodProvider api)
    {
        super(api);
    }


    @Override
    public boolean Valid() {

        if(myPlayer().isMoving())
            return false;

        if(!_shopPosition.getArea(10).contains(myPosition()))
            return true;

        return false;
    }

    @Override
    public void Execute() {

        walking.webWalk(_shopPosition);

    }

}
