package game.Actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.Grounds.Door;
import game.Items.Key;

/**
 * 
 * This class is the action to unlock or lock a door.
 * The door can be locked or unlocked.
 * 
 */

public class UnlockAction extends Action {
	
	Actor actor;
	Key key;
	Door door;
	
	/**
	 * Constructor for UnlockAction
	 * @param actor: The actor that unlocks/locks a door.
	 * @param key: The key used to unlock/lock the door.
	 * @param door: The door object.
	 */
	public UnlockAction(Actor actor, Key key, Door door) {
		this.actor = actor;
		this.key = key;
		this.door = door;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		
		this.door.unlock(key);
		this.actor.removeItemFromInventory(key);
		return this.menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		String status = "unlocks";
		if (!this.door.blocksThrownObjects())
			status = "locks";
		return actor.toString() + " " + status +" the door.";
	}

	@Override
	public String hotKey() {
		return "";
	}

}
