package game.Items;

import edu.monash.fit2099.engine.Item;
import game.Actions.ModifyDamage;
/**
 * 
 * The class for magic pills, magic pills modifies an actor's damage.
 *
 */
public class MagicPill extends Item {

	int value;
	
	/**
	 * Constructor for MagicPills
	 * @param value: How much does the damage attribute changes if used the magic pill. Positive for increase, negative for decrease. (Please don't use 0 it's useless)
	 */
	public MagicPill(int value) {
		super("Magic Pill", '?');
		this.value = value;
		this.allowableActions.clear();
		this.getAllowableActions().add(new ModifyDamage(value));
	}

}
