package game.Actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Actors.NewPlayer;

/**
 * 1. Has nothing to do with Avengers.
 * 2. The feature of this class is to end the game in two ways, either user quits the game, or the user wins the game.
 *
 */
public class EndGame extends Action {

	private NewPlayer player;
	private int status; 
	private String description;
	
	/**
	 * The constructor of EndGame action.
	 * @param player The reference of the current player
	 * @param status 0 for user quits the game, 1 for user won the game.
	 */
	public EndGame(NewPlayer player, int status) {
		this.player = player;
		this.status = status;
		if (status == 0)
			this.description = "Quit Game";
		else if (status == 1)
			this.description = "Win The Game";
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		String message = "";
		if (this.status == 0) {
			this.player.quit();
			message = "Player quits the game";
		}
		
		else if (this.status == 1) {
			this.player.won();
			message = "Player wins the game";
		}
			
		return message;
	}

	@Override
	public String menuDescription(Actor actor) {
		return this.description;
	}

	@Override
	public String hotKey() {
		return "";
	}

}
