package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;



/**
 *  Action that can be triggered when having a medicine item 
 */
public class GetHeal extends Action{
	
	private int healing_point;
	private int loca_x;
	private int loca_y;
	private Medicine medicine;
	private boolean isFp;
	
	
	
	public GetHeal(int hp, int x, int y,Medicine item, boolean isfp) {
		healing_point = (hp > 0) ? hp:0;
		loca_x = x;
		loca_y = y;
		medicine = item;
		isFp = isfp;
		
		
	}



	
	
	/**
	 *  take the medicine and increase hit point 
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		
		if(isFp) {
			actor.heal(healing_point);
			map.at(loca_x,loca_y).removeItem(medicine);
		}
		
		else {
			
			
			boolean inInventory = false;
			actor.heal(healing_point);
			for (Item i: actor.getInventory()) {
				if (i instanceof Medicine) {
					actor.removeItemFromInventory(i);
					inInventory = true;
					break;
				}
			}
			
			if (! inInventory) {
				map.at(loca_x,loca_y).removeItem(medicine);
				
			}
			
			
		}
	
		
		return actor.toString() + "get healed by "+ String.valueOf(healing_point) + " points.";
		
	}

	@Override
	public String menuDescription(Actor actor) {
		if(isFp) {
			return "get healed." ;
		}
		return "take medicine";
		
	}

	@Override
	public String hotKey() {
		return "";
	}
	

}
