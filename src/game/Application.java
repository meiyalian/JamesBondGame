package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.demo.Crater;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.MoveActorAction;
import edu.monash.fit2099.engine.Player;
import edu.monash.fit2099.engine.World;
/**
 * 
 * The main driver class for the entire project, entry point of the game.
 *
 */
public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());
		FancyGroundFactory groundFactory = new FancyGroundFactory(new Floor(), new Wall(), new Door(), new RocketPad(),
				 new MoonCrater());
		GameMap gameMap, moonMap, marsMap;

		// (x,y): x is horizontal, y is vertical
		
		// "R" on the left (1,3) is for testing, replace it with "." after testing.
		List<String> map = Arrays.asList(
				".........................",  // 0
				"....#####......######....",  // 1
				"....#...#......#R...#....",  // 2
				"....#...+......#....#....",  // 3
				"....#####......##+###....",  // 4
				".........................",  // 5
				".........................",  // 6
				".........................",  // 7
				".........................",  // 8
				".........................",  // 9
				"........................."); // 10
		
		
		// Always add the map to the world  before adding any actors
		gameMap = new GameMap(groundFactory, map);
		world.addMap(gameMap);
		
		
		// The map for moon
		List<String> moon = Arrays.asList(
				"oooooooooooooooooooooooooo",     // 0
				"oooooooooooooooooooooooooo",		// 1
				"oooooooooooooooooooooooooo",		// 2
				"oooooooooooooooooooooooooo",     // 3
				"oooooooooooooooooooooooooo",		// 4
				"oooooooooooooooooooooooooo",		// 5
				"oooooooooooooooooooooooooo",     // 6
				"oooooooooooooooooooooooooo",		// 7
				"oooooooooooooooooooooooooo",		// 8
				"oooooooooooooooooooooooooo",     // 9
				"oooooooooooooooooooooooooo",		// 10
				"oooooooooooooooooooooooooo");    // 11
		
		moonMap = new GameMap(groundFactory, moon);
		world.addMap(moonMap);
		
		
		// The map for mars
		List<String> mars = Arrays.asList(
				"....................",     // 0
				"....................",		// 1
				"....................",		// 2
				"....................",		// 3
				".....#####+#####....",		// 4
				".....#.........#....",		// 5
				".....#.........#....",		// 6
				".....#.........#....",		// 7
				".....#.........#....",		// 8
				".....###########....");	// 9
		
		marsMap = new GameMap(groundFactory, mars);
		//world.addMap(marsMap);
		
		
		
		
		
		NewActor player = new NewPlayer("Player", '@', 1, 50,gameMap);

	
		gameMap.at(2,1).setGround(new OxygenDispenser(gameMap,2,1,player));
		
		gameMap.at(0,3).setGround(new FirstAidPoint(gameMap,0,3));
		
		world.addPlayer(player, gameMap, 2, 2);
		
		spaceSuit sp = new spaceSuit();
		sp.addSkill(SkillList.SPACETRAVEL);
		gameMap.addItem(sp,2,0);
		
		
		Grunt grunt = new Grunt("Mongo", player);
		gameMap.addActor(grunt, 5, 0);
		
		Grunt grunt2 = new Grunt("Norbert", player);
		gameMap.addActor(grunt2, 10, 10);
		
		Goon goon = new Goon("Goon", player);
		gameMap.addActor(goon, 8, 10);
		
		Ninja n = new Ninja("Ninja", player);
		gameMap.addActor(n, 2, 4);
		
		Q q = new Q("Q", (NewPlayer)player);
		gameMap.addActor(q, 10, 8);
		
		DoctorMaybe doctorMaybe = new DoctorMaybe("Doc", player);
		gameMap.addActor(doctorMaybe, 18, 2);
		
		RocketPlan rp = new RocketPlan();
		gameMap.addItem(rp, 6, 2);
		
		Medicine m = new Medicine(10,1,1,false);
		gameMap.addItem(m, 1,1);
		
		
//		Item portalEarth = Item.newFurniture("Portal", 'R');
//		portalEarth.getAllowableActions().add(new MoveActorAction(moonMap.at(10, 1), "to Moon"));
//		gameMap.at(16, 2).addItem(portalEarth);
		
		RocketPad earthActRp = new RocketPad();
		earthActRp.addBody();
		earthActRp.addEngine();
		gameMap.at(3, 2).setGround(earthActRp);
		
		Item portalEarth = Item.newFurniture("Portal", 'R');
		portalEarth.getAllowableActions().add(new MoveActorAction(moonMap.at(10, 1), "to Moon"));
		gameMap.at(3, 2).addItem(portalEarth);
		
		
		
		
		//-------------------------------------------------------------------------------

		RocketPad moonActRp = new RocketPad();
		moonActRp.addBody();
		moonActRp.addEngine();
		moonMap.at(10, 1).setGround(moonActRp);
		
		
//		Item portalMoon = Item.newFurniture("Portal", 'R');
//		portalMoon.getAllowableActions().add(new MoveActorAction(gameMap.at(16, 2), "to Earth"));
//		moonMap.at(10, 1).addItem(portalMoon);
		
		Item portalMoon = Item.newFurniture("Portal", 'R');
		portalMoon.getAllowableActions().add(new MoveActorAction(gameMap.at(3, 2), "to Earth"));
		moonMap.at(10, 1).addItem(portalMoon);
		
		//----------------------------------------------------------------------------------
		
//		RocketPad actRp = new RocketPad();
//		actRp.addBody();
//		actRp.addEngine();
//		marsMap.at(10, 1).setGround(actRp);
//		
//		Item portalMars = Item.newFurniture("Portal", 'R');
//		portalMars.getAllowableActions().add(new MoveActorAction(gameMap.at(16, 2), "to Earth"));
//		marsMap.at(10, 1).addItem(portalMars);
//		
//		
//		MagicPill mp = new MagicPill(20);
//		marsMap.addItem(mp, 10, 7);
//		
//		Soldier[] soldiers = new Soldier[6];
//		soldiers[0] = new Soldier("Soldier1", player);
//		soldiers[1] = new Soldier("Soldier2", player);
//		soldiers[2] = new Soldier("Soldier3", player);
//		soldiers[3] = new Soldier("Soldier4", player);
//		soldiers[4] = new Soldier("Soldier5", player);
//		soldiers[5] = new Soldier("Soldier6", player);
//		
//		int[][] sodLocs = {{9,3}, {11,3}, {9,7}, {11,7}, {10,6}, {10,8}};
//
//		for (int i = 0; i < 6; i++)
//			marsMap.addActor(soldiers[i], sodLocs[i][0], sodLocs[i][1]);
//		
//		
//		SuperSoldier[] spS = new SuperSoldier[4];
//		spS[0] = new SuperSoldier("SuperSoldier1", player);
//		spS[1] = new SuperSoldier("SuperSoldier2", player);
//		spS[2] = new SuperSoldier("SuperSoldier3", player);
//		spS[3] = new SuperSoldier("SuperSoldier4", player);
//		
//		int[][] spSLocs = {{6,5}, {6,6}, {14,5}, {14,6}};
//		
//		
//		Trap trap1 = new Trap(marsMap);
//		for (Actor actor : soldiers)
//			trap1.removeActor(actor);
//		for (int i = 0; i < 4; i++)
//			trap1.spawnActor(spS[i], spSLocs[i]);
//
//		
//		marsMap.at(10, 7).addItem(trap1);
//		
		
		// Put any modifications of maps/actors/items above this code.
		world.run();
		
	}
}
