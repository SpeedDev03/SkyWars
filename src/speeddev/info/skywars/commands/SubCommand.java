package speeddev.info.skywars.commands;

import org.bukkit.command.CommandSender;

public abstract class SubCommand {

    public abstract void execute(CommandSender sender, String[] args);

}