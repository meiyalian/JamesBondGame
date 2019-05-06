package game;

/**
 * Interface that defines object that can be throw.
 */
public interface Throwable {
	
	/**
	 * defines the possibility of the object hit an object 
	 * @return a boolean value check if the target is hit by this object 
	 */
	public boolean isHit();
	
	/**
	 * Perform the affect of this object when it is hitting to the target .
	 *
	 */
	public void affect();
	
}
