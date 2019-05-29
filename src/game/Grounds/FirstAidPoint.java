package game.Grounds;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.Actors.NewActor;
import game.Actors.NewPlayer;
import game.Items.Medicine;

public class FirstAidPoint extends Ground{
	
	private int pos_x;
	private int pos_y;
	private GameMap gamemap;
	

	public FirstAidPoint(GameMap gp, int x, int y) {
		super('~');
		pos_x = x;
		pos_y = y;
		gamemap = gp;
	}
	
	
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction){
		Actions actions = new Actions();
		if(actor instanceof NewPlayer) {
			if(((NewPlayer) actor).getHitpoint()<=10) {
				
				
				if (((NewActor) actor).getHitpoint()<50) {
					gamemap.at(pos_x,pos_y).addItem(new Medicine(15,pos_x,pos_y,true));
				}
				
			}
			
		}
		
		
		return actions;
	}
	

	@Override
	public boolean canActorEnter(Actor actor) {
		if (actor instanceof NewPlayer && ((NewPlayer) actor).getHitpoint()<=10) {
			return true;
		}
		return false;
	}
}
