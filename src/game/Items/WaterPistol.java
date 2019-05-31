package game.Items;

import java.lang.Math;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponItem;
import game.Throwable;
import game.Actors.NewActor;

public class WaterPistol extends Item {
	
	public boolean isFilled;
	
	public WaterPistol() {
		super("Water Pistol", 'P');
		this.isFilled = false;
		
	}

}
