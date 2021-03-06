package game.Actors;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.AttackAction;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.SkipTurnAction;
import game.Actions.ActionFactory;
import game.Actions.GiveAction;
import game.Actions.GiveAndDisappearAction;
import game.Actions.TalkAction;
import game.Actions.WanderingBehaviour;
import game.Items.RocketBody;
import game.Items.RocketPlan;


/**
 * Class for character Q
 * Q will wander around the map, can talk to the player, can give/receive item from/to player, and disappears.
 */
public class Q extends NewActor{
	
	private NewPlayer protagonist;
	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();
	private Display display;
	
	/**
	 * Constructor for Q.
	 * @param name: The name for Q.
	 * @param player: The current player.
	 */
	public Q(String name, NewPlayer player) {
		super(name,'Q', 5, 10);
		protagonist = player;
		addBehaviour(new WanderingBehaviour());
		
		RocketBody item = new RocketBody();
		item.getAllowableActions().clear();
		item.getAllowableActions().add(new DropItemAction(item));
		this.addItemToInventory(item);
		
	}
	
	@Override
	/**
	 * Method for implementing Talking.
	 * If an actor is next to Q, they can talk to Q. And the dialogue will be different based on whether they have RocketPlan in their inventory.
	 */
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		
		Actions actions = new Actions();
		
		String dialogue = "I can give you something that will help, but I am going to need the plans.";
		for (Item item : otherActor.getInventory())
			if (item instanceof RocketPlan) {
				actions.add(new GiveAction(otherActor, this, item));
				dialogue = "Hand them over, I don't have all day!";
				break;
			}
		actions.add(new TalkAction(otherActor, this, dialogue));
		
		return actions;
	}
	
	private void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
	}

	
	
	@Override
	/**
	 * Method for implementing Give And Disappearing of Q.
	 * Q does not have RocketPlan to begin with, and player is the only one who can give items.
	 * So if Q has RocketPlan in his/her inventory, then it's the player who gave it to him/her.
	 * Hence if Q has RocketPlan, then give RocketBody to the player, then disappears from the map.
	 */
	public Action playTurn(Actions actions, GameMap map, Display display) {
		this.display = display;
		
		Item rocketBody = null;
		boolean give = false;
		for (Item item : this.getInventory()) {
			if (item instanceof RocketBody)
				rocketBody = item;
			if (item instanceof RocketPlan)
				give = true;
		}
			
		if (give)
			return new GiveAndDisappearAction(this, this.protagonist, rocketBody, "has disappeared with a cheery wave.");
				
		
		for (ActionFactory factory : actionFactories) {
			Action action = factory.getAction(this, map);
			if(action != null && !(action instanceof AttackAction))
				return action;
		}
		return super.playTurn(actions,  map,  display);
	}


/**
 * calculate the distance between 2 actors 
 */
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}

	



}
