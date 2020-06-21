package de.Juldre.Vanish.Utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static de.Juldre.Vanish.main.*;
import de.Juldre.Vanish.main;
import de.Juldre.Vanish.Utils.Files.Type;

public class VanishManager {
	List<String> vanishList;
	public VanishManager() {
		loadVanished();
	}
	public void loadVanished() {	
		vanishList = ((main.files.data.get("Vanished")==null)?new ArrayList<String>():main.files.data.getStringList("Vanished"));
	}
	public void saveVanished() {
		files.data.set("Vanished", vanishList);
		files.save(Type.DATA);
	}
	public void vanishPlayer(Player player) {
		Bukkit.getOnlinePlayers().forEach(target->{
			if(files.isVisibleToPerms()) {
				if(!hasPerms(target, files.getShowVanishPerms())) {
					target.hidePlayer(player);
				}
			}else {
				target.hidePlayer(player);
			}
			
		});
	}
	public boolean hasPerms(Player p,String perms) {
		if(p.hasPermission(perms)) {
			return true;
		}else {
			return false;
		}
	}
	
	public void showPlayer(Player player) {
		Bukkit.getOnlinePlayers().forEach(target->{
			target.showPlayer(player);
		});
	}
	public boolean isVanished(String uuid) {
		return vanishList.contains(uuid);
	}
	public boolean toggleVanish(String uuid) {
		if(isVanished(uuid)) {
			vanishList.remove(uuid);
			return false;
		}else {
			vanishList.add(uuid);
			return true;
		}
	}
}
