package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
/**
 * 
 * Action that modifies an actor's damage. 
 *
 */
public class ModifyDamage extends Action{

	private int damage;
	private String status;
	
	/**
	 * Constructor for ModifyDamageActions
	 * @param damage: How much damage does it change for the actor. (If the calculated damage is less than 0, then actor's damage will be set to 0 instead of negative damage)
	 */
	public ModifyDamage(int damage) {
		this.damage = damage;
		
		this.status = "Increase";
		if (damage < 0)
			status = "Decrease";
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		
		((NewActor)actor).setDamage(((NewActor)actor).getDamage() + this.damage);
		return actor + "'s damage has " + status + "d by " + damage;
	}

	@Override
	public String menuDescription(Actor actor) {
		return status + "s " + actor + "'s damage by " + damage;
	}

	@Override
	public String hotKey() {
		return "";
	}

}
