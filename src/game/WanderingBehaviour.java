package game;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;

public class WanderingBehaviour implements ActionFactory {
	
	// for generate random step
	private Random rand = new Random();
	

	@Override
	public Action getAction(Actor actor, GameMap map) {

		ArrayList<Location> locations = new ArrayList<Location>();
		
		Location here = map.locationOf(actor);
		
		ArrayList<Exit> exits = (ArrayList<Exit>) here.getExits();
		for (Exit exit : exits) {
			Location destination = exit.getDestination();
			if (destination.canActorEnter(actor)) {
				locations.add(destination);
			}
			
		}
		if (locations.size()>0) {
			int randomIndex = rand.nextInt( locations.size());
			Location randomStep = locations.get(randomIndex);
			return new MoveActorAction(randomStep,exits.get(randomIndex).getName());
		}
			
		return null;
	}
	
	

}
