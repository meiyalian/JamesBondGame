package game;

import edu.monash.fit2099.engine.Item;

/**
 *  medicine item -- can be taken by actors directly or store in inventory and be used later 
 */
public class Medicine extends Item{
	
	private int healing_point;
	
	/**
	 *  @param hp the amount of hit points the actor will receive when taking this medicine
	 */
	public Medicine(int hp, int x, int y) {
		super("medicine",'*');
		
		this.healing_point = hp;
		allowableActions.add(new GetHeal(hp,x,y,this));
		
	}

	public int getHp() {
		return healing_point;
	}

}
