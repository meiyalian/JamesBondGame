# **FIT2099 Assignment Design Rationale**
This document explains the design choices that were made to implement the required funtionality to the game, as well as the pros and cons for each of the design.
## Door
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
- Can switch status between locked and unlocked.
- Can be extended to make unique `Doors` in the future design.

##### Cons
- A pair of `Door` and `Key` are dependent on each other if the `Door` is to be opened, but can be prone to errors if no such type of key exists in the game.
- Hard for the player to identify the type of `Door` unless they are at the location.

## Key
##### Design
- Create a new class named `Key` that extends from `Item`.
- Each `Key` has an attribute to identify their type, and the types are within the same range as types for `Door` objects.
- `Keys` are added to some enemies' inventory when they were created.
- A `Key` can be used to unlock a `Door`, if their types matches each other.

##### Pros
- Different types of `Keys` allows them has distinguishable representaions, such as rarity.
- As an `Item`, `Key` objects can also be generated on different `Locations`.

##### Cons


