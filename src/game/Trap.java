package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class Trap extends Item {
	
	private GameMap map;
	private boolean triggered;

	private ArrayList<Actor> spawnActors = new ArrayList<>();
	private ArrayList<int[]> spawnActorsLoc = new ArrayList<>();
	
	private ArrayList<Actor> removeActors = new ArrayList<>();
	
	public Trap(GameMap map) {
		super("Trap", 't');
		this.map = map;
		this.triggered = false;
		this.allowableActions.clear();
	}
	
	
	public void spawnActor(Actor actor, int[] location) {
		this.spawnActors.add(actor);
		this.spawnActorsLoc.add(location);
	}
	
	public void removeActor(Actor actor) {
		this.removeActors.add(actor);
	}
	

	@Override
	public Actions getAllowableActions() {
		
		if (!this.triggered) {
			for (Actor remActor : this.removeActors)
				this.map.removeActor(remActor);
			
			for (int i = 0; i < this.spawnActors.size(); i++) 
				this.map.addActor(spawnActors.get(i), spawnActorsLoc.get(i)[0], spawnActorsLoc.get(i)[1]);

			this.triggered = true;
		}
			
		return super.getAllowableActions();
	}
}
