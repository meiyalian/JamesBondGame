package game.Items;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.Actors.NewPlayer;

/**
 * 
 * A trap (item), an actor has to be at its location to triggers.
 * Theoretically has the ability to do almost anything to the map it's in, including adding/removing actors.
 *
 */
public class Trap extends Item {
	
	private GameMap map;
	private Location location;
	private boolean triggerOnce, triggered;

	private NewPlayer player;
	
	private ArrayList<Actor> spawnActors = new ArrayList<>();
	private ArrayList<int[]> spawnActorsLoc = new ArrayList<>();
	
	private ArrayList<Actor> removeActors = new ArrayList<>();
	private int damageHealth = 0; 
	
	/**
	 * Constructor for traps.
	 * @param map: The current map the trap is in.
	 */
	public Trap(GameMap map, int x, int y, boolean triggerOnce, char character) {
		super("Trap", character);
		this.map = map;
		this.location = map.at(x, y);
		this.triggerOnce = triggerOnce;
		this.triggered = false;
		this.allowableActions.clear();
	}
	
	/**
	 * Add an actor to be spawned if the trap is triggered.
	 * @param actor: The actor to be spawned.
	 * @param location: The location of the actor to be spawned at.
	 */
	public void spawnActor(Actor actor, int[] location) {
		this.spawnActors.add(actor);
		this.spawnActorsLoc.add(location);
	}
	
	/**
	 * Add an actor to be removed from the map if the trap is triggered.
	 * @param actor: The actor to be removed.
	 */
	public void removeActor(Actor actor) {
		this.removeActors.add(actor);
	}

	public void setDamageHealth(int damage) {
		this.damageHealth = damage;
	}

	@Override
	/**
	 * Execute all actions (triggered trap)
	 */
	public Actions getAllowableActions() {
		
		if (this.triggerOnce == false || !this.triggered)
			
			if (Math.random() > 0.5) {
				for (Actor remActor : this.removeActors)
					this.map.removeActor(remActor);
				
				for (int i = 0; i < this.spawnActors.size(); i++) 
					this.map.addActor(spawnActors.get(i), spawnActorsLoc.get(i)[0], spawnActorsLoc.get(i)[1]);
				
				this.location.getActor().hurt(this.damageHealth);
				
				this.triggered = true;
			}
			
		return super.getAllowableActions();
	}
}
