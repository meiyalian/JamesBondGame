# **FIT2099 Assignment Design Rationale**
##### This document explains the design choices that were made to implement the required funtionality to the game, as well as the pros and cons for each of the design choices.

## **Map**
##### The layouts of maps in this game will be hardcoded as auto generated maps might not be guaranteed to always have required features such as a locked room, which needs to be implemented via a combination of `Locations`.

## **Interfaces**

### Throwable
***
##### Design
- Creates an interface `Throwable` to identify if an item or weapon can be thrown.
- Contains two abstract methods:
- isHit( ): a method to control the possibility of hitting a target. 
- affect( ) : the effect caused by the throwable object when it hits a character. 
##### Pros
- Flexibility and no dependencies, as any class can add/remove this feature.
##### Cons
- Redundant implementations on subclasses of `Item`, `Weapon`, etc. As classes in the engine cannot be modified.
- Not rigorous, `Actor` entites can also implement this feature to determine if they are throwable or not.


## **Enums**
### SkillList (not yet implemented, might be implemented later)
***
##### Design
- An enum named `SkillList` used to store all of the skills in the game.
##### List of all skills
- `Shouting`: The skilled entity will shout an insult at its target.
##### Pros
- Centralised. Instead of having skills in different sections of the code, all skills in the game is in this enum, so programmers only have to look for one place for different skills.


## **Actions**
### Throw
***
##### Design
- Create a new `Throw` class that extends `Action`.
- The action requires three parameters for entities, an `Actor`(executor), who throws a `Throwable` (entity) towards another `Actor` (target).
- The action also requires two position parameters, the `Location` of the executor and the target.
- The `Throw` action will consider the obstacles between the executor and the target on the map, that is, if the thrown object can be reached to its destination.
##### Pros
- Extensiveness. As an `Action`, this feature is not limited to specific `Actors`, any `Actor` who is allowed for this action can perform it.
- Preciseness. Using strong typing, only `Throwable` entities are allowed to be used during this action.
##### Cons
- Might have issues on adaptability, as this action only supports `Actor` to `Actor` interactions.

### Give
***
##### Design
- Create a `GiveAction` class that extends `Action`.
- An `Actor` (giver) can give one `Item` from its inventory to another `Actor` (taker).
- This feature is implemented by adding the `Item` to the taker's inventory, and removing the `Item` from the giver's inventory.
##### Pros
- Flexibility, any `Actor` can give items to any `Actor`.

### Give And Disappear
***
##### Design
- Create a `GiveAndDisappearAction` class that extends `Action`.
- An `Actor` can give one `Item` to another `Actor` (using the `Give` action), then (the giver) disappears from the map.
- The giver can also choose a custom dialogue when they disappear.
##### Pros
- This action was creating with the intention of implementing Q's unique features, but can also be used by other `Actors` if needed.
##### Cons
- Redundancy. Arbitrary to combined two `Actions` into one.

### GetHeal (Bonus)
***
##### Design
- Create a `GetHeal` class that extends `Action` that heals `Actors`.
- The action can be triggered when the `Actor` having a `Medicine` item.
- Has an attribute to store the amount of hit points to be healed if `Actor` executes this action.
- If the attribute is negative, it will be adjusted to 0 to prevent damaging the `Actor`.
- Heals the `Actor` that executes this action by the stored hit points.
- Removes the `Medicine` from `Actor`'s inventory after healing.
##### Pros
- Must have an `Item` in order to heal the `Actor`, preventing the abuse of this `Action` in the game.

### ModifyDamage (Bonus)
***
##### Design
- Create a `ModifyDamage` class that extends `Action`.
- Can change the damage of an `Actor` towards others (Both increasing and decreasing).
##### Pros
- Uses downcasting to modify `Actors` damage via their own object's method, hence to prevent logical error.
##### Cons
- The action does not required `Items` or whatsoever to be executed, so it's possible to abuse (repeat) this action in game to modify `Actors`' damage.

