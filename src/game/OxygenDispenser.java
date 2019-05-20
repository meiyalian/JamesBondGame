package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;


/**
 * Class for the oxygen dispenser
 * 
 */

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
	
	
	/**
	 * if there is already an oxygen tank on the position of the oxygen dispenser or the oxgen 
	 * dispenser is currently producing the oxygen tank, the press button action will not be 
	 * added to the allowable actions list
	 * 
	 */
	
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
			this.hasOxygen = true;
			turnCounter =0;
		}
		
		return actions;
	
	
	}
	
	
	/**
	 * the description of this dispenser item
	 */
	
	public String description() {
		return "oxygen dispenser";
	}
	
	
	

	/**
	 * method to produce an oxygen tank in the map (in the location of the oxygen dispenser)
	 */

	public String produce() {
		if (turnCounter == 0) {
			turnCounter = 1;
			return "Stay in place. Producing oxygen ...";
		}
		
		if(hasOxygen == false) {

			OxygenTank tank = new OxygenTank(targetPlayer);
			this.map.addItem(tank,x,y);
			
		}
		
		return "";
		
		
		
	}
	
	
	
	/**
	 * if there is an oxygen produced, player is allowed 
	 * to enter the location and pick the oxygen tank up. 
	 * 
	 *Otherwise player cannot enter this position since the dispenser
	 *is a machine rather than a ground. 
	 */
	
	
	@Override
	public boolean canActorEnter(Actor actor) {
		if(hasOxygen && actor instanceof NewPlayer) {
			return true;
		}
		
		return false;
	}

	
}

	
	

