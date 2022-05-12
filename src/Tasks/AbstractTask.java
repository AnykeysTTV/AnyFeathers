package Tasks;

import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.script.MethodProvider;

public abstract class AbstractTask extends MethodProvider implements ITask {


    public AbstractTask(final MethodProvider method)
    {
        this.exchangeContext(method.getBot());
    }

    protected RS2Widget shopWidget()
    {
        return  getWidgets().getAll().stream().filter(w->w.getItemId() == 11881 && w.getWidth() == 36 && w.getHeight() == 32).findFirst().orElse(null);
    }
    protected boolean isShopOpen()
    {
        return shopWidget() != null && shopWidget().isVisible();
    }


}
