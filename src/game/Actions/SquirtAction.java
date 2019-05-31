package game.Actions;

import java.lang.Math;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Actors.YugoMaxx;
import game.Items.WaterPistol;

public class SquirtAction extends Action{

	Actor castingActor;
	YugoMaxx targettedActor;
	WaterPistol pistol;
	
	public SquirtAction(Actor caster, YugoMaxx target, WaterPistol item) {
		this.castingActor = caster;
		this.targettedActor = target;
		this.pistol = item;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		
		double random = Math.random();		
		if (random < 0.3) {
			this.pistol.isFilled = false;
			return "You missed!";
		}
		else {
			this.pistol.isFilled = false;
			this.targettedActor.DestroyExoskeleton();
			return "You hit Yugo Maxx, destroying his exoskeleton!";
		}	
	}

	@Override
	public String menuDescription(Actor actor) {
		String result = "Squirt " + this.targettedActor.toString() + " with water pistol.";
		return result;
	}

	@Override
	public String hotKey() {
		return "";
	}

}
