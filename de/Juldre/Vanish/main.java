package de.Juldre.Vanish;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.Juldre.Vanish.Commands.vanishCommand;
import de.Juldre.Vanish.Listener.JoinQuitEvent;
import de.Juldre.Vanish.Utils.Files;
import de.Juldre.Vanish.Utils.Logger;
import de.Juldre.Vanish.Utils.VanishManager;

public class main extends JavaPlugin {
	public static Logger logger;
	public static Files files;
	public static VanishManager vanishManager;

	@Override
	public void onDisable() {
		super.onDisable();
		vanishManager.saveVanished();
		logger.log("§7Plugin §cdisabled §7successfully");
	}

	@Override
	public void onEnable() {
		super.onEnable();
		files = new Files(this.getName());
		logger = new Logger();
		vanishManager = new VanishManager();

		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(new JoinQuitEvent(), this);

		PluginCommand cmd = this.getCommand("vanish");
		cmd.setExecutor(new vanishCommand());
		cmd.setPermission(files.getVanishPerms());
		if(files.getStrNoPerms().isShowMessage()) {
			cmd.setPermissionMessage(files.getStrNoPerms().getMessage());
		}
		cmd.setTabCompleter(new TabCompleter() {
			@Override
			public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
				return new ArrayList<String>();
			}
		});

		logger.log("§7Plugin §aenabled§7 successfully");
	}

}
