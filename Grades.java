package be.Vilevar.Guildes.Utils;

import java.util.UUID;

import be.Vilevar.Guildes.Guildes;
import net.md_5.bungee.api.ChatColor;

public enum Grades {

	RIEN(ChatColor.WHITE+"", ChatColor.WHITE+""),
	INVITE(ChatColor.GRAY+"#", ChatColor.GRAY+"Invité"),
	MEMBRE(ChatColor.YELLOW+"##", ChatColor.YELLOW+"Membre"),
	MODO(ChatColor.GREEN+"*", ChatColor.GREEN+"Modo"),
	ADMIN(ChatColor.RED+"**", ChatColor.RED+"Admin"),
	CHEF(ChatColor.DARK_RED+"***", ChatColor.DARK_RED+"Chef");
	
	private String idG;
	private String nameG;
	
	Grades (String id, String name){
		this.idG = id;
		this.nameG = name;
	}
	
//METHODES
  //METHODES ENUM
	//recup id van grades
	public String getIdG(Grades grade){
		
		if(grade == null)return null;
		else{
			return idG;
		}
	}
	//recup name van grades
	public String getNameG(Grades grade){
		
		if(grade == null) return null;
		else{
			return nameG;
		}
	}
  //EXTERNALS ENUM METHODES
	//recup grades 
	public Grades getGrade(UUID uuid){
		
		if(uuid == null)return null;
			
		if(Guildes.getInstance().guilde.containsValue(uuid) && Guildes.getInstance().grade.containsKey(uuid) && Guildes.getInstance().grade.get(uuid) != null){
			return Guildes.getInstance().grade.get(uuid);
		}else{
			if(Guildes.getInstance().guilde.containsValue(uuid)){
				
			}
			return null;
		}
	}
	//remove player van grades
	public void removeGrade(UUID uuid){
		
		if(uuid == null) return;
		
		if(Guildes.getInstance().grade.containsKey(uuid)){
			Guildes.getInstance().grade.remove(uuid);
		}else{
			return;
		}
	}
	//set grades
	public void setGrade(UUID uuid, Grades grade){
		
		if(uuid == null || grade == null) return;

		if(Guildes.getInstance().grade.containsKey(uuid)){
			Guildes.getInstance().grade.remove(uuid);
		}
		Guildes.getInstance().grade.put(uuid, grade);
	}
	//player is grades ...
	public boolean isGrade(UUID uuid, Grades grade){
		
		if(uuid == null || grade == null) return false;
		
		if(Guildes.getInstance().grade.containsKey(uuid) && Guildes.getInstance().grade.get(uuid) != null){
			if(Guildes.getInstance().grade.get(uuid) == grade){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
  //EXTERNALS METHODES
	//recup guildes van player
	public String getGuilde(UUID uuid){
		
		if(uuid == null) return null;
		
		if(Guildes.getInstance().guilde.containsValue(uuid) && Guildes.getInstance().GvanP.get(uuid) != null){
			return Guildes.getInstance().GvanP.get(uuid);
		}else{
			return null;
		}
	}
	//player is in one guildes
	public boolean isInGuilde(UUID uuid){
		
		if(uuid == null) return false;
		
		if(Guildes.getInstance().guilde.containsValue(uuid) && Guildes.getInstance().GvanP.containsKey(uuid)){
			return true;
		}else{
			return false;
		}
	}
	//remove player van guildes
	public void removePlayerVanGuilde(UUID uuid){
		
		if(uuid == null) return;
		
		if(Guildes.getInstance().GvanP.containsKey(uuid) && Guildes.getInstance().guilde.containsValue(uuid)){
			Guildes.getInstance().GvanP.remove(uuid);
			Guildes.getInstance().guilde.remove(Guildes.getInstance().GvanP.get(uuid));
		}else{
			return;
		}
	}
	//set player in guildes
	public void setPlayerinGuilde(UUID uuid, String guilde){
		
		if( uuid == null || guilde == null) return;
		
		if(Guildes.getInstance().GvanP.containsKey(uuid) && Guildes.getInstance().GvanP.get(uuid) == guilde && Guildes.getInstance().guilde.containsValue(uuid)){
			Guildes.getInstance().GvanP.remove(uuid);
			Guildes.getInstance().guilde.remove(Guildes.getInstance().GvanP.get(uuid));
		}

		if(Guildes.getInstance().guilde.containsKey(guilde)){
			Guildes.getInstance().guilde.put(guilde, uuid);
		}else{
			return;
		}
	}
}


















