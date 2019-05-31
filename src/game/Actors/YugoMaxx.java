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
import game.Actions.SquirtAction;
import game.Actions.WanderingBehaviour;
import game.Items.RocketBody;
import game.Items.RocketEngine;
import game.Items.UnconsciousYugoMaxx;
import game.Items.WaterPistol;

public class YugoMaxx extends NewActor{
	
	private boolean hasExoskeleton = true;
	private NewActor player;
	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();
	
	public YugoMaxx(String name, NewActor player) {
			
			super(name, 'Y', 3, 20);
			this.player = player;
			this.damage = 10;
			addBehaviour(new WanderingBehaviour());
			
				
			UnconsciousYugoMaxx item = new UnconsciousYugoMaxx();
			item.getAllowableActions().clear();
			item.getAllowableActions().add(new DropItemAction(item));
			this.addItemToInventory(item);
		}
	
	public void DestroyExoskeleton() {
		this.hasExoskeleton = false;
	}
	
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions actions = new Actions();

		if (hasExoskeleton) {
			for (Item item : otherActor.getInventory()) {
				if (item instanceof WaterPistol) {
					actions.add(new SquirtAction(this.player, this, (WaterPistol) item));
					break;
				}
			}
		}
		return actions;
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
