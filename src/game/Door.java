package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.AttackAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class Door extends Ground{
	
	private boolean locked;

	public Door() {
		super('+');
		this.locked = true;
	}

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
	
	@Override
	public boolean canActorEnter(Actor actor) {
		return !this.locked;
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return this.locked;
	}
	
	public boolean isLocked() {
		return this.locked;
	}
	
	public void unlock(Key key) {
		this.locked = !this.locked;
		if (this.locked)
			this.displayChar = '+';
		else
			this.displayChar = '|';
	}

}
