package game;

import edu.monash.fit2099.engine.Item;

public class MagicPill extends Item {

	int value;
	
	public MagicPill(int value) {
		super("Magic Pill", '?');
		this.value = value;
		this.allowableActions.clear();
		this.getAllowableActions().add(new ModifyDamage(value));
	}

}