### Talk
***
##### Design
- Create a `TalkAction` that extends `Action`.
- An `Actor` can speak to another `Actor` with a custom dialogue(String).
- The dialogue will be printed for the user to see.
##### Pros
- The dialogue can be choosen by the speaker, instead of speaking static words.
##### Cons
- Possible redundant functionality with the `Shouting` class, will be fixed in the next assignment.

### Lock/Unlock
***
##### Design
- Create a `Unlock` class that extends `Action`.
- Allows an `Actor` to lock or unlock a `Door` using a `Key` in their inventory.
##### Pros
- Uses an `Action` class so the `Actor` can choose whether to lock/unlock the `Door` or not.
##### Cons
- As a `Door`'s `Location` cannot be entered if the `Door` is locked, some edge cases can cause logical errors (e.g. An `Actor` is at the `Location` of an unlocked `Door`, but another `Actor` locks that `Door` instead).


## **Behaviours**
### Wander Behaviour
***
##### Design
- Create a `WanderingBehaviour` class that implements `ActionFactory`.
- If an `Actor` has this action, for each turn the `Actor` will have equal chances to move to one of its adjoining `Locations` (Provide the `Actor` can enter it), or to not move at all. (50% chance to not move, or move to up/down/left/right)
- The action stops if the `Actor` is no longer conscious.
- The action can be terminated if wish.
##### Pros
- Reused code. Uses `MoveActorAction` to relocate the `Actor` while `Wander` is still in control of the decision makings.
##### Cons
- Unpredictable, the `Actor` might be hard to approach as it moves around the map randomly.


## **Grounds**
### Door
***
##### Design
- Create a new class named `Door` that extends from `Ground`.
- Has a boolean attribute to determine whether the `Door` is locked. 
- Its display symbol changes based on whether it's locked or not.
- Has similar features of a `Wall` when it's locked.
- Has similar features of a `Floor` when it's unlocked.
- Can be locked/unlocked by `Actors` near the location, if they have a `Key` in their inventory.
- If `Actor` has more than 1 `Key` in their inventory, allowable actions to lock/unlock the `Door`.
##### Pros
- Has the capacity to switch between locked and unlocked.
- Can be extended to make unique `Doors` in the future design.
##### Cons
- Currently only has one type of `Door`, which is universal and not distinguishable.
- Has dependency on `Keys`, if no `Key` exists in the game, `Doors`' status cannot be changed.

### The Rocket Pad
***
##### Design
- Create a `RocketPad` class that extends `Ground`.
- If `Player` is near the `RocketPad`, they'll have the option to `Give` `RocketBody` or `RocketEngine` to the `RocketPad`(temporary `Actor` to accept the items).
- The `RocketPad` cannot be accessed until it has both `RocketPad` and `RocketBody`.
- If the `RocketPad` has both `Item` mentioned above, `Actors` can go to the `RocketPad` and travel to a differnt `Map`.
##### Pros
- Inherited an existing class to prevent code redundancy.
- Extensiveness. Because this is a special `Location`, creating a new subclass will allow much more modifications in the future if needed.
##### Cons
- Dependency on `Actors` and `Items` in order to achieve the functionality of receiving items and teleport actors.



## **Items**
### Rocket Plans
##### Design
- Create a `RocketPlans` class that extends `Item`.
- `RocketPlan` can be found in a room with a locked `Door`.

### Rocket Body
##### Design
- Create a `RocketBody` class that extends `Item`.
- `RocketBody` can be obtained from `Q` (NPC) via a quest.

### Rocket Engine
##### Design
- Create a `RocketEngine` class that extends `Item`.
- Dropped by `DoctorMaybe` upon its defeat.

### Benefits of Rocket (Plans, Body, Engine)
- Independency. Each type of the item has its own class, so they are not dependent nor conflicting with each other.
- Extensiveness. Individual classes for item allows each of them to be modified on its own.
- Since they are all independent `Items`, they are not unique and one game could contain multiple numbers of these items.

