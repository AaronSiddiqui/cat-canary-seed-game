/**
 * Write a description of class Grid here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.ArrayList;

public class Grid
{
    private int size; //size of grid
    private ArrayList<Location> locations; //ArrayList of Locations
    private ArrayList<GridObject> gridObjects; //ArrayList of GridObjects
    private ArrayList<GridObject> deletedObjects; //ArrayList of GridObjects to be removed from the the gridObjects ArrayList
    private ArrayList<Animal> winners; //ArrayList for if there are more than one winner
    
    public Grid(int size)
    {
       this.size = size;
       locations = new ArrayList(size*size);
       gridObjects = new ArrayList();           //initialise variable
       deletedObjects = new ArrayList();
       winners = new ArrayList();
       
       for(int i = 0; i < size; i++){
           for(int j = 0; j < size; j++){
               locations.add(new Location(i,j)); //creates the grid by assigning Locations to the locations ArrayList depending on size
           }
        }
   
    }

    public void addAvatar(GridObject gridObject, int x, int y){
        gridObjects.add(gridObject); //adds the GridObject to the gridObject ArrayList
        
        int index = (size*x) + y; // converts the coordinates to an index number
        
        locations.get(index).setGridObject(gridObject); //sets the GridObject for this location
        gridObject.setLocation(locations.get(index)); //sets the Location for the GridObject
    }
    
    public void start(int rounds){ //starts the game with the amount of rounds      
        for(int i = 1; i <= rounds; i++){ //loops through each round
            System.out.println("*** Round " + i + " ***"); //prints out the round number
            
            for(GridObject gridObject: gridObjects){ //loops through each GridObject in the gridObjects ArrayList
                if(gridObject instanceof Seed){ 
                    gridObject.printLocation(); //if the GridObject is a Seed Object, it prints the location of the Seed GridObject
                }
                else if(gridObject instanceof Animal){
                    ifAnimal(gridObject); //if the GridObject is an Animal object, it runs ifAnimal() with the GridObject
                }
            }
            deleteObjectsFromGrid(); //deletes GridObjects from the grid that are out of energy or are have been eaten
            System.out.println();
        }
        
        findWinners(); //finds the winner(s) of the game
        
        if(winners.size() == 1){ //prints the winner if there is only one winner from the winners ArrayList
            System.out.println("The winner with " + winners.get(0).getEnergy() + " energy is " + winners.get(0).getName() + "!!!");
        }
        else if(winners.size() > 1){ //prints the winners if there is more than one winner from the winners ArrayList
            System.out.println("The winners with " + winners.get(0).getEnergy() + " energy are:");
            
            for(Animal winner: winners){
                System.out.println(winner.getName());
            }
        }
        else{ //prints if there are no winners
            System.out.println("There are no winners!!!");
        }
        
        System.out.println("********* GAME OVER *********");
    }
    
    public void ifAnimal(GridObject gridObject){ //method if the GridObject is an Animal object
        Animal animal = (Animal) gridObject; //casts reference to the Animal Object
        Location currLocation = gridObject.getLocation(); //gets the current location of the GridObject
        Location destination = null; //the destination location is null
                    
        while(destination == null || currLocation == destination){ //runs until the destination location is not null or  it is not the same as the current location
            destination = getRandomLocation(); //finds a random location in the locations ArrayList
        }
                    
        int currIndex = (size*currLocation.x) + currLocation.y; //converts the current location to an index
        int destIndex = (size*destination.x) + destination.y; //converts the destination location to an index
                    
        if(gridObject.move(destination)){ //checks to see if the GridObject can move to the destination i.e. has enough energy
            if(destination.isOccupied()){ //checks to see if the destination is occupied
                GridObject destGridObject = destination.getGridObject(); //if the destination is occupied, it gets the GridObject in that locaion
                        
                if(animal instanceof Canary && destGridObject instanceof Seed){ //if a Canary Object flies to a Seed Object
                    Seed seed = (Seed) destGridObject; //casts reference to the Seed Object
                            
                    animal.eat(seed); //the Canary Object eats the Seed Object
                    deletedObjects.add(destGridObject);
                }                  
                else if(animal instanceof Cat && destGridObject instanceof Canary){ //if a Cat Object runs to a Canary Object
                    Canary canary = (Canary) destGridObject; //casts reference to the Canary Object
                                
                    animal.eat(canary); //the Cat Object eats the Canary Object
                    deletedObjects.add(destGridObject);
                }
                else if(animal instanceof Cat && destGridObject instanceof Seed){ //if a Cat Object runs to a Seed Object
                    Seed seed = (Seed) destGridObject; //casts reference to the Seeed Object
                    
                    animal.eat(seed); //the Cat Object tries to eat the Seed Object but fails
                    ifAnimal(gridObject); //runs ifAnimal() until the Cat Object doesn't run to a Seed Object
                }
                else if(animal instanceof Canary && destGridObject instanceof Canary){ //if a Canary Object flies to another Canary Object
                    System.out.println("There is already a canary there. I better move."); //prints that there is already a Canary Object here and that the Canary Object needs to move
                    ifAnimal(gridObject); //runs ifAnimal() until the Canary Object doesn't fly to another Canary Object
                }
                else if(animal instanceof Canary && destGridObject instanceof Cat){ //if a Canary Object flies to a Cat Object
                    System.out.println("That's a cat. I definitely need to move."); //prints that there is Cat Object here and that the Canary Object definitely nees to move
                    ifAnimal(gridObject); //runs ifAnimal() until the Canary Object doesn't fly to a Cat Object
                }
            }
            
            locations.get(destIndex).setGridObject(gridObject); //moves the GridObject to the destination in the locations ArrayList, providing the GridObject moved
            locations.get(currIndex).setGridObject(null); //removes the GridObect from the current location in the locations ArrayList, providing the GridObject moved
        }
        else{
            deletedObjects.add(gridObject); //if the GridObject can't move, it adds it to the deletedObect ArrayList to be removed
        }
    }
    
    public void deleteObjectsFromGrid(){ //method to remove GridObjects from the gridObjects ArrayList (used this method because I was having problems removing from the ArrayList in the ifAnimal() method)
        for(GridObject deletedObject: deletedObjects){ //loops through deletedObjects ArrayList
            if(deletedObject instanceof Seed){
                Seed seed = (Seed) deletedObject; //casts reference to the Seed Object and prints it has been removed from the grid
                System.out.println(seed.getName() + " has been removed from the Grid.");
            }
            else if(deletedObject instanceof Animal){
                Animal animal = (Animal) deletedObject; //casts reference to the Animal Object and prints it has been removed from the grid
                System.out.println(animal.getName() + " has been removed from the Grid.");
            }
            gridObjects.remove(deletedObject); //removes the deletedObject from the gridObjects ArrayList
        }
        
        deletedObjects.clear(); //clears the deletedObjects ArrayList
    }
    
    public Location getRandomLocation(){ //method to get a random location in the locations Arraylist
        Location randomLocation = locations.get((int)(Math.random() * (size*size))); //Math.random() randomises a location in the locations ArrayList
        
        return randomLocation;
    }
    
    public void findWinners(){ //finds the winners of the game
        int max = 0; //max energy
        ArrayList<Animal> animals = new ArrayList(); //ArrayList to hold the GridObjects that are converted to Animal Objects
        
        for(GridObject gridObject: gridObjects){ //loops through the gridObjects ArrayList
            if(gridObject instanceof Animal){ //checks to see if they're an Animal Object
                Animal animal = (Animal) gridObject; //casts reference to the Animal Object
                animals.add(animal); //adds them to the animals ArrayList
            }
        }
        
        for(Animal animal: animals){ //then it loops through the animals ArrayList
            if(animal.getEnergy() > max){
                max = animal.getEnergy(); //finds the maximum energy value in the ArrayList
            }   
        }
         
        for(Animal animal: animals){ //loops through the animals ArrayList again
            if(animal.getEnergy() == max){
                winners.add(animal); //adds the Animal Object with the max energy to the winners ArrayList (the for loop is to check if there more than one winner)
            }   
        }
    }
}
