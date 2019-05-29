package game.Actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
/**
 * Class for an actor to give an item to another actor.
 * When using Give, the class assumes the actor already have the item,
 * So the item should be checked for its existence before calling Give.
 */

public class GiveAction extends Action{

	
	Actor actor, target;
	Item item;
	
	/**
	 * Constructor for Give Action
	 * @param actor: The actor that gives item.
	 * @param target: The actor that receives item.
	 * @param item: The item to be given/received.
	 */
	public GiveAction(Actor actor, Actor target, Item item) {
		this.actor = actor;
		this.target = target;
		this.item = item;
	}

	@Override
	/**
	 * Give the item to the target, while also removes it from the giver's inventory.
	 */
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