package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class RocketPadActor extends NewActor{

	private RocketPad parent;
	
	public RocketPadActor(RocketPad callingClass) {
		super("rocketPadActorInterface", 'E', 0, 999);
		this.parent = callingClass;
	}

	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		
		for (Item item : this.getInventory()) {
			
			display.println(Character.toString(item.getDisplayChar()));
			display.println("dsauhfso");
			
			if (item instanceof RocketBody) {
				parent.addBody();
				this.removeItemFromInventory(item);
			}
			
			if (item instanceof RocketEngine) {
				parent.addEngine();
				this.removeItemFromInventory(item);
			}
		}
		
		return null;
	}
}
