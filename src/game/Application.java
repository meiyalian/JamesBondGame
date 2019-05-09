package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Player;
import edu.monash.fit2099.engine.World;

public class Application {
//testing
	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Floor(), new Wall(), new Door(), new RocketPad());
		GameMap gameMap;

		List<String> map = Arrays.asList(
				".........................",
				"....#####......######....",
				"....#...#......#....#....",
				".R..#...+......#....#....",
				"....#####......##+###....",
				".........................",
				".........................",
				".........................",
				".........................",
				".........................",
				".........................");
		
		gameMap = new GameMap(groundFactory, map);
		world.addMap(gameMap);
		
		NewActor player = new NewPlayer("Player", '@', 1, 100);
		
		RocketBody body = new RocketBody();
		body.getAllowableActions().clear();
		body.getAllowableActions().add(new DropItemAction(body));
		player.addItemToInventory(body);
		
		RocketEngine engine = new RocketEngine();
		engine.getAllowableActions().clear();
		engine.getAllowableActions().add(new DropItemAction(engine));
		player.addItemToInventory(engine);
		
		world.addPlayer(player, gameMap, 2, 2);
		
//		Grunt grunt = new Grunt("Mongo", player);
//		gameMap.addActor(grunt, 0, 0);
//		
//		Grunt grunt2 = new Grunt("Norbert", player);
//		gameMap.addActor(grunt2, 10, 10);
//		
//		Goon goon = new Goon("Goon", player);
//		gameMap.addActor(goon, 8, 10);
//		
//		Ninja n = new Ninja("Ninja", player);
//		gameMap.addActor(n, 2, 4);
//		
		Q q = new Q("Q", (NewPlayer)player);
		gameMap.addActor(q, 10, 8);
		
		DoctorMaybe doctorMaybe = new DoctorMaybe("Doc", player);
		gameMap.addActor(doctorMaybe, 18, 2);
		
		RocketPlan rp = new RocketPlan();
		gameMap.addItem(rp, 6, 2);
		
		Medicine m = new Medicine(10);
		gameMap.addItem(m, 9, 7);
		
		world.run();
		
	}
}
