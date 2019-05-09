package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;

/**
 * Abstract Class extends from the Actor class in the engine code. 
 * Add more features and functions for an actor
 */
public abstract class NewActor extends Actor{
	
	
	protected boolean isStunned = false; // the state of an actor (stunned / not stunned) 

	
	public NewActor(String name, char displayChar, int priority, int hitPoints) {
		super(name, displayChar, priority, hitPoints);
	}
	
	
	/**
	 * method for setting the actor to a stunned state.
	 * override this method to invoke different actions when an actor is being stunned. 
	 * @return None	
	 */
	public void beStunned() {
		isStunned = true;
	}
	
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		
		for (Action action : actions) {
			if (action instanceof DropItemAction || action instanceof PickUpItemAction)
				actions.remove(action);
		}
		return super.playTurn(actions,  map,  display);
	}
//	
//	public boolean hasItem(String name) {
//		for (Item i : inventory) {
//			if (i.toString().equals(name)) {
//				return true;
//			}
//		}
//		return false;
//	}
}
