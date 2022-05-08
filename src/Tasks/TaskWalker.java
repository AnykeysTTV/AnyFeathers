package Tasks;

import nox.scripts.noxscape.util.Sleep;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.script.MethodProvider;

public class TaskWalker implements ITask{
    private MethodProvider _api;
    private Position endPosition;
    public  TaskWalker(final MethodProvider api, Position position)
    {
        this.endPosition =position;
        this._api = api;
    }

    @Override
    public boolean Valid() {
        return (endPosition.getArea(5).contains(_api.myPosition()) == false);
    }

    @Override
    public void Execute() {

        Sleep.until(() ->
            !_api.myPlayer().isAnimating()
        , 1000);

        _api.walking.webWalk(endPosition);

    }
}