### Disadvantages of Rocket (Plans, Body, Engine)
- Individual classes means there needs to be extra logic (code) to relate these items, which increases the complexity of the code.

### Key
***
##### Design
- Create a new class named `Key` that extends from `Item`.
- `Keys` are added to some enemies' inventory when they were created.
- A `Key` can be used to lock/unlock a `Door`.
- A `Key` item is removed(used) from the `Actor`' inventory after locking/unlocking a `Door`.
##### Pros
- `Keys` will automatically drop upon the defeat of some enemies, as it's in their inventory.
- As an `Item`, `Key` objects can also be generated on different `Locations`.
- Multiple `Keys` can be owned by an `Actor` and has no conflicts with locking/unlocking a `Door`.
##### Cons
- Adds complexity to the logic of the game.

### Stun Powder
***
##### Design
- Create a new class `StunPowder` that extends `WeaponItem` and implements `Throwable`.
- Can be used as a object during the `Throw` action.
- The target is stunned (skip 2 turns) if the stun powder hits them, and has no effect if the target is already stunned.
- Has 50% of chance to hit the target.
##### Pros
- Flexibility. As a `WeaponItem` it can be used by any `Actor` that owns this item and is allowed to `Throw`.
##### Cons
- If this item will only be possessed by very few `Actors`, then this class unnecessarily increased the complexity of the game. 

### MagicPill (Bonus)
***
##### Design
- Creates a `MagicPill` class that extends `Item`.
- Can `ModifyDamage` of an `Actor`. (Increase of decrease of their damage)
- Cannot be picked up by default (intended to be used as an furniture `Item`).
##### Pros
- Uses an `ModifyDamage` action to execute the modification, instead of making this the only class that can modify `Actor`'s damage.
- Can do both increase and decrease for `Actor`'s damage.
##### Cons
- Not allowed to be picked up by `Actors`, so the item must be used at the location.

### Medicine (Bonus)
***
##### Design
- Creates a `Medicine` class that extends `Item`.
- Can `Heal` an `Actor` with specific amount of hit points.
##### Pros
- Use the representation of an `Item` to `Heal` `Actors`, so they can choose when to execute it, instead of executing is immediately.
- Uses `GetHeal` action to execute the healing, so if other class also wants to perform healing, the code need not to be repeated.
##### Cons
- Can enter negative values for healing points. However, these cases will be catched and fixed by `GetHeal` and/or `NewActor`.

### Trap (Bonus)
***
##### Design
- Creates a `Trap` class that extends `Item`.
- Cannot be picked up.
- Will be triggered if an `Actor` enter the `Location` that this `Item` is in.
- Theoretically can perform anything to the current map its in.
- Including but not limited to: Spawning `Actors` to this map's specific `Locations`, Removing specific `Actors` from the map, etc.
##### Pros
- Huge flexibility. Can use this to modify many features in the game in one turn.
##### Cons
- Issues with encapsulation and data hiding, since it has the reference of the current map. However, the issue can be limited since `Traps` are only allows to be generated in the `Main Driver Class` of this game, so it's static and programmed before launching the game. No `Traps` are allowed to be created once the game has started.

## **Actors**
### NewActor
***
##### Design
- An abstract class extends from the `Actor` class in the engine code. 
- It is used by all the `Actors` in the game. 
- By default do not `PickUp` or `Drop` `Items`.
- Automatically attacks `Actors` near them (Need to be controlled in subclasses in needed to).
- Compare to the `Actor` class, it has a Boolean attribute `isStunned` which records if the `Actor` is stunned or not in order to implement the affect caused by throwing a `StunPowder` to an `Actor` (the `Actor` will skip 2 turns). 
- Only heals the `Actor` if healing point is positive.
- Only damage the `Actor` if damage point is positive.
- Can also change the `Actor`'s damage to other `Actors`.
- Prevents an `Actor`'s damage to others become less than 0.

