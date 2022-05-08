import Tasks.*;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import java.util.ArrayList;
import java.util.List;

@ScriptManifest(author = "Anykeys#0663", version = 1.0, info = "", logo = "", name = "Any feathers")
public class MainMethod extends Script {

    private final List<ITask> botTasks = new ArrayList<ITask>();
    private static EState _state = EState.STARTUP;

    @Override
    public void onStart() throws InterruptedException {
        botTasks.add(new TToShop(this));
        botTasks.add(new TToBank(this));
        botTasks.add(new TBuyFeather(this));
        botTasks.add(new TInventoryHandler(this));
    }

    public static void setState(EState state)
    {
        _state =state;
    }
    public static EState getState()
    {
        return _state;
    }

    @Override
    public int onLoop() throws InterruptedException {

        for (ITask t : botTasks ) {

            if(t.Valid())
                t.Execute();

        }

        return 0;
    }
}
