package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.GameMap;

/**
 * 
 * The enhanced version of Soldier, much stronger and follows the player instead of standing still.
 *
 */
public class SuperSoldier extends Soldier {

	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();
	
	
	/**
	 * Constructor for Super Soldiers.
	 * @param name: SuperSoldier's name.
	 * @param player: The current player.
	 */
	public SuperSoldier(String name, NewActor player) {
		super(name, player);
		this.damage = 15;
		this.maxHitPoints = 50;
		this.hitPoints = 50;
		this.addBehaviour(new FollowBehaviour(player));
		
		Key key = new Key();
		key.getAllowableActions().clear();
		key.getAllowableActions().add(new DropItemAction(key));
		this.addItemToInventory(key);
	}

	private void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
	}

	@Override
	/**
	 * Method for implementing following the player, if near player let super class to handle action.
	 */
	public Action playTurn(Actions actions, GameMap map, Display display) {
		for (ActionFactory factory : actionFactories) {
			Action action = factory.getAction(this, map);
			if(action != null)
				return action;
		}
		
		return super.playTurn(actions,  map,  display);
	}
	
}
