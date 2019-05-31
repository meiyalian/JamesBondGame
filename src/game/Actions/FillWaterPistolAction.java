package game.Actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Items.WaterPistol;

public class FillWaterPistolAction extends Action{

	WaterPistol pistol;
	Actor holdingActor;
	
	public FillWaterPistolAction(Actor actor, WaterPistol item) {
		this.pistol = item;
		this.holdingActor = actor;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		this.pistol.isFilled = true;
		String result = actor.toString() + "has filled their water pistol with water.";
		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Fill water pistol with water";
	}

	@Override
	public String hotKey() {
		return "";
	}
}
