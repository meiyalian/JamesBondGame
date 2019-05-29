package game.Actors;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.AttackAction;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.SkipTurnAction;


/**
 *  Soldier class -- soldier will always stay in the same position and trying to defend something or someone
 */

public class Soldier extends NewActor {
	
	private NewActor target; 

	public Soldier(String name, NewActor player) {
		super(name, '!', 2, 20);
		this.target = player;
	}
	
	@Override
	/**
	 * Method to make Soldiers to stay still, and attacks anyone nears them.
	 */
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
