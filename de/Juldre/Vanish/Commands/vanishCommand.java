package de.Juldre.Vanish.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.Juldre.Vanish.main;

public class vanishCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		if(!(sender instanceof Player)) {
			main.logger.logError(sender,"Only Players can use this Command");
			return true;
		}
		Player player = (Player) sender;
		String uuid = player.getUniqueId().toString();
		if(main.vanishManager.toggleVanish(uuid)) {
			//TODO: Added
			main.vanishManager.vanishPlayer(player);
			main.files.sendMessage(sender, main.files.getStrVanishOn());
		}else {
			//TODO: Removed
			main.vanishManager.showPlayer(player);
			main.files.sendMessage(sender, main.files.getStrVanishOff());
		}
		return true;
	}

}
