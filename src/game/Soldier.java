package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.AttackAction;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.SkipTurnAction;



/**
 *  Soldier class -- soldier will always stay in the same position and trying to defend something 
 */

public class Soldier extends NewActor {
	
	private NewActor target;

	public Soldier(String name,NewPlayer player) {
		super(name, '!', 2, 30);
		target = player;
	}
	
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		Location here = map.locationOf(this);
		Location there = map.locationOf(target);
		
		if (Math.abs(here.x() - there.x()) + Math.abs(here.y() - there.y()) <=1){
			return new AttackAction(this, target);
		}
		
		else {
			return new SkipTurnAction();
		}
	}
	
	
}
