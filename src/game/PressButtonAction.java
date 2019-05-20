package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;



/**
 * Class for the press button action ( when the 
 * action is invoked, the oxygen dispenser will produce oxygen)
 * 
 */

public class PressButtonAction extends Action {
	
	private OxygenDispenser dispenser;
	private String produceDescrip;
	
	public PressButtonAction(OxygenDispenser d, String itemToProduce) {
		this.dispenser = d;
		this.produceDescrip = itemToProduce;
		
	}

	
	@Override
	public String execute(Actor actor, GameMap map) {
		
		String produce = dispenser.produce();
		return produce ;
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Press the button of " + (dispenser).description() + 
				" to produce " + this.produceDescrip;
	}

	@Override
	public String hotKey() {
		return "";
	}
	
	

}
