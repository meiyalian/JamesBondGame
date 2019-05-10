package game;

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


/**
 * Class for character Q
 */
public class Q extends NewActor{
	
	private NewPlayer protagonist;
	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();
	private Display display;
	

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
