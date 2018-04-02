package speeddev.info.skywars.tasks;

import org.bukkit.scheduler.BukkitRunnable;
import speeddev.info.skywars.Skywars;
import speeddev.info.skywars.object.Game;

public class GameCountdownTask extends BukkitRunnable {

    private int time = 20;
    private Game game;

    public GameCountdownTask(Game game) {
         this.game = game;
    }

    public void run() {
        time -= 1;

        if (time == 0) {
            // Start
            cancel();

            new GameRunTask(game).runTaskTimer(Skywars.getInstance(), 0, 20);
//            Skywars.getInstance().getServer().getScheduler().runTask(Skywars.getInstance(), new GameRunTask(game)); // Deprecated
        } else {
            if (time == 15 || time == 10 || time == 5) {
                game.sendMessage("&a[*] You'll be teleported to the game in " + time + " seconds");
            }
        }
    }

}
