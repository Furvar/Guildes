package be.Vilevar.Guildes.Events;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import be.Vilevar.Guildes.Guildes;
import be.Vilevar.Guildes.Commands.NpcTicketCmd;
import be.Vilevar.Guildes.Utils.NPCTicketOptions;
import be.Vilevar.Guildes.Utils.Ticket;

public class ClassManager {

	private Guildes pl;
	
	public ClassManager(Guildes guildes) {
		this.pl = guildes;
	}

	public void registerEvents() {
		
		PluginManager pm = Bukkit.getPluginManager();
		
		//guildes
		//ticket
		pm.registerEvents(new Ticket(), pl);
		pm.registerEvents(new NPCTicketOptions(), pl);
		
		pl.getCommand("ticket").setExecutor(new NpcTicketCmd());
	}
}
