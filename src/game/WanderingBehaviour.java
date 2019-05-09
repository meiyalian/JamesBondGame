package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;


/**
 * 
 * The class that implements wandering behavior, which makes an actor moves around the map randomly.
 * Note: For each turn the actor only has 50% of chance to move around to help the player easily get in touch with 
 * Q
 *
**/
public class WanderingBehaviour implements ActionFactory {
	
	// for generate random step
	private Random rand = new Random();

	/**
	 *  
	 *  @param actor that perform this behaviour 
	 *
	 *  @return: MoveActorAction random move step
	 *  
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {

		ArrayList<Location> locations = new ArrayList<Location>();
		
		Location here = map.locationOf(actor);
		Location destination;
		
		List<Exit> exits = here.getExits();
		for (Exit exit : exits) {
			destination = exit.getDestination();
			if (destination.canActorEnter(actor))
				locations.add(destination);
		}
		
		
		MoveActorAction moves = null;
		
		if (locations.size()>0) {
			int randomIndex = rand.nextInt(locations.size());
			Location randomStep = locations.get(randomIndex);

			if (rand.nextDouble() < 0.5)
				moves = new MoveActorAction(randomStep, exits.get(randomIndex).getName());
		}
		
		return moves;
	}

}
