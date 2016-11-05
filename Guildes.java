package be.Vilevar.Guildes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import be.Vilevar.Guildes.Events.ClassManager;
import be.Vilevar.Guildes.Utils.Grades;
import be.Vilevar.Guildes.Utils.NPCTicketOptions;
import be.Vilevar.Guildes.Utils.Ticket;

public class Guildes extends JavaPlugin implements Listener{
	
	public HashMap<String, UUID> guilde = new HashMap<>();
	public HashMap<UUID, String> GvanP = new HashMap<>();
	public HashMap<UUID, Grades> grade = new HashMap<>();
	public HashMap<String, Integer> maxNumberP = new HashMap<>();
	public HashMap<String, Integer> NumberP = new HashMap<>();
	public ArrayList<UUID> PermCreateG = new ArrayList<>();
	
	public static Guildes instance;
	
	public static Guildes getInstance(){
		return instance;
	}
	
	@Override
	public void onEnable(){
		super.onEnable();
		getServer().getPluginManager().registerEvents(this, this);
		
		instance = this;
		
		//OUVERTURE
		
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		System.out.println("GuildesPl en activation.");
		System.out.println("Chargement des guildes.");
		
		new ClassManager(this).registerEvents();
		
		//constructors
		new Ticket();
		new NPCTicketOptions();
	}
	
	@Override
	public void onDisable(){
		super.onDisable();
		System.out.println("GuildesPl en désactivation.");
	}
}


















