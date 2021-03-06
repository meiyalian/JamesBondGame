package game.Actions;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponItem;
import game.Throwable;
/**
 * Class for throw action 
 */
public class Throw extends Action{
	
	private Actor actor;
	private Actor subject;
	private Throwable item;
	
	/**
	 * Constructor of ThrowActions.
	 * @param newActor: The thrower.
	 * @param newSubject: The actor being thrown an item at.
	 * @param newItem: The item to be threw at the subject.
	 */
	public Throw(Actor newActor, Actor newSubject, Throwable newItem) {
		this.actor = newActor;
		this.subject = newSubject;
		this.item = newItem;
		
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
	
		if (item.isHit()) {
			int damage = ((WeaponItem) item).damage();
			String result = actor + " " + ((WeaponItem) item).verb() + " " + subject + " for " + damage + " damage.";	
			item.affect();
			
			return result;
		}
		else {
			return actor + " misses throwing " + item.toString() + " to " + subject + ".";
		}
		
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Throw " + item.toString() + " to " + subject.toString();
	}

	@Override
	public String hotKey() {
		return "";
	}

}
