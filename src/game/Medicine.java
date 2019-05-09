package game;

import edu.monash.fit2099.engine.Item;

/**
 *  medicine item -- can be taken by actors directly or store in inventory and be used later 
 */
public class Medicine extends Item{
	/**
	 *  @param hp the amount of hit points the actor will receive when taking this medicine
	 */
	private int healing_point;
	public Medicine(int hp) {
		super("medicine",'*');
		allowableActions.add(new GetHeal(hp));
		
	}

	public int getHp() {
		return healing_point;
	}

}
