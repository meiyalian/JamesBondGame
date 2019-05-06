package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponItem;

public class Throw extends Action{
	
	private Actor actor;
	private Actor subject;
	private Throwable item;
	
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
			return actor + " misses " + subject + ".";
		}
		
		
		
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String hotKey() {
		// TODO Auto-generated method stub
		return null;
	}

}
