package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.SkipTurnAction;


/**
 * Class for character Q
 */
public class Q extends NewActor{
	
	private  NewPlayer protagonist;
	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();
	private Display display;
	

	public Q(String name, NewPlayer player) {
		super(name,'Q', 5, 10);
		protagonist = player;
		addBehaviour(new WanderingBehaviour());
		
		
		
	}
	
	private void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
	}

	
	
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		this.display = display;
		
	
		
		for (ActionFactory factory : actionFactories) {
			Action action = factory.getAction(this, map);
			if(action != null ) {
				return action;
		}
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
