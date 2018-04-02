package speeddev.info.skywars.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import speeddev.info.skywars.Skywars;
import speeddev.info.skywars.object.Game;

public class PlayerMove implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        Game game = Skywars.getInstance().getGame(player);
        if (game != null) {
            if (game.isMovementFrozen()) {
                if (event.getFrom().getBlockX() != event.getTo().getBlockX() || event.getFrom().getBlockZ() != event.getTo().getBlockZ()) {
                    player.teleport(event.getFrom());
                }
            }
        }
    }

}