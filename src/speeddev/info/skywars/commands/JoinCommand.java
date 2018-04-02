package speeddev.info.skywars.commands;


import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import speeddev.info.skywars.Skywars;
import speeddev.info.skywars.object.Game;
import speeddev.info.skywars.object.GamePlayer;
import speeddev.info.skywars.utility.ChatUtil;

public class JoinCommand extends SubCommand {
	
	String prefix = "§a§lSkyWars §8§l> ";
	
    public void execute(CommandSender commandSender, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (args.length == 0) {
                player.sendMessage(ChatUtil.format( prefix + "&c/skywars join <game name>"));
            } else {
                for (Game game : Skywars.getInstance().getGames()) {
                    for (GamePlayer gamePlayer : game.getPlayers()) {
                        if (gamePlayer.isTeamClass()) {
                            if (gamePlayer.getTeam().isPlayer(player)) {
                                player.sendMessage(ChatUtil.format(prefix + "&cYou're in a game."));
                                return;
                            }
                        } else {
                            if (gamePlayer.getPlayer() == player) {
                                player.sendMessage(ChatUtil.format(prefix + "&cYou're in a game."));
                                return;
                            }
                        }
                    }
                }

                Game game = Skywars.getInstance().getGame(args[0]);
                if (game == null) {
                    player.sendMessage(ChatUtil.format(prefix + "&cThat game doesn't exist."));
                    return;
                }
                game.joinGame(new GamePlayer(player));
            }
        } else {
            commandSender.sendMessage(ChatUtil.format(prefix + "&cYou're not a player!"));
        }
    }
}