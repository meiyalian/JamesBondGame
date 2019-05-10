package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;
/**
 * Class for the rocket pad location. The player can place the rocket body and engine here to build a rocket.
 * 
 */

public class RocketPad extends Ground{
	
	private Actor actorInterface = new Actor("RocketPadActor", 'R', 1, 1000);
	private boolean hasBody;
	private boolean hasEngine;
	
	/**
	 * Constructor initializes the rocket pad without any parts present.
	 * 
	 */
	public RocketPad() {
		super('R');
		this.hasBody = false;
		this.hasEngine = false;
	}
	
	
	/**
	 * Method that determines what actions the player may make regarding the rocket pad each turn.
	 * 
	 * @param actor the actor querying the class for available actions (usually the player).
	 * @param location the location of the class being queried.
	 * @param direction the direction of the ground from the actor.
	 * @return actions a set of actions available to the actor.
	 */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction) {
		Actions actions = new Actions();
			
		for (Item item : this.actorInterface.getInventory()) {
			if (item instanceof RocketBody) {
				this.addBody();
				this.actorInterface.removeItemFromInventory(item);
			}
			
			if (item instanceof RocketEngine) {
				this.addEngine();
				this.actorInterface.removeItemFromInventory(item);
			}
		}
		
		// An abstract actor class acts as an interface allowing the player to utilize the GiveAction action on a non-actor entity.
		if (this.hasBody == false || this.hasEngine == false) {
			for (Item item : actor.getInventory()) {
				if (item instanceof RocketBody && this.hasBody == false)
					actions.add(new GiveAction(actor, this.actorInterface, item));
			
				if (item instanceof RocketEngine && this.hasEngine == false)
					actions.add(new GiveAction(actor, this.actorInterface, item));
			}
		}
		return actions;
	}
	
	
	/**
	 * Returns the ability of the player to enter the rocket pad. The player may only enter when all components are present.
	 * 
	 * @param actor the actor querying this class.
	 * @return true if both parts are present, else false.
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return (hasBody && hasEngine);
	}
	
	/**
	 * Returns whether this location blocks thrown objects. The location only blocks thrown objects if one or more components are present.
	 * 
	 * @return true if the pad is empty, else false.
	 */
	@Override
	public boolean blocksThrownObjects() {
		return !(hasBody || hasEngine);
	}
	
	/**
	 * Sets the current state of the rocket pad to containing an engine after the player has added it to the pad.
	 * 
	 */
	private void addEngine() {
		this.hasEngine = true;
	}
	
	/**
	 * Sets the current state of the rocket pad to containing a body after teh player has added it to the pad.
	 * 
	 */
	private void addBody() {
		this.hasBody = true;
	}

}
