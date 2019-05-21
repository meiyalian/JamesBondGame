package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;
import edu.monash.fit2099.engine.SkipTurnAction;


/**
 * Class representing the Player. Compare to the player class in the engine code, add features 
 * of skipping 2 turns when the a stun powder is thrown to the player. (has no effect if the player is 
 * already stunned) 
 *  
 */

public class NewPlayer extends NewActor{
	
	
	private int stunTurnCounter = 0; // counters that record how many turns has the player been stunned
	private GameMap earthMap;
	
	

	/**
	 * Constructor.
	 *
	 * @param name Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param priority How early in the turn the player can act
	 * @param hitPoints Player's starting number of hitpoints
	 */

	public NewPlayer(String name, char displayChar, int priority, int hitPoints, GameMap homeMap) {
		super(name, displayChar, priority, hitPoints);
		earthMap = homeMap;
	}
	
	
	/**
	 * Play a turn. Doing this means displaying a menu to the user and getting their selected option.
	 * When the user is stunned, the user will skip 2 turns and a message will be printed out 
	 *
	 * Ignores more than 26 options. We could do better. We could also roll out a
	 * dedicated menu class instead of having it here. Player is 90% menu.
	 *
	 * @param actions the actions to display
	 * @param map the map to display
	 * @param display the object that performs the console I/O
	 * @return an action
	 */
	
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		
		
		if (map.locationOf(this).getGround() instanceof MoonCrater) {
			
		
			display.println( Integer.toString(checkOxygenPoints()-1) + " oxygen points left");
			

			for (Item item: this.getInventory() ) {
				if (item instanceof OxygenTank) {
					if (((OxygenTank) item).getPoints() ==0) {
						this.removeItemFromInventory(item);
					}

					if (((OxygenTank) item).getPoints() >0) {
						((OxygenTank) item).usedOxygen();
						break;
			}
					}
				}
			
			
			int oxygenPoint = this.checkOxygenPoints();
		
			if(oxygenPoint<= 0) {
				
				display.println( "No oxygen left. Going back to Earth. ");
				return new MoveActorAction(earthMap.at(3, 2), "to Earth ");
			}
			
			else {
				return showMenu(actions, display);
			}
			
			}
		
		
		if (! isStunned){
		return showMenu(actions, display);
		
		}
		else {
			stunTurnCounter ++;
			display.println(this.name + " has been stunned, cannot do anything in this turn.");

			if (stunTurnCounter ==2) {
				stunTurnCounter = 0;
				isStunned = false;
			}
			
			return new SkipTurnAction();
		}
	}

	/**
	 * Display a menu to the user and have them select an option. 
	 *
	 * @param actions the Actions that the user can choose from
	 * @param display the I/O object that will display the map
	 * @return the Action selected by the user
	 */
	protected Action showMenu(Actions actions, Display display) {
	
		ArrayList<Character> freeChars = new ArrayList<Character>();
		HashMap<Character, Action> keyToActionMap = new HashMap<Character, Action>();
		
	
		for (char i = 'a'; i <= 'z'; i++)
			freeChars.add(i);

		for (Action action : actions) {
			String hotKey = action.hotKey();
			if (hotKey != "") {
				if (freeChars.isEmpty())
					break;
				char c = hotKey.charAt(0);
				freeChars.remove(Character.valueOf(c));
				keyToActionMap.put(c, action);
				display.println(hotKey + ": " + action.menuDescription(this));
			}
		}
		

		for (Action action : actions) {
			if (action.hotKey() == "") {
				if (freeChars.isEmpty())
					break;
				char c = freeChars.get(0);
				freeChars.remove(0);
				keyToActionMap.put(c, action);
				display.println(c + ": " + action.menuDescription(this));
			}
		}

		char key;
		do {
			key = display.readChar();
		} while (!keyToActionMap.containsKey(key));
		
		
		
		
		return keyToActionMap.get(key);
	}

	
	
	
	public int checkOxygenPoints() {
		
		int point = 0;
		for (Item i : this.getInventory() ) {
			if(i instanceof OxygenTank) {
				point += ((OxygenTank) i).getPoints();
			}
		}
		return point;
	}
	
	

	
}




