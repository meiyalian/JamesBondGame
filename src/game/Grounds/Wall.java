package game.Grounds;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
/**
 * 
 * A regular wall (Ground object) that blocks actor from entering its location.
 *
 */
public class Wall extends Ground {

	public Wall() {
		super('#');
	}
	
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
