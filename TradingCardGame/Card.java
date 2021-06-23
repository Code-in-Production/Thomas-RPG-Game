package TradingCardGame;
import java.util.*;

public class Card {

	//0 = grass  1 = water  2 = fire  3 = flying  4 = normal
	public int type; 
	public int weakness;
	//grass < fire, flying
	//water < grass
	//rock < grass, water
	//fire < water
	//flying < rock
	
	public String name;
	public int id;
	public int stage;
	public int pre_id; //ID of the preevolution 
	
	public int attack1cost;
	public int attack1damage;
	
	public int attack2cost;
	public int attack2damage;
	
	public int HP;
	public int curHP;
	public int attachedEnergy;
	
	Card(int a, int b, String name, int c, int d, int e, int f, int g, int h, int i, int j) {
		this.name = name;
		type = a;
		weakness = b;
		id = c;
		stage = d;
		pre_id = e;
		attack1cost = f;
		attack1damage = g;
		attack2cost = h;
		attack2damage = i;
		HP = j;
		curHP = j;
		attachedEnergy = 0;
	}
	
	public String describe() {
		String str = "";
		if(type == 5) str += ":regional_indicator_e:";
		else if(type == 6) str += ":regional_indicator_t:";
		else if(stage == 0) str += ":zero:";
		else if(stage == 1) str += ":arrow_up_small:";
		else if(stage == 2) str += ":arrow_double_up:";
		str += "`" + name + " ";
		if(type <= 4) {
			if(stage == 0) str += "(Basic) ";
			else str += "(Stage " + stage + ") ";
		}
		if(HP != 0) str += " " + HP + "HP   Type: ";
		
		switch (type) {
		case 0:
			str += "grass";
			break;
		case 1:
			str += "water";
			break;
		case 2: 
			str += "fire";
			break;
		case 3:
			str += "flying";
			break;
		case 4:
			str += "rock";
			break;
		case 5:
			str += "   (Energy)";
			break;
		case 6:
			str += "   (Trainer)";
			break;
		}
		
		str += "`\n";
		if(attack1cost != 0) str += "`Cost: " + attack1cost + "       " + attack1damage + "`\n";
		if(attack2cost != 0) str += "`Cost: " + attack2cost + "       " + attack2damage + "`\n";
		return str;
	}
	
	public String describeSymbols() {
		String str = "";
		if(type == 5) str += ":regional_indicator_e:";
		else if(type == 6) str += ":regional_indicator_t:";
		else if(stage == 0) str += ":zero:";
		else if(stage == 1) str += ":arrow_up_small:";
		else if(stage == 2) str += ":arrow_double_up:";
		str += "`" + name + " ";
		if(type <= 4) {
			if(stage == 0) str += "(Basic) ";
			else str += "(Stage " + stage + ") ";
		}
		if(HP != 0) {
			str += "`";
			for(int i = 0; i < ((HP-curHP) / 10); i++) {
				str += ":heart:";
			}
			for(int i = 0; i < (curHP / 10); i++) {
				str += ":green_heart:";
			}
			str += "` Type: ";
		}
		str += "`";
		switch (type) {
		case 0:
			str += ":fallen_leaf:";
			break;
		case 1:
			str += ":droplet:";
			break;
		case 2: 
			str += ":fire:";
			break;
		case 3:
			str += ":airplane:";
			break;
		case 4:
			str += ":rock:";
			break;
		case 5:
			str += "   `(Energy)`";
			break;
		case 6:
			str += "   `(Trainer)`";
			break;
		}
		
		str += "\n";
		if(type <= 4) str += "`Energy attached: " + attachedEnergy + "`\n";
		if(attack1cost != 0) str += "`Cost: " + attack1cost + "       " + attack1damage + "`\n";
		if(attack2cost != 0) str += "`Cost: " + attack2cost + "       " + attack2damage + "`\n";
		return str;
	}
}
