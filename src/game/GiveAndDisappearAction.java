package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
/**
 * Class for the give and disappear action. This action will let the actor.
 * Give to another actor something and then disappears (removed from map).
 */

public class GiveAndDisappearAction extends Action{

	Actor actor;
	Actor target;
	Item item;
	String description;
	
	/**
	 * Constructor for GiveAndDisappearAction.
	 * @param actor: Actor that gives item, then will be disappeared.
	 * @param target: The receiver of the item.
	 * @param item: The item to be given/received.
	 * @param description: The message to be printed after the actor disappears.
	 */
	public GiveAndDisappearAction(Actor actor, Actor target, Item item, String description) {
		this.actor = actor;
		this.target = target;
		this.item = item;
		this.description = description;
	}

	@Override
	/**
	 * Execution of the GiveAndDisappearAction.
	 */
	public String execute(Actor actor, GameMap map) {
		
		GiveAction give = new GiveAction(this.actor, this.target, this.item);
		String result = give.execute(this.actor, map) + "\n";
		map.removeActor(actor);
		
		result += actor.toString() + " " + description;
		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor.toString() + " give item to " + target.toString() + " and disappears.";
	}

	@Override
	public String hotKey() {
		return "";
	}
}
