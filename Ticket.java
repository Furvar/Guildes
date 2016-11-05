package be.Vilevar.Guildes.Utils;

import java.util.ArrayList;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Ticket implements Listener {

	public static ItemStack Ticket;
	
	public Ticket(){
		
		ItemStack tiquet = new ItemStack(Material.PAPER);
		ItemMeta tiquetM = tiquet.getItemMeta();
		tiquetM.setDisplayName(ChatColor.AQUA+"Tiquet de guilde");
		ArrayList<String>desc = new ArrayList<String>();
		desc.add(ChatColor.AQUA+"Tiquet de guilde");
		tiquetM.setLore(desc);
		tiquetM.addEnchant(Enchantment.ARROW_INFINITE, 2, true);
		tiquet.setItemMeta(tiquetM);
		Ticket = tiquet;
		
	}
}