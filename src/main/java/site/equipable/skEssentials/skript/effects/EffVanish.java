package site.equipable.skEssentials.skript.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.earth2me.essentials.User;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import site.equipable.skEssentials.SkEssentials;

@Name("Vanish Player")
@Description("Vanish or unvanish a player.")
@Examples({"vanish player"})
@Since("INSERT VERSION")
public class EffVanish extends Effect {

    static {
        Skript.registerEffect(EffVanish.class, "vanish %players%",
                "unvanish %players%",
                "make %players% vanish",
                "make %players% unvanish");
    }

    private Expression<Player> players;

    private boolean vanish;
    private boolean unvanish;
    private boolean makeVanish;
    private boolean makeUnvanish;

    @Override
    protected void execute(Event event) {
        for (Player player : players.getArray(event)) {
            User user = SkEssentials.essentials.getUser(player);
            if (vanish || makeVanish) {
                user.setVanished(true);
            }
            if (unvanish || makeUnvanish) {
                user.setVanished(false);
            }
        }
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "vanish";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        players = (Expression<Player>) exprs[0];
        vanish = matchedPattern == 0;
        unvanish = matchedPattern == 1;
        makeVanish = matchedPattern == 2;
        makeUnvanish = matchedPattern == 3;
        return true;
    }
}
