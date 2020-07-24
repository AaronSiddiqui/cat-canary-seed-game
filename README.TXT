# Cat, Canary & Seed Game

This is a simple score-based game involving cats, canaries and seeds on a 2-dimensional grid. One cat and a selection of canaries and seeds are randomly distributed on the board. Then the cats and canaries move to a new space over a number of rounds, decided by the user.

## Rules

The rules of the game are as follows:
- If the location is unoccupied, the incoming object can move to the location. It loses some energy in the move.
- If the location is already occupied, the incoming object (a Cat or a Canary)  will try to eat the occupying object at  the location. 
- If the incoming object succeeds, it occupies the location (and absorbs the energy of the occupying object). If it fails, it must go to another empty location and it must lose energy due to its move.
- To make this a fair fight, a Cat should lose a lot of energy it fails to eat.

The winner is the cat or canary with the highest energy when the rounds are finished.
