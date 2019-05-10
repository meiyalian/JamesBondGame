package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.AttackAction;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;

/**
 * 
 * The class that implements MiniBoss: Doctor Maybe.
 * Doctor Maybe does not move, and has half of damage/hitpoints of a Grunt.
 *
 */
public class DoctorMaybe extends NewActor {
	
	private NewActor player;
	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();
	
	/**
	 * The constructor of Doctor Maybe.
	 * Doctor Maybe deals 3 damage and has 15 hitpoints, and is displayed by character 'd'.
	 * @param name: The display name for this actor.
	 * @param player: The current player.
	 */
	public DoctorMaybe(String name, NewActor player) {
		
		super(name, 'd', 3, 15);
		this.player = player;
		this.damage = 3;
			
		RocketEngine item = new RocketEngine();
		item.getAllowableActions().clear();
		item.getAllowableActions().add(new DropItemAction(item));
		this.addItemToInventory(item);
	}
	
	@Override
	/**
	 * Overrides the Actions from super class, so to make Doctor Maybe not move around the map.
	 * @param actions: Allowable actions in this turn for the current Actor.
	 * @param map: Current map the Actor is in.
	 * @param display: Used to print messages for the user to see.
	 * @return the chosen action to perform in this turn.
	 */
	public Action playTurn(Actions actions, GameMap map, Display display) {

		if (this.distance(map.locationOf(this.player), map.locationOf(this)) <=1)
			return new AttackAction(this, this.player);
		
		for (Action action : actions) {
			if (action instanceof MoveActorAction)
				actions.remove(action);
		}
		
		return super.playTurn(actions,  map,  display);
	}
	
	
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}

}
