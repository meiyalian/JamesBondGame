package game.Actions;

import edu.monash.fit2099.engine.*;

/**
 * 
 * The behavior for an actor to keep following another actor.
 *
 */
public class FollowBehaviour implements ActionFactory {

	private Actor target;

	public FollowBehaviour(Actor subject) {
		this.target = subject;
	}

	@Override
	/**
	 * Method for making the decision of moving to the location that will be closest to the target, 
	 * hence achieving following behavior.
	 */
	public Action getAction(Actor actor, GameMap map) {
		if (!target.isConscious())
			return null;
		
		Location here = map.locationOf(actor);
		Location there = map.locationOf(target);

		int currentDistance = distance(here, there);
		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			if (destination.canActorEnter(actor)) {
				int newDistance = distance(destination, there);
				if (newDistance < currentDistance) {
					return new MoveActorAction(destination, exit.getName());
				}
			}
		}

		return null;
	}

	// Manhattan distance.
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}
}