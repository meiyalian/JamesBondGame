package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class RocketPad extends Ground{
	
	private RocketPadActor actorInterface = new RocketPadActor(this);
	private NewActor player;
	private boolean hasBody;
	private boolean hasEngine;
	
	public RocketPad() {
		super('R');
		this.hasBody = false;
		this.hasEngine = false;
	}
	
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction) {
		
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
		
		Actions actions = new Actions();
		
		if (!(this.hasBody && this.hasEngine)) {
			for (Item item : actor.getInventory()) {
				if (item instanceof RocketBody && this.hasBody == false)
					actions.add(new GiveAction(actor, this.actorInterface, item));
			
				if (item instanceof RocketEngine && this.hasEngine == false)
					actions.add(new GiveAction(actor, this.actorInterface, item));
			}
		}
		else {
			System.out.println("THE ROCKET HAS ALL THE PARTS, HAHAHA WE WIN!");
		}
		return actions;
	}
	
	@Override
	public boolean canActorEnter(Actor actor) {
		return (hasBody && hasEngine);
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return !(hasBody || hasEngine);
	}
	
	public void addEngine() {
		this.hasEngine = true;
	}
	
	public void addBody() {
		this.hasBody = true;
	}

}
