package game.Grounds;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.Actions.FillWaterPistolAction;
import game.Actions.GiveAction;
import game.Items.RocketBody;
import game.Items.RocketEngine;
import game.Items.WaterPistol;

public class Water extends Ground{

	public Water() {
		super('W');
	}
	
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction) {
		Actions actions = new Actions();

		for (Item item : actor.getInventory()) {
			if (item instanceof WaterPistol) {
				WaterPistol tempItem = (WaterPistol) item;
				if (tempItem.isFilled == false) {
					actions.add(new FillWaterPistolAction(actor, tempItem));
				}
			}
		}
		return actions;
	}
}
