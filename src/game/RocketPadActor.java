package game;

public class RocketPadActor extends NewActor{

	private RocketPad parent;
	
	public RocketPadActor(RocketPad callingClass) {
		super("rocketPadActorInterface", 'E', 3, 999);
		this.parent = callingClass;
	}

}
