package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class GiveAction extends Action{
	
	
	/**
	 * class for the give action
	 */

	// When using Give, the class assumes the actor already have the item,
	// So the item should be checked for its existence before calling Give.
	
	Actor actor, target;
	Item item;
	
	public GiveAction(Actor actor, Actor target, Item item) {
		this.actor = actor;
		this.target = target;
		this.item = item;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		target.addItemToInventory(item);
		actor.removeItemFromInventory(item);
		
		String result = actor.toString() + " has given " + item.toString() + " to " + target.toString() + ".";
		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor.toString() + " give " + item.toString() +" to " + target.toString();
	}

	@Override
	public String hotKey() {
		return "";
	}

}