package game;


import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;

public class OxygenTank extends Item {
	
	private int oxygenPoints;
	
	private Actor carrier; 

	public OxygenTank(Actor newCarrier) {
		super("Oxygen tank", 'O');
		oxygenPoints = 10;
		carrier = newCarrier;
	}
	
	
	
	
	
	

}
