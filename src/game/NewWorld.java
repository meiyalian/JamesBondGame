package game;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;
import game.Actors.NewPlayer;

public class NewWorld extends World {

	public NewWorld(Display display) {
		super(display);
	}

	
	/**
	 * Run the game.
	 *
	 * On each iteration the gameloop does the following:
	 *  - displays the player's map
	 *  - processes the actions of every Actor in the game, regardless of map
	 *
	 * We could either only process the actors on the current map, which would make
	 * time stop on the other maps, or we could process all the actors.  We chose to
	 * process all the actors.
	 *
	 * @throws IllegalStateException if the player doesn't exist
	 */
	public void run() {
		if(player == null)
			throw new IllegalStateException();
		
		while (stillRunning()) {
			NewPlayer p = (NewPlayer)this.player;
			if (p.isQuitting() || p.hasWon())
				break;
			
			GameMap playersMap = actorLocations.locationOf(player).map();
			playersMap.draw(display);
			for (Actor actor : actorLocations) {
				processActorTurn(actor);
			}
		}
		display.println(endGameMessage());
	}
	
	

	/**
	 * Return a string that can be displayed when the game ends.
	 *
	 * @return the string "Game Over"
	 */
	protected String endGameMessage() {
		
		if (((NewPlayer)player).isQuitting())
			return "Goodbye";
		else if (((NewPlayer)player).hasWon())
			return "Congratulations! You have won the game!";
		else
			return "Game Over";
	}
}
