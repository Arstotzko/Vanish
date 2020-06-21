package de.Juldre.Vanish.Listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.Juldre.Vanish.main;

public class JoinQuitEvent implements Listener{
	@EventHandler
	public void JoinEvent(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		String uuid = player.getUniqueId().toString();
		if(main.vanishManager.isVanished(uuid)) {
			main.vanishManager.vanishPlayer(player);
			main.files.sendMessage(event.getPlayer(), main.files.getStrNotification());
			if(!main.files.isShowJoinMessage()) {
				event.setJoinMessage(null);
			}
		}
		Bukkit.getOnlinePlayers().forEach(target->{
			String targetUUID = target.getUniqueId().toString();
			if(main.vanishManager.isVanished(targetUUID)) {
				if(!main.vanishManager.hasPerms(player, main.files.getShowVanishPerms())) {
					player.hidePlayer(target);
				}
			}
		});
	}
	@EventHandler
	public void JoinEvent(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		String uuid = player.getUniqueId().toString();
		if(main.vanishManager.isVanished(uuid)) {
			if(!main.files.isShowQuitMessage()) {
				event.setQuitMessage(null);
			}
		}
	}
}
