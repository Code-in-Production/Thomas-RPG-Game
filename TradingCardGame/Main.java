package TradingCardGame;

import java.util.*;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.*;

public class Main {

	public static JDA jda;
	
	public static String prefix = "!";
	
	public static void main(String[] args) throws LoginException {
		// TODO Auto-generated method stub
		//ODU1ODYxNjQxMjU4MjA1MTg0.YM4pRA.ZQMTc1BuBVIlkoyuT77cVaxpLBI

		jda = JDABuilder.createDefault("ODU1ODYxNjQxMjU4MjA1MTg0.YM4pRA.ZQMTc1BuBVIlkoyuT77cVaxpLBI").build();
		
		jda.getPresence().setStatus(OnlineStatus.ONLINE);
		
		jda.addEventListener(new Game());
	}
}