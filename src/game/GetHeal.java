package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;



/**
 *  action that can be triggered when having a medicine item 
 */
public class GetHeal extends Action{
	
	private int healing_point;
	
	public GetHeal(int hp) {
		healing_point = hp;
		
	}

	/**
	 *  take the medicine and increase hit point 
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		actor.heal(healing_point);
		for (Item i: actor.getInventory()) {
			if (i instanceof Medicine) {
				actor.removeItemFromInventory(i);
				break;
			}
		}
		return actor.toString() + " take the medicine and get healed by "+ String.valueOf(healing_point) + " points.";
		
	}

	@Override
	public String menuDescription(Actor actor) {
		return "take medicine";
		
	}

	@Override
	public String hotKey() {
		return "";
	}
	

}
