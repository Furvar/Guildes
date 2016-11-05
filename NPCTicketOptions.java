package be.Vilevar.Guildes.Utils;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class NPCTicketOptions implements Listener {

	public String titre = ChatColor.AQUA+"Vente de tiquet de guilde";
	
	public NPCTicketOptions(){}
	
//OUVERTURE INVENTAIRE	
	@EventHandler
	public void onInteractNPC(PlayerInteractEntityEvent e){
		Player p = e.getPlayer();
		Entity ent = e.getRightClicked();
		if(ent instanceof Villager){
			Villager npc = (Villager)ent;
			if(npc.getCustomName() != null && npc.getCustomName().equals(ChatColor.AQUA+"Vendeur de tiquets de guilde")){
				e.setCancelled(true);
				Inventory inv = Bukkit.createInventory(null, 9, titre);
				inv.setItem(0, getItem(Material.GOLD_INGOT, ChatColor.GOLD+"prix", ChatColor.AQUA+"64 diamants"));
				inv.setItem(1, getItem(Material.GOLD_INGOT, ChatColor.GOLD+"prix", ChatColor.AQUA+"64 diamants"));
				inv.setItem(8, Ticket.Ticket);
				p.openInventory(inv);
			}
		}
	}
	
//NO KILL NPCTicket
	@EventHandler
	public void onNoDamagNPC(EntityDamageEvent e){
		Entity ent = e.getEntity();
		if(ent instanceof Villager){
			Villager npc = (Villager)ent;
			if(npc.getCustomName() != null && npc.getCustomName().equals(ChatColor.AQUA+"Vendeur de tiquets de guilde")){
				e.setCancelled(true);
			}
		}
	}
	
//OPTIONS CLICKS INVENTAIRE
	@EventHandler
	public void onClick(InventoryClickEvent e){
		Inventory inv = e.getInventory();
		if(inv.getTitle().equals(titre)){
			if(e.getCurrentItem().getType() == Material.DIAMOND)return;
			if(e.getCurrentItem().getType() == Material.PAPER){
				if(inv.contains(Material.DIAMOND, 128))return;
				else e.setCancelled(true);
			}
			if(e.getCurrentItem().getType() != Material.PAPER && e.getCurrentItem().getType() != Material.DIAMOND)e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onFermeInv(InventoryCloseEvent e){
		Player p = (Player) e.getPlayer();
		Inventory inv = e.getInventory();
		if(inv.getTitle().equals(titre)){
			if(inv.contains(Material.DIAMOND, 128) && !p.getInventory().contains(Ticket.Ticket)){
				p.getInventory().addItem(Ticket.Ticket);
				p.sendMessage(ChatColor.GREEN+"Vous venez d'acheter le tiquet de guilde.");
			}
		}
	}
	
	private ItemStack getItem(Material mat, String name, String desc) {
		ItemStack i = new ItemStack(mat, 1);
		ItemMeta iM = i.getItemMeta();
		ArrayList<String> desc1 = new ArrayList<>();
		desc1.add(desc);
		iM.setLore(desc1);
		iM.setDisplayName(name);
		i.setItemMeta(iM);
		return i;
	}
}
