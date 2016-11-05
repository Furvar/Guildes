package be.Vilevar.Guildes.Commands;

import net.minecraft.server.v1_10_R1.Entity;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;

import be.Vilevar.Guildes.Guildes;
import be.Vilevar.Guildes.Utils.Ticket;

public class NpcTicketCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(cmd.getName().equalsIgnoreCase("ticket")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(args.length == 0){
					p.sendMessage(ChatColor.GOLD+"/ticket < spawn | kill | give >");
				}
				if(args.length == 1){
					// /ticket spawn 
					if(args[0].equalsIgnoreCase("spawn")){
						if(p.hasPermission("guildes.ticket.spawn")){
							
							//variables
							Location loc = p.getLocation();
							Villager npc = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
							Entity nms = ((CraftEntity) npc).getHandle();
					
							//nms
							nms.setCustomName(ChatColor.AQUA+"Vendeur de tiquets de guilde");
							nms.setCustomNameVisible(true);
							nms.setPositionRotation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
				
							//artificiel intelligence + profession
							npc.setAI(false);
							npc.setProfession(Profession.LIBRARIAN);
						}
					}
					// /ticket kill
					if(args[0].equalsIgnoreCase("kill")){
						if(p.hasPermission("guildes.ticket.kill")){
							int distanceKill = Guildes.getInstance().getConfig().getInt("ticket.distanceKillNPC");
							for(org.bukkit.entity.Entity ent : p.getNearbyEntities(distanceKill, distanceKill, distanceKill)){
								if(ent.getType() == EntityType.VILLAGER && ent.getCustomName() != null && ent.getCustomName().equals(ChatColor.AQUA+"Vendeur de tiquets de guilde")){
									ent.remove();
								}
							}
						}
					}
					// /ticket give
					if(args[0].equalsIgnoreCase("give")){
						if(p.hasPermission("guildes.ticket.give")){
							p.getInventory().addItem(Ticket.Ticket);
							p.sendMessage(ChatColor.GREEN+"Tiquet de guilde bien reçus.");
						}
					}
				}
			}
		}
		return false;
	}
}