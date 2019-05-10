package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
/**
 * 
 * The abstract class for implementing behaviors.
 * Behaviors are continuous Actions that will keep being executed for each turn.
 *
 */
public interface ActionFactory {
	Action getAction(Actor actor, GameMap map);
}
