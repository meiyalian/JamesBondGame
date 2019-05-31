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
import edu.monash.fit2099.engine.MoveActorAction;
import game.Actions.ActionFactory;
import game.Actions.WanderingBehaviour;
import game.Items.RocketEngine;
import game.Items.UnconsciousYugoMaxx;

public class YugoMaxx extends NewActor{
	
	private boolean hasExoskeleton = false;
	private NewActor player;
	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();
	
	public YugoMaxx(String name, NewActor player) {
			
			super(name, 'Y', 3, 15);
			this.player = player;
			this.damage = 10;
			addBehaviour(new WanderingBehaviour());
			
				
			UnconsciousYugoMaxx item = new UnconsciousYugoMaxx();
			item.getAllowableActions().clear();
			item.getAllowableActions().add(new DropItemAction(item));
			this.addItemToInventory(item);
		}
	
	@Override
	public Actions getAllowableActions() {
		
	}
	
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
	
		if (this.distance(map.locationOf(this.player), map.locationOf(this)) <=1)
			return new AttackAction(this, this.player);
		
		
		return super.playTurn(actions,  map,  display);
	}

	private void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
	}
	
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}
}
