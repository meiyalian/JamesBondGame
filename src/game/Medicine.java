package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;

/**
 *  medicine item -- can be taken by actors directly or store in inventory and be used later 
 */
public class Medicine extends Item{
	
	private int healing_point;
	private boolean isFp;
	
	/**
	 *  @param hp the amount of hit points the actor will receive when taking this medicine
	 */
	public Medicine(int hp, int x, int y,boolean isfp) {
		super("medicine",'*');
		isFp = isfp;
		this.healing_point = hp;
		
		if (isFp) {
			for(Action a: allowableActions) {
				if (a instanceof PickUpItemAction) {
					allowableActions.remove(a);
				}
			}
			allowableActions.add(new GetHeal(hp,x,y,this,true));
			
		}
		
		else {
			allowableActions.add(new GetHeal(hp,x,y,this,false));
		}
		
		
	}

	public int getHp() {
		return healing_point;
	}

}