### NewPlayer
***
##### Design
- Class extends from `NewActor`, uses to replace the `Player` class in the engine .
- Compare to the `Player` class, this class, in each turn, it will check if the `Player` is stunned or not. If the `Player` is stunned, the playturn function will return `SkipTurnAction` for 2 turns. 
##### Cons
- Since the class is extended from the `NewActor` class, it cannot extends from the `Player` class in the engine due to the conflict of two subclasses. Hence, in the playturn function, a part of code in the engine `Player` class is copy-pasted to this class. 

### Grunt
***
##### Design
- Modify the `Grunt` class, extends `NewActor` and override its `IntrinsicWeapon` to "slap".
- Add `FollowingBehaviour` to follow the player.
- Contains a `Key` in their inventory.
##### Pros
- Adding features to an existing class to prevent redundancy in code.
##### Cons
- Original `IntrinsicWeapon` for `Grunt` has been modified, possible to cause chain effect of logical errors.

### Goon
***
##### Design
- Create `Goon` class that extends from `NewActor`.
- Add `FollowingBehaviour` to follow the player.
- Its `IntrinsicWeapon` does twice as damage as `Grunts`.
- Has `Shouting` skill.
- For each turn, has 10% of chance to use `Shouting` on `Player`.
- Contains a `Key` in their inventory.
##### Pros
- Avoid redundancy. Creates a new class but all its features are implemented via existing functionalities.
##### Cons
- Dependency issues. If the features it uses from other classes has been changed, then the functionalities of `Goons` will also be affected.

### Ninja
***
##### Design
- Create a `Ninja` class that extends `NewActor`.
- Stays in one `Location` in the `GameMap` unless `Player` is within 5 squares of them. (Checking the distance between `Player` and itself for each turn)
- `Throws` `StunPowder` towards the `Player` if they are 5 squares within.
- Move away from the `Player` by one square after throwing. (By calculating the direction of the `Player`)
- Contains a `Key` in their inventory. (`StunPowders` are infinite for `Ninjas` as it's newly generated for each turn)
##### Pros
- Avoids redundancy. Many features is implemented via existing functionalities.

### Q (NPC)
***
##### Design
- Create a class `Q` that extends `NewActor`.
- Does not attack other `Actors`.
- Has a `RocketBody` in his inventory.
- Has `WanderingBehaviour`. (Wandering randomly on the map)
- The `Player` can `Talk` to `Q` if the `Player` is near `Q`.
- `Q` will respond differently (`TalkAction`) based on whether the `Player` has `RocketPlan` in its inventory.
- If the `Player` `Give` `Q` the `RocketPlan`, `Q` will `Give` `Player` a `RocketBody`, then disappears from the map.
##### Pros
- Consistency to logic. Uses existing features to give `Item` to the `Player` from its inventory, instead of spawning new items.
- Use of other classes. Actions such as talk are individual `Actions`, hence `Q` is not the only `Actor` that can perform these `Actions`.
##### Cons
- Dependency on `WanderingBehaviour`, `TalkAction`, `GiveAndDisappearAction`.

### Doctor Maybe
***
##### Design
- Create a new class named `DoctorMaybe` that extends from `NewActor`.
- `DoctorMaybe` will only appear in a locked room on the map.
- Has a `Key` and a `RocketEngine` in his inventory.
- Does not move at all. (Passive unless the `Actors` are near `DoctorMaybe`)
- Has half the hit points and does half the damage of `Grunts`.
##### Pros
- Simplicity. The logic for this class are short and simple.

### Soldiers (Bonus)
***
##### Design
- Create a `Soldier` class that extends `NewActor`.
- Soldiers has 20 hit points and does 5 damage.
- Soldiers does not move around the map.
- Soldiers attacks `Actors` nearby.

### Super Soldiers (Bonus)
***
##### Design
- Create a `SuperSoldier` class that extends `Soldier`.
- Super soldiers has 50 hit points and does 15 damage.
- Super soldiers has `FollowingBehaviour` that follows the `Player`.
- Super soldiers has a `Key` in their inventory.
##### Pros
- Inheritance. Prevents repeating code from `Soldier` class.