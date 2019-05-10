package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class ModifyDamage extends Action{

	private int damage;
	private String status;
	
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
