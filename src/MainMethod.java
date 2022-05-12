import Tasks.*;


import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;


@ScriptManifest(author = "Anykeys#0663", version = 1.0, info = "", logo = "", name = "Any feathers")
public final class MainMethod extends Script {

    private final List<ITask> botTasks = new ArrayList<ITask>();

    private long scriptStartTime = -1;

    @Override
    public void onStart() throws InterruptedException {
        botTasks.add(new TToShop(this));
        botTasks.add(new TBuyFeather(this));
        botTasks.add(new TInventoryHandler(this));
        scriptStartTime = System.currentTimeMillis();
    }


    //200 512
    @Override
    public int onLoop() throws InterruptedException {

       Item coins = inventory.getItem( i -> i.getName().equalsIgnoreCase("coins") && i.getAmount() >=200);
        if(coins == null)
        {
            log("We ran out of coins");
            this.stop(true);
        }

        for (ITask t : botTasks ) {

            if(t.Valid())
                t.Execute();

        }



        return 1000;
    }


    private final Font m = new Font("Sans Serif", 1, 20);

    private final Font n = new Font("Serif", 0, 14);

    private final Font o = new Font("Serif", 0, 16);

    private final Color p = new Color(0, 0, 0, 69);

    private final Color q = new Color(0, 0, 0, 100);

    private final int xPos =312;
    private final int yPos = 235;

    @Override
    public void onPaint(Graphics2D paramGraphics2D) {
        paramGraphics2D.setColor(this.p);
        paramGraphics2D.fillRoundRect(xPos, yPos, 200, 90, 10, 10);
        paramGraphics2D.setColor(this.q);
        paramGraphics2D.drawRoundRect(xPos, yPos, 200, 90, 10, 10);
        paramGraphics2D.setFont(this.m);
        paramGraphics2D.setColor(Color.red);
        paramGraphics2D.drawString(getName(), xPos + 5, yPos + 20);
        Graphics2D graphics2D = paramGraphics2D;
        paramGraphics2D.setColor(Color.white);
        paramGraphics2D.setFont(this.o);
        paramGraphics2D.drawString("Total Runtime: " + getRunTimeStr(), xPos + 5, yPos +40);
        paramGraphics2D.setFont(this.n);
        paramGraphics2D.setColor(Color.white);
        //paramGraphics2D.drawString("Iron keys", 20, 140);
        paramGraphics2D.drawString("Written by Anykeys#0663", xPos + 5, yPos +60);
        paramGraphics2D.drawString("V" + getVersion(), xPos + 5, yPos + 80);
        paramGraphics2D.setColor(new Color(208, 189, 165));
        paramGraphics2D.fillRect(11, 459, 110, 14);

    }

    private String getRunTimeStr()
    {
        long t = (System.currentTimeMillis() - scriptStartTime) / 1000;
        return String.format("%02d:%02d:%02d", new Object[]{Long.valueOf(t / 3600L), Long.valueOf(t % 3600L / 60L), Long.valueOf(t % 60L) });
    }


}
