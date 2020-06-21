package de.Juldre.Vanish.Utils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import de.Juldre.Vanish.main;

public class Logger {

	public void log(String text) {
		if(main.files.getStrPrefix().isShowMessage()) {
			Bukkit.getConsoleSender().sendMessage(main.files.getStrPrefix().getMessage()+text);
		}else {
			Bukkit.getConsoleSender().sendMessage(text);
		}
		
	}

	public void logError(CommandSender sender,String stackTrace) {
		sender.sendMessage("\n§7------§cBegin Error§7------\n§c" + stackTrace + "\n§7-------§cEnd Error§7-------");
	}

	public Logger() {
		
	}
}
