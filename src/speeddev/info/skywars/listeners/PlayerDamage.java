package speeddev.info.skywars.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import speeddev.info.skywars.Skywars;
import speeddev.info.skywars.object.Game;

public class PlayerDamage implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            Game game = Skywars.getInstance().getGame(player);
            if (game != null) {
                if (game.isState(Game.GameState.LOBBY) || game.isState(Game.GameState.STARTING) || game.isState(Game.GameState.PREPARATION) || game.isState(Game.GameState.ENDING)) {
                    event.setCancelled(true);
                }
            } else {
                if (Skywars.getInstance().isSingleServerMode()) {
                    event.setCancelled(true);
                }
            }
        }
    }

}