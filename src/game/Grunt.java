package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;



/**
 * 
 * The class for Grunts, Grunts follows and attacks the player.
 *
 */
public class Grunt extends NewActor {

	/**
	 * Constructor for Grunts.
	 * @param name: The name for the individual Grunts.
	 * @param player: The current player.
	 */
	public Grunt(String name, Actor player) {
		super(name, 'g', 5, 30);
		addBehaviour(new FollowBehaviour(player));
		
		Key item = new Key();
		item.getAllowableActions().clear();
		item.getAllowableActions().add(new DropItemAction(item));
		this.addItemToInventory(item);
	}

	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

	private void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
	}

	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		for (ActionFactory factory : actionFactories) {
			Action action = factory.getAction(this, map);
			if(action != null)
				return action;
		}
		
		return super.playTurn(actions,  map,  display);
	}
	

}
