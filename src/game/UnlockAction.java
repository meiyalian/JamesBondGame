package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * 
 * This class is the action to unlock a door.
 * (Logics still not finished, placeholder class for the moment)
 */

public class UnlockAction extends Action {
	
	Actor actor;
	Key key;
	Door door;
	
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
