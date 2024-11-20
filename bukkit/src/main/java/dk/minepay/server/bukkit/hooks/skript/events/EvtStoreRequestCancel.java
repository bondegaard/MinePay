package dk.minepay.server.bukkit.hooks.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import dk.minepay.server.bukkit.classes.StoreRequest;
import dk.minepay.server.bukkit.events.StoreRequestCancelEvent;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

public class EvtStoreRequestCancel extends SkriptEvent {
    static {
        Skript.registerEvent(
                "Store Request Cancel",
                EvtStoreRequestCancel.class,
                StoreRequestCancelEvent.class,
                "store request cancel");
        EventValues.registerEventValue(
                StoreRequestCancelEvent.class,
                OfflinePlayer.class,
                new Getter<OfflinePlayer, StoreRequestCancelEvent>() {
                    @Override
                    public OfflinePlayer get(StoreRequestCancelEvent event) {
                        return event.getPlayer();
                    }
                },
                0);
        EventValues.registerEventValue(
                StoreRequestCancelEvent.class,
                StoreRequest.class,
                new Getter<StoreRequest, StoreRequestCancelEvent>() {
                    @Override
                    public StoreRequest get(StoreRequestCancelEvent event) {
                        return event.getRequest();
                    }
                },
                0);
    }

    @Override
    public boolean init(
            Literal<?>[] args, int matchedPattern, SkriptParser.ParseResult parseResult) {
        return true;
    }

    @Override
    public boolean check(Event event) {
        return event instanceof StoreRequestCancelEvent;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "";
    }
}