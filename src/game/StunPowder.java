package game;

import java.lang.Math;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponItem;




/**
 * Class for the stun powder which is an weaponItem that can be thrown by an actor
 *  and has 50% of chance to hit the target. The target is stunned (skip 2 turns) 
 *  if the stun powder hits them, and has no effect if the target is already stunned.
 */
public class StunPowder extends WeaponItem implements Throwable{
	
	private NewActor subject;
	
	/**
	 * The constructor
	 *
	 * @param the target to hit
	 * 
	 */

	public StunPowder(NewActor subjectActor) {
		super("Stun Powder",'s', 0, "stunned");
		subject =subjectActor;
		
	}



	/**
	 * used by the throw action, has 50% chance to hit the target and stun the target
	 *
	 * @param the target to hit
	 * 
	 */
	@Override
	public boolean isHit() {
		double random = Math.random();		
		if (random < 0.1) {
			return false;
		}
		else {			
			return true;
		}	
	}

	/**
	 * the effect caused by this object
	 * 
	 */

	@Override
	public void affect() {
		subject.beStunned();
		
		
		
	}
	
	



	
}
