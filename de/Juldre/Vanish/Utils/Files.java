package de.Juldre.Vanish.Utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import de.Juldre.Vanish.main;

public class Files {

	private MessageElement strPrefix, strVanishOn, strVanishOff, strNoPerms, strNotification;
	private String vanishPerms, showVanishPerms;
	private boolean showQuitMessage, showJoinMessage, visibleToPerms;


	public MessageElement getStrPrefix() {
		return strPrefix;
	}

	public void setStrPrefix(MessageElement strPrefix) {
		this.strPrefix = strPrefix;
	}

	public MessageElement getStrVanishOn() {
		return strVanishOn;
	}

	public void setStrVanishOn(MessageElement strVanishOn) {
		this.strVanishOn = strVanishOn;
	}

	public MessageElement getStrVanishOff() {
		return strVanishOff;
	}

	public void setStrVanishOff(MessageElement strVanishOff) {
		this.strVanishOff = strVanishOff;
	}

	public MessageElement getStrNoPerms() {
		return strNoPerms;
	}

	public void setStrNoPerms(MessageElement strNoPerms) {
		this.strNoPerms = strNoPerms;
	}

	public MessageElement getStrNotification() {
		return strNotification;
	}

	public void setStrNotification(MessageElement strNotification) {
		this.strNotification = strNotification;
	}

	public boolean isShowQuitMessage() {
		return showQuitMessage;
	}

	public void setShowQuitMessage(boolean showQuitMessage) {
		this.showQuitMessage = showQuitMessage;
	}

	public boolean isShowJoinMessage() {
		return showJoinMessage;
	}

	public void setShowJoinMessage(boolean showJoinMessage) {
		this.showJoinMessage = showJoinMessage;
	}

	File folder, file, dataFile;
	YamlConfiguration config, data;

	public boolean createFolder(File folder) {
		if (!folder.exists()) {
			folder.mkdir();
			return true;
		} else {
			return false;
		}
	}

	public boolean createFile(File file) {
		if (file.exists())
			return false;
		try {
			file.createNewFile();
			return true;
		} catch (IOException e) {
			main.logger.logError(Bukkit.getConsoleSender(), e.getStackTrace().toString());
		}
		return false;
	}

	public static enum Type {
		DATA, CONFIG
	}

	public void save(Type type) {
		try {
			switch (type) {
			case CONFIG:
				System.out.println("Config");
				config.save(file);
				break;
			case DATA:
				System.out.println("Data");
				data.save(dataFile);
				break;
			}
		} catch (Exception e) {
			main.logger.logError(Bukkit.getConsoleSender(), e.getStackTrace().toString());
		}
	}

	public Files(String folderName) {
		// TODO: Set Paths
		folder = new File("plugins/" + folderName + "/");
		file = new File("plugins/" + folderName + "/config.yml");
		dataFile = new File("plugins/" + folderName + "/data.yml");

		// TODO: Create Pluginfolder
		createFolder(folder);

		// TODO: Create Configfile
		createFile(file);

		// TODO: Load Config
		config = YamlConfiguration.loadConfiguration(file);

		// TODO: Set Default System
		

		// TODO: Set Default Messages
		setDef("System.Prefix", "&8[§6Vanish§8] ");
		setDef("System.Perms", "&cYou do not have Permissions to do that! &7(&6<permission>&7)");
		setDef("Vanish.Enable", "&6Vanish&7 toggled &aOn");
		setDef("Vanish.Disable", "&6Vanish&7 toggled &cOff");
		setDef("Vanish.Notification", "&7You're still in &6Vanish");

		// TODO: Set Default Permissions
		config.addDefault("Permissions.Vanish.Toggle", "Vanish.Toggle");
		config.addDefault("Permissions.Vanish.ShowOthers", "Vanish.Show");

		// TODO: Set Default Options
		config.addDefault("Options.VisibleToPerms", true);
		config.addDefault("Options.ShowJoinMessage", false);
		config.addDefault("Options.ShowQuitMessage", false);
		config.options().copyDefaults(true);
		
		// TODO: Save Config
		save(Type.CONFIG);

		// TODO: Set Messages
		strPrefix = getElement("System.Prefix");
		strNoPerms = getElement("System.Perms");
		strVanishOn = getElement("Vanish.Enable");
		strVanishOff = getElement("Vanish.Disable");
		strNotification = getElement("Vanish.Notification");
		
		// TODO: Set Permissions
		setVanishPerms(config.getString("Permissions.Vanish.Toggle"));
		setShowVanishPerms(config.getString("Permissions.Vanish.ShowOthers"));

		// TODO: Set Options
		setVisibleToPerms(config.getBoolean("Options.VisibleToPerms"));
		setShowJoinMessage(config.getBoolean("Options.ShowJoinMessage"));
		setShowQuitMessage(config.getBoolean("Options.ShowQuitMessage"));

		// TODO: Create Dataile
		createFile(dataFile);

		// TODO: Load Data File
		data = YamlConfiguration.loadConfiguration(dataFile);
	}

	public MessageElement getElement(String str) {
		return new MessageElement(getMsg(str),getShow(str));
	}
	
	public String getMsg(String str) {
		return config.getString("Messages." + str + ".Message").replaceAll("&", "§");
	}
	public void sendMessage(CommandSender sender,MessageElement el) {
		if(el.isShowMessage()) {
			if(getStrPrefix().isShowMessage()) {
				sender.sendMessage(getStrPrefix().getMessage()+el.getMessage());
			}else {
				sender.sendMessage(el.getMessage());
			}
			
		}
	}
	
	public void setDef(String str,String val) {
		defMsg(str, val);
		defShow(str, true);
	}
	
	public void defMsg(String str, String val) {
		config.addDefault("Messages." + str + ".Message", val);
	}

	public void defShow(String str, boolean val) {
		config.addDefault("Messages." + str + ".Show", val);
	}

	public Boolean getShow(String str) {
		return config.getBoolean("Messages." + str + ".Show");
	}

	public String getVanishPerms() {
		return vanishPerms;
	}

	public void setVanishPerms(String vanishPerms) {
		this.vanishPerms = vanishPerms;
	}

	public String getShowVanishPerms() {
		return showVanishPerms;
	}

	public void setShowVanishPerms(String showVanishPerms) {
		this.showVanishPerms = showVanishPerms;
	}

	public boolean isVisibleToPerms() {
		return visibleToPerms;
	}

	public void setVisibleToPerms(boolean visibleToPerms) {
		this.visibleToPerms = visibleToPerms;
	}
}
