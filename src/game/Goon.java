package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;

public class Goon extends Actor {
	
	private Display display;
	private Actor target;

	// Goon have 100 hit points and are always represented with an 'o'
	public Goon(String name, Actor player) {
		super(name, 'o', 5, 100);
		addBehaviour(new FollowBehaviour(player));
		target = player;
	
	}
	
	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

	
	
	private void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
		
	}
	
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		
		// there is 10% chance on each turn of the Goon shouting an insult at the player. 
		double random = Math.random();
		if (random < 0.1) {
			ShoutAction shout = new ShoutAction(this, target);
			String result = shout.execute(this, map);
			display.println(result);
		}
		
		for (ActionFactory factory : actionFactories) {
			Action action = factory.getAction(this, map);
			if(action != null)
				return action;
		}
		
		return super.playTurn(actions,  map,  display);
	}

	
	
	
	
}
