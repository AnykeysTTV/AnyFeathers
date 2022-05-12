package nox.scripts.noxscape.util;

import org.osbot.rs07.utility.ConditionalSleep;

import java.util.function.BooleanSupplier;

public final class CSleep extends ConditionalSleep {

    private final BooleanSupplier condition;

    public CSleep(final BooleanSupplier condition, final int timeout) {
        super(timeout);
        this.condition = condition;
    }

    public CSleep(final BooleanSupplier condition, final int timeout, final int interval) {
        super(timeout, interval);
        this.condition = condition;
    }

    @Override
    public final boolean condition() throws InterruptedException {
        return condition.getAsBoolean();
    }

    public static boolean until(final BooleanSupplier condition, final int timeout) {
        return new CSleep(condition, timeout).sleep();
    }

    public static boolean until(final BooleanSupplier condition, final int timeout, final int interval) {
        return new CSleep(condition, timeout, interval).sleep();
    }
}