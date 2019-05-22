package game;


import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;


/**
 * Class for the oxygen tank
 * 
 */

public class OxygenTank extends Item {
	
	private int oxygenPoints;
	
	private Actor carrier; 

	public OxygenTank(Actor newCarrier) {
		super("Oxygen tank", 'O');
		oxygenPoints = 10;
		carrier = newCarrier;
	}
	
	
	/**
	 * method to change( subtract) the oxygen points of the tank
	 */
	public void usedOxygen() {
		this.oxygenPoints -=1;
	}
	
	/**
	 * method used by other classes, checking how many oxygen tank is this tank left
	 * @return int the amount of the oxygen points
	 */
	
	public int getPoints() {
		return oxygenPoints;
	}
	

}
