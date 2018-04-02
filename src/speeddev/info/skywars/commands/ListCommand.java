package speeddev.info.skywars.commands;

import org.bukkit.command.CommandSender;
import speeddev.info.skywars.Skywars;
import speeddev.info.skywars.object.Game;
import speeddev.info.skywars.utility.ChatUtil;


public class ListCommand extends SubCommand {
	
	String prefix = "§a§lSkyWars §8§l> ";

    public void execute(CommandSender sender, String[] args) {
        for (Game game : Skywars.getInstance().getGames()) {
            sender.sendMessage(ChatUtil.format(prefix + "§f" + game.getDisplayName() + " - " + game.getPlayers().size() + " (alive) players"));
        }
    }
}
