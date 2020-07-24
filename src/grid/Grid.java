package grid;

import java.util.ArrayList;

import gridobjects.Animal;
import gridobjects.Canary;
import gridobjects.Cat;
import gridobjects.GridObject;
import gridobjects.Seed;

// Concrete class for the grid
// Uses a 2D square grid
public class Grid {
    private int size; 								// Size of the dimensions for the square grid
    private ArrayList<Location> locations;
    private ArrayList<GridObject> gridObjects;
    private ArrayList<GridObject> deletedObjects;	// Grid objects that have been removed
    private ArrayList<Animal> winners; 				// ArrayList is used as there can be more than one winner
    
    public Grid(int size) {
       this.size = size;
       locations = new ArrayList(size*size);
       gridObjects = new ArrayList();
       deletedObjects = new ArrayList();
       winners = new ArrayList();
       
       // Creates the grid by assigning locations to the locations ArrayList dependent on its size
       for (int i = 0; i < size; i++){
           for (int j = 0; j < size; j++){
               locations.add(new Location(i,j));
           }
        }
   
    }
    
    // Adds the grid object to the grid and sets the appropriate location
    public void addAvatar(GridObject gridObject, int x, int y) {
        gridObjects.add(gridObject);
        
        // Converts the coordinates to an index for the locations ArrayList
        int index = (size * x) + y;
        
        // Sets the grid object for the location and the location for the grid object
        locations.get(index).setGridObject(gridObject);
        gridObject.setLocation(locations.get(index));
    }
    
    // Starts the game with a specified number of rounds
    public void start(int rounds) {     
        for (int i = 1; i <= rounds; i++){
            System.out.println("*** Round " + i + " ***");
            
            // For each round, it loops through the gridObjects ArrayList
            for (GridObject gridObject: gridObjects){
            	// Prints the location if it's a seed
                if (gridObject instanceof Seed){ 
                    gridObject.printLocation();
                }
                // Moves if it's an animal
                else if (gridObject instanceof Animal){
                    ifAnimal(gridObject);
                }
            }
            
            // Deletes grid objects from the grid that are out of energy or are have been eaten
            deleteObjectsFromGrid();
            System.out.println();
        }
        
        findWinners();
        
        // One winner
        if (winners.size() == 1) {
            System.out.println("The winner with " + winners.get(0).getEnergy() + " energy is " + winners.get(0).getName() + "!!!");
        }
        // More than one winner
        else if (winners.size() > 1) {
            System.out.println("The winners with " + winners.get(0).getEnergy() + " energy are:");
            
            for(Animal winner: winners) {
                System.out.println(winner.getName());
            }
        }
        // No winners
        else {
            System.out.println("There are no winners!!!");
        }
        
        System.out.println("********* GAME OVER *********");
    }
    
    // Moves the animals and determines each of the circumstances for them when they move to a new location
    public void ifAnimal(GridObject gridObject) {
        Animal animal = (Animal) gridObject;
        Location currLocation = gridObject.getLocation();
        Location destination = null;
         
        // Gets a new random location that isn't null or the same as the current location
        while (destination == null || currLocation == destination) {
            destination = getRandomLocation();
        }
        
        // Converts the coordinates to indexes for the locations ArrayList
        int currIndex = (size*currLocation.x) + currLocation.y;
        int destIndex = (size*destination.x) + destination.y;
                  
        // Checks to see if the grid object can move to the destination i.e. has enough energy
        if(gridObject.move(destination)) { 
        	// Then checks if it's occupied
            if (destination.isOccupied()) {
                GridObject destGridObject = destination.getGridObject();
                
                // Canary eats a seed
                if (animal instanceof Canary && destGridObject instanceof Seed) {
                    Seed seed = (Seed) destGridObject;
                            
                    animal.eat(seed);
                    deletedObjects.add(destGridObject);
                }
                // Cat eats a canary
                else if (animal instanceof Cat && destGridObject instanceof Canary) {
                    Canary canary = (Canary) destGridObject;
                                
                    animal.eat(canary);
                    deletedObjects.add(destGridObject);
                }
                // Cat tries to eat the seed but fails and moves again
                else if (animal instanceof Cat && destGridObject instanceof Seed) { 
                    Seed seed = (Seed) destGridObject;
                    
                    animal.eat(seed);
                    ifAnimal(gridObject);
                }
                // Canaries don't eat other canaries, therefore they move again
                else if (animal instanceof Canary && destGridObject instanceof Canary) {
                    System.out.println("There is already a canary there. I better move.");
                    ifAnimal(gridObject);
                }
                // Canaries don't eat cats, therefore they move again
                else if (animal instanceof Canary && destGridObject instanceof Cat) {
                    System.out.println("That's a cat. I definitely need to move.");
                    ifAnimal(gridObject);
                }
            }
            
            // Updates the animals' locations on the grid
            locations.get(destIndex).setGridObject(gridObject);
            locations.get(currIndex).setGridObject(null);
        }
        else {
        	// If the grid object can't move, it adds it to the deletedObject ArrayList to be removed
            deletedObjects.add(gridObject);
        }
    }
    
    // Deletes grid objects from the grid that are out of energy or are have been eaten
    // I used this method because I was having problems removing grid objects from the gridObjects ArrayList in the ifAnimal() method
    public void deleteObjectsFromGrid() {
        for (GridObject deletedObject: deletedObjects){
            if (deletedObject instanceof Seed) {
                Seed seed = (Seed) deletedObject;
                System.out.println(seed.getName() + " has been removed from the Grid.");
            }
            else if (deletedObject instanceof Animal) {
                Animal animal = (Animal) deletedObject;
                System.out.println(animal.getName() + " has been removed from the Grid.");
            }
            
            gridObjects.remove(deletedObject);
        }
        
        // Clears the ArrayList to be used on this method's next call
        deletedObjects.clear();
    }
    
    // Returns a random location on the grid
    public Location getRandomLocation( ){
        Location randomLocation = locations.get((int)(Math.random() * (size*size)));
        
        return randomLocation;
    }
    
    // Finds the winners of the game
    public void findWinners() {
        int max = 0; 									// Max energy
        ArrayList<Animal> animals = new ArrayList();	// ArrayList to hold the grid objects that are converted to Animal objects
        
        // Adds all the animals on the grid to the animals ArrayList
        for (GridObject gridObject: gridObjects) {
            if (gridObject instanceof Animal) {
                Animal animal = (Animal) gridObject;
                animals.add(animal);
            }
        }
        
        // Finds the maximum energy value in the ArrayList
        for (Animal animal: animals) {
            if (animal.getEnergy() > max) {
                max = animal.getEnergy();
            }   
        }
         
        // Adds the Animal object with the max energy to the winners ArrayList 
        // This loop is to check if there more than one winner
        for (Animal animal: animals) {
            if (animal.getEnergy() == max) {
                winners.add(animal);
            }   
        }
    }
}
