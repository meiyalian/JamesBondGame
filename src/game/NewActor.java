package game;

import edu.monash.fit2099.engine.Actor;

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
	
	
	
}
