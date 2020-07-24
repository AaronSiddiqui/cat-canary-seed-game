# The Cat, the Canary & the Seed

## Description

This is a simple score-based game involving cats, canaries and seeds on a 2-dimensional grid. One cat and a selection of canaries and seeds are distributed on the grid specified by the user. Then the cats and canaries move to a new space over a user specified number of rounds.

## Rules

The rules of the game are as follows:
- If the location is unoccupied, the incoming grid object can move to the location. It loses some energy for the move.
- If the location is already occupied, the incoming object (a Cat or a Canary)  will try to eat the occupying object at the location. 
- If the incoming object succeeds, it occupies the location and absorbs the energy of the occupying object. If it fails, it must move to another empty location and lose energy due to its move.

The 3 primary grid objects have the following properties:
- **Cats:** Starts with 10 energy, use 2 energy to move, and can eat canaries but not seeds.
- **Canaries:** Starts with 5 energy, use 1 energy to move, and can eat seeds but not cats.
- **Seeds:** Starts with 10 energy, doesn't move, and can't eat anything.

The winner is the cat or canary with the highest energy when the rounds have concluded.

## Usage

To run a simple test of the game using 3 seeds, 3 canaries and 1 cat, run the **test/GridTest.java** file with your favourite editor, IDE, or CLI tool (I personally ran this using an Eclipse project).

If you want to run your own scenarios, either modify the this file or create your own tests.
