package game.Grounds;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.AttackAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.Actions.UnlockAction;
import game.Items.Key;

/**
 * Class that implements the door functionality as an extension of map objects.
 * 
 */
public class Door extends Ground{
	
	private boolean locked;

	/**
	 * Constructor for the door, which initially starts locked.
	 */
	public Door() {
		super('+');
		this.locked = true;
	}

	/**
	 * Method that determines what actions the player may make regarding this door each turn.
	 * 
	 * @param actor the actor querying the class for available actions (usually the player).
	 * @param location the location of the class being queried.
	 * @param direction the direction of this door from the actor.
	 * @return actions a set of actions available to the actor. If the player has a key, this will include the option of unlocking the door.
	 */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction){
		Actions actions = new Actions();
		for (Item item : actor.getInventory())
			if (item instanceof Key) {
				actions.add(new UnlockAction(actor, (Key)item, this));
				return actions;
			}
		
		return super.allowableActions(actor, location, direction);
	}
	
	/**
	 * Method that returns a boolean representing if the actor may move through this door or not.
	 * 
	 * @return true if the door is unlocked, else false.
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return !this.locked;
	}
	
	/**
	 * Method that returns a boolean representing if the door blocks thrown objects.
	 * 
	 * @return true if the door is locked, else false.
	 */
	@Override
	public boolean blocksThrownObjects() {
		return this.locked;
	}
	
	/**
	 * Method that returns a boolean representing if the door is locked or not.
	 * 
	 * @return true if the door is locked, else false.
	 */
	public boolean isLocked() {
		return this.locked;
	}
	
	/**
	 * Method that unlocks the door and changed the display character appropriately.
	 * 
	 * @param key the key being used to unlock this door by an actor.
	 */
	public void unlock(Key key) {
		this.locked = !this.locked;
		if (this.locked)
			this.displayChar = '+';
		else
			this.displayChar = '|';
	}

}
