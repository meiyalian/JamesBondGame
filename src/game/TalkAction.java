package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * 
 * The class for actor to Talk to each other. 
 *
 */
public class TalkAction extends Action {
	
	Actor listener, speaker;
	String dialogue;

	public TalkAction(Actor listener, Actor speaker, String dialogue) {
		this.speaker = speaker;
		this.listener = listener;
		this.dialogue = dialogue;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		return this.speaker.toString() + ": " + this.dialogue;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor.toString() + " talks to " + this.speaker.toString();
	}

	@Override
	public String hotKey() {
		return "";
	}

}
