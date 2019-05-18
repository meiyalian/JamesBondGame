package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

public class OxygenDispenser extends Ground{
	
	private boolean hasOxygen;
	private GameMap map;
	private int turnCounter;
	private int x;
	private int y;
	private Actor targetPlayer;
	
	
	
	public OxygenDispenser(GameMap gameMap, int x_co, int y_co, Actor player) {
		super('D');
		this.hasOxygen = false;
		map = gameMap;
		turnCounter = 0;
		x = x_co;
		y = y_co;
		targetPlayer = player;
		
		

		
	}
	
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction) {
		Actions actions = new Actions();
		
		if(this.map.at(x,y).getItems().size() ==0) {
			hasOxygen = false;
		}
		
		if(! hasOxygen && turnCounter ==0) {
			actions.add(new PressButtonAction(this, "Oxygen Tank"));
		}
		
		if(! hasOxygen && turnCounter ==1) {
			produce();
			turnCounter =0;
		}
		
		return actions;
	
	
	}
	
	
	public String description() {
		return "oxygen dispenser";
	}
	


	public String produce() {
		if (turnCounter == 0) {
			turnCounter = 1;
			return "Stay in place. Producing oxygen ...";
		}
		
		if(hasOxygen == false) {

			OxygenTank tank = new OxygenTank(targetPlayer);
			this.map.addItem(tank,x,y);
			this.hasOxygen = true;
			return "Oxygen tank is ready to pick up!";
		}
		
		return "";
		
		
		
	}
	
	
	@Override
	public boolean canActorEnter(Actor actor) {
		if(hasOxygen) {
			return true;
		}
		
		return false;
	}

	
}

	
	

