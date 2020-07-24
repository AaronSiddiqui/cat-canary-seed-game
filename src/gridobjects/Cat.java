package gridobjects;

import grid.Location;

// Concrete class for the cat
public class Cat extends Feline implements GridObject { 
    private Location location;
    
    public Cat(String name) {
    	// Overrides some default fields
        super();
        this.name = name;
        colour = "black";
        energy = 10;
    }
    
    @Override
    public void setLocation(Location location) {
        this.location = location;
    }
    
    @Override
    public Location getLocation() {
        return location;
    }
    
    // Determines if and how the cat can eat its food
    @Override
    public boolean eat(Food food) {
    	// A check to see if the Food object is empty
        if (food == null) {
            return false;
        }
        
        // Checks to see if the Food object is a canary, extracts the energy and prints the result
        if (food instanceof Canary) {
            Canary canary = (Canary) food;
            
            energy += canary.extractEnergy();
            System.out.println(getName() + " has eaten " + canary.getName() + ". Energy level at " + energy + ".");
            
            return true;
        }
        // Check to see if the Food object is a seed and prints that it's inedible
        else if (food instanceof Seed) {
            Seed seed = (Seed) food;
            
            System.out.println(getName() + " cannot eat this " + seed.getName() +".");
        }
        
        // Returns false otherwise
        return false;
    }
    
    // Determines how the cat will move
    @Override
    public boolean move(Location destination) {
        if (energy > 0) {
        	// Calculates the distance to travel to the destination and sets the new location
            double distance = this.location.distanceTo(destination);
            setLocation(destination);

            System.out.printf("%s runs %f metres. ", getName(), distance);
            energy -= 2;	// Decrements by 2 energy for the movement
            
            // A check to ensure the energy doesn't become negative
            if(energy < 0){
                energy = 0;
            }
            
            System.out.printf("%s now has Energy level: %d\n", getName(), energy);
            printLocation();
            return true;
        }
        // Prints if the cat is out of energy
        else {
            System.out.println(getName() + " is out of energy and cannot move.");
        }
        
        // Returns false if the cat is out of energy
        return false;
    }
    
    @Override
    public void printLocation() {
        System.out.println(getName() + " is at " + location + "."); 
    }
}
