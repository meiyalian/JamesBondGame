package game.Actors;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;

/**
 * Abstract Class extends from the Actor class in the engine code. 
 * Add more features and functions for an actor
 */
public abstract class NewActor extends Actor{
	
	protected int damage = 5;
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
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(damage, "punches");
	}
	
	@Override
	/**
	 * Avoid actors picking up or dropping items. (No affects on player since it's user's choice)
	 */
	public Action playTurn(Actions actions, GameMap map, Display display) {
		
		for (Action action : actions) {
			if (action instanceof DropItemAction || action instanceof PickUpItemAction)
				actions.remove(action);
		}
		return super.playTurn(actions,  map,  display);
	}

	/**
	 * check if the actor has been stunned
	 */
	public boolean checkStun() {
		return isStunned;
	}
	
	@Override
	/**
	 * Only heal if heal points is greater than 0.
	 */
	public void heal(int hp) {
		hitPoints = hitPoints + ((hp > 0) ? hp:0);
		hitPoints = Math.min(hitPoints, maxHitPoints);
	}
	
	@Override
	/**
	 * Only damage if the damage is greater than 0.
	 */
	public void hurt(int hp) {
		hitPoints = hitPoints - ((hp > 0) ? hp:0);
	}
	
	public int getDamage() {
		return this.damage;
	}
	
	public void setDamage(int damage) {
		this.damage = (damage >= 0) ? damage:0;
	}
	
	public int getHitpoint() {
		return this.hitPoints;
	}
	
	
}
