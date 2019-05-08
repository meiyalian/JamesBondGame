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
### SkillList
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
- Create a `Give` class that extends `Action`.
- An `Actor` (giver) can give one `Item` from its inventory to another `Actor` (taker), provide the other `Actor` is not hostile.
- This feature is implemented by adding the `Item` to the taker's inventory, and removing the `Item` from the giver's inventory.

##### Pros
- Flexibility, any `Actor` can give items to any `Actor`.

##### Cons
- Partial dependency on `Disposition` interface. If the `Player` is to give item to another `Actor`, the action could fail if the other `Actor` is hostile.


## **Behaviours**
### Wander Behaviour
***
##### Design
- Create a `WanderBehaviour` class that implements `ActionFactory`.
- If an `Actor` has this action, for each turn the `Actor` will have equal chances to move to one of its adjoining `Locations` (Provide the `Actor` can enter it), or to not move at all. (20% equally chance to not move, or move to up/down/left/right)
- The action stops if the `Actor` is no longer conscious.
- The action can be terminated if wish
##### Pros
- Reused code. Uses `MoveActorAction` to relocate the `Actor` while `Wander` is still in control of the decision makings.
##### Cons
- Prone to errors, as this action will only terminate if the `Actor` is no longer conscious.
- Unpredictable, the `Actor` might be hard to approach as it moves around the map randomly.


## **Grounds**
### Door
***
##### Design
- Create a new class named `Door` that extends from `Ground`.
- Each `Door` has an attribute to identify their type.
- Has a boolean attribute to determine whether the `Door` is locked. 
- Its display symbol changes based on whether it's locked or not.
- Has similar features of a `Wall` when it's locked.
- Has similar features of a `Floor` when it's unlocked.
- Can be unlocked by entities at the location, and also has a corresponding `Key` in their inventory.
##### Pros
- Differnt types for `Door` objects allows them to be distinguishable.
- Types of `Doors` can be used to represent the rarity of the `Door`.
- Has the capacity to switch between locked and unlocked.
- Can be extended to make unique `Doors` in the future design.
##### Cons
- A pair of `Door` and `Key` are dependent on each other if the `Door` is to be opened, but can be prone to errors if no such type of key exists in the game.
- Hard for the player to identify the type of `Door` unless they are at the location.

### The Rocket Pad
***
##### Design
- Create a `RocketPad` class that extends `Ground`.
- If `Player`'s current `Location` has a `RocketPad` ground, and the `Location` has both `RocketPad` and `RocketBody`, then remove both item mentioned above, and add a `Rocket` to the `Location`.
##### Pros
- Inherited an existing class to prevent code redundancy.
- Extensiveness. Because this is a special `Location`, creating a new subclass will allow much more modifications in the future if needed.
##### Cons
- A `Rocket` can only be made by the `Player` but not any other entities, which limits the possibility of the game framework.

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

### Rocket
##### Design
- Create a `Rocket` class that extends `Item`.
- Can be created at `RocketPad` with a `RocketBody` and a `RocketEngine`.

### Benefits of Rocket (Plans, Body, Engine)
- Independency. Each type of the item has its own class, so they are not dependent nor conflicting with each other.
- Extensiveness. Individual classes for item allows each of them to be modified on its own.

### Disadvantages of Rocket (Plans, Body, Engine)
- Since they are all independent `Items`, they are not unique and one game could contain multiple numbers of these items.
- Individual classes means there needs to be extra logic (code) to relate these items, which increases the complexity of the code.

### Key
***
##### Design
- Create a new class named `Key` that extends from `Item`.
- Each `Key` has an attribute to identify their type, and the types are within the same range as types for `Door` objects.
- `Keys` are added to some enemies' inventory when they were created.
- A `Key` can be used to unlock a `Door`, if their types matches each other.
- A `Key` item is removed(used) from the entities' inventory after unlocking a `Door`.
##### Pros
- `Keys` will automatically drop upon the defeat of some enemies, as it's in their inventory.
- Different types of `Keys` allows them has distinguishable representaions, such as rarity.
- As an `Item`, `Key` objects can also be generated on different `Locations`.
- Multiple `Keys` of the same type can exist in the game.
##### Cons
- Adds complexity to the logic of the game.
- The player cannot determine the type of a `Key` until they obtain it.
- The location to acquire a type of `Key` is not known by the player.

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
- If this item will only be possessed by very few entities, then this class unnecessarily increased the complexity of the game. 


## **Actors**

### NewActor
***
##### Design
- An abstract class extends from the `Actor` class in the engine code. 
- It is used by all the `Actors` in the game. 
- Compare to the `Actor` class, it has a Boolean attribute `isStunned` which records if the `Actor` is stunned or not in order to implement the affect caused by throwing a `StunPowder` to an `Actor` (the `Actor` will skip 2 turns). 

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
- Modify the `Grunt` class, override its `IntrinsicWeapon` to "slap".
- Implements `Disposition` and is hostile towards the `Player`.
- Contains a `Key` in their inventory.
##### Pros
- Adding features to an existing class to prevent redundancy in code.
##### Cons
- Original `IntrinsicWeapon` for `Grunt` has been modified, possible to cause chain effect of logical errors.

### Goon
***
##### Design
- Create `Goon` class that extends from `Actor`.
- Implements `Disposition` and is hostile towards the `Player`.
- Follows the `Player`.
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
- Create a `Ninja` class that extends `Actor`.
- Implements `Disposition` and is hostile towards the `Player`.
- Stays in one `Location` in the `GameMap` unless `Player` is within 5 squares of them. (Checking the distance between `Player` and itself for each turn)
- `Throws` `StunPowder` towards the `Player` if they are 5 squares within.
- Move away from the `Player` by one square after throwing. (By calculating the direction of the `Player`)
- Contains `StunPowders` and a `Key` in their inventory.
##### Pros
- Avoids redundancy. Many features is implemented via existing functionalities.
##### Cons
- Possible edge cases. `Ninjas` have limited numbers of `StunPowders` as they are items in its inventory. Also they will drop `StunPowders` upon defeat, which makes the item no longer exclusive to `Ninjas`.

### Q (NPC)
***
##### Design
- Create a class `Q` that extends `Actor` and implements `Disposition`, and sees the `Player` as friendly.
- Has a `RocketBody` in his inventory.
- Has `WanderBehaviour`. (Wandering randomly on the map)
- The `Player` can talk to `Q` if they are at the same `Location`.
- `Q` will respond differntly (print messages) based on whether the `Player` has `RocketPlan` in its inventory.
- If the `Player` `Give` `Q` the `RocketPlan`, `Q` will `Give` `Player` a `RocketBody`, then disappears from the map.
##### Pros
- Consistency to logic. Uses existing features to give `Item` to the `Player` from its inventory, instead of spawning new items.
##### Cons
- Actions such as talk are currently unique to `Q` and is hardcoded in this class, which could limit its adaptability in the future.

### Doctor Maybe
***
##### Design
- Create a new class named `DoctorMaybe` that extends from `Actor` and implements `Disposition`, and is hostile towards the `Player`.
- `DoctorMaybe` will only appear in a locked room on the map.
- Has a `Key` and a `RocketEngine` in his inventory.
- Does not move at all. (Passive unless the `Player` is at the same `Location`)
- Has half the hit points and does half the damage of `Grunts`.
##### Pros
- Only applied functionalities that are needed to implement this `Actor`, features such as movements are not included.
##### Cons
- Some attributes are dependent on other entities like `Grunt`, so if the hit points or damage for `Grunt` changes, it will also affect `DoctorMaybe`.
