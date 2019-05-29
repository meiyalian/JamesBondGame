package game.Actors;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;
import edu.monash.fit2099.engine.SkipTurnAction;
import game.Actions.Throw;
import game.Items.Key;
import game.Items.StunPowder;
/**
 * 
 * The class for Ninjas, Ninja will throw stun powders at
 *  player if the player is within 5 distance, and backing away by 1 distance.
 *
 */
public class Ninja extends NewActor{
	
	private NewActor target;

	/**
	 * Constructor for Ninja.
	 * @param name: The name for each Ninja.
	 * @param player: The current player.
	 */
	public Ninja(String name, NewActor player) {
		super(name, 'n', 5,20);
		target = player;
		
		Key item = new Key();
		item.getAllowableActions().clear();
		item.getAllowableActions().add(new DropItemAction(item));
		this.addItemToInventory(item);
	}
	


	@Override
	/**
	 *  If the distance between Ninja and player is less or equal to 5, ninja will throw a bag 
	 *  of stun powder to the player and step away from player 
	 */
	public Action playTurn(Actions actions, GameMap map, Display display) {
		
		Location here = map.locationOf(this);
		Location there = map.locationOf(target);
		
		
		int currentDistance = distance(here,there);
		
		if(currentDistance > 5) {
			return new SkipTurnAction();
		}
		
		else {
			
			
			if(!target.checkStun()) {
				StunPowder sp = new StunPowder(target);
				Throw t = new Throw(this, target,sp);
				String result = t.execute(this, map);
				display.println(result);
			}
			
			
			
			
			for (Exit exit : map.locationOf(this).getExits()) {
				Location destination = exit.getDestination();
				
				if (destination.canActorEnter(this) && distance(destination,there) > currentDistance) {
					return new MoveActorAction(destination, exit.getName());
				}
			}

			}
		return super.playTurn(actions,  map,  display);
			
		}

	
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}

}
