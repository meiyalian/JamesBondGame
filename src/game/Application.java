package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Player;
import edu.monash.fit2099.engine.World;

public class Application {
//testing
	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Floor(), new Wall());
		GameMap gameMap;

		List<String> map = Arrays.asList(
				".......................",
				"....#####....######....",
				"....#...#....#....#....",
				"....#........#....#....",
				"....#####....##.###....",
				".......................",
				".......................",
				".......................",
				".......................",
				".......................",
				".......................");
		gameMap = new GameMap(groundFactory, map);
		world.addMap(gameMap);
		
		NewActor player = new NewPlayer("Player", '@', 1, 100);
		world.addPlayer(player, gameMap, 2, 2);
		
		Grunt grunt = new Grunt("Mongo", player);
		gameMap.addActor(grunt, 0, 0);
		Grunt grunt2 = new Grunt("Norbert", player);
		gameMap.addActor(grunt2,  10, 10);
		
		Goon goon = new Goon("goon", player);
		gameMap.addActor(goon,  8, 10);
		
		Ninja n = new Ninja("ninja",player);
		gameMap.addActor(n,  2, 4);
		
		Q q = new Q("q",(NewPlayer)player);
		gameMap.addActor(q,3,0);
		
			
		world.run();
		
	}
}
