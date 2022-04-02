import org.osbot.rs07.Bot;
import org.osbot.rs07.api.Inventory;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.ConditionalSleep;

public class ItemTask {
    private MethodProvider _script;
    public ItemTask(MethodProvider script)
    {
        _script = script;
    }
    public boolean valid() {
        /*
        * get if inventory is full open featherboxes if we have else but more
        * if we ran out of feahterboxes to open and have no coins go to GE
        * Feather pack feather coin
        * */

        Item featherPack = _script.inventory.getItem(item->{return item.getName().equalsIgnoreCase("feather pack") && item.getAmount() != 0 ;});
        Item coins = _script.inventory.getItem(item->{return item.getName().equalsIgnoreCase("coins") && item.getAmount() >= 200;});

        if(_script.inventory.isFull()) {
            new ConditionalSleep(1000){

                @Override
                public boolean condition() throws InterruptedException {
                    return featherPack == null;
                }
            };
        }


        return true;
    }

    public void execute() {

    }
}
