//package game;
//
//import edu.monash.fit2099.engine.Action;
//import edu.monash.fit2099.engine.Actor;
//import edu.monash.fit2099.engine.GameMap;
//import edu.monash.fit2099.engine.Location;
//
//public class NinjaBehaviour implements ActionFactory {
//	
//	private Actor target;
//	
//	public NinjaBehaviour(Actor subject){
//		this.target = subject;
//	}
//
//	@Override
//	public Action getAction(Actor actor, GameMap map) {
//		
//		Location here = map.locationOf(actor);
//		Location there = map.locationOf(target);
//		
//		int currentDistance = distance(here, there);
//		
//		
//	}
//
//}
