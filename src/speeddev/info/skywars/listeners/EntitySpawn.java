package speeddev.info.skywars.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import speeddev.info.skywars.Skywars;

public class EntitySpawn implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        if (Skywars.getInstance().isSingleServerMode()) {
            if (event.getEntityType() != EntityType.PLAYER && event.getEntityType() != EntityType.DROPPED_ITEM && event.getEntityType() != EntityType.PRIMED_TNT) {
                event.setCancelled(true);
            }
        }
    }

}