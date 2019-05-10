package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Class for character Goons. Goons also follows player and does twice damage as Grunts.
 */
public class Goon extends NewActor {
	
	private Display display;
	private Actor target;
	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();
	

	/**
	 * Constructor for Goons.
	 * @param name: The name for the individual goon.
	 * @param player: The current player.
	 */
	public Goon(String name, Actor player) {
		super(name, 'o', 12, 30);
		this.damage = 10;
		
		addBehaviour(new FollowBehaviour(player));
		this.target = player;
		
		
		Key item = new Key();
		item.getAllowableActions().clear();
		item.getAllowableActions().add(new DropItemAction(item));
		this.addItemToInventory(item);
	}
	
	
	private void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
	}
	
	
	/**
	 * There is 10% chance on each turn of the Goon shouting an insult at the player. 
	 */
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		
		// there is 10% chance on each turn of the Goon shouting an insult at the player. 
		double random = Math.random();
		if (random < 0.1) {
			ShoutAction shout = new ShoutAction(this, target);
			String result = shout.execute(this, map);
			display.println(result);
		}
		
		Action action;
		for (ActionFactory factory : actionFactories) {
			action = factory.getAction(this, map);
			if(action != null)
				return action;
		}
		return super.playTurn(actions,  map,  display);
	}
}
