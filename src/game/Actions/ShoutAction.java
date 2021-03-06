package game.Actions;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import java.util.Random;


/**
 *  An action for shouting an insult at other Actors.
 */
public class ShoutAction extends Action{
	
	private Actor shouter;
	private Actor receiver;
	private ArrayList<String> wordsToShout; // the list of insulting words (currently the list is fixed and cannot be changed) 
	private Random rand = new Random();
	
	
	/**
	 * Constructor for ShoutAction.
	 * @param actor: The shouter.
	 * @param subject: The target to be shouted at.
	 */
 	public ShoutAction(Actor actor, Actor subject ) {
		this.shouter = actor;
		this.receiver = subject;
		wordsToShout = new ArrayList<String>();
		wordsToShout.add("stupid");
		wordsToShout.add("weak");
		wordsToShout.add("silly");
		wordsToShout.add("You can never catch me");

				
	}
	@Override
	public String execute(edu.monash.fit2099.engine.Actor actor, GameMap map) {
	
		String word = wordsToShout.get(rand.nextInt( wordsToShout.size()) );
		return this.menuDescription(actor) + ": " + word+ "!";
	
	}
	@Override
	public String menuDescription(edu.monash.fit2099.engine.Actor actor) {
		
		return actor.toString() + " shouts to " + this.receiver.toString();
	}
	@Override
	public String hotKey() {return "";	}
	
	

}
