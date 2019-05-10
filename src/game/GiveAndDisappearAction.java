package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * class for the give and dissapear action. This action will let the actor 
 * give to another actor something and then dissapears
 */
public class GiveAndDisappearAction extends Action{

	Actor actor;
	Actor target;
	Item item;
	String description;
	
	public GiveAndDisappearAction(Actor actor, Actor target, Item item, String description) {
		this.actor = actor;
		this.target = target;
		this.item = item;
		this.description = description;
	}

	@Override
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
