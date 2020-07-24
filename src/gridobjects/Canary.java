package gridobjects;

import grid.Location;

// Concrete class for the canary
public class Canary extends Bird implements Food, GridObject {
    private Location location;

    public Canary(String name) {
    	// Overrides some default fields
        super();
        this.name = name;
        colour = "yellow";
        energy = 5;
    }
    
    @Override
    public void setLocation(Location location) {
        this.location = location;
    }
    
    @Override
    public Location getLocation() {
        return location;
    }
    
    @Override
    public int getCalories() {
        return energy;
    }
    
    // Removes the energy from the canary
    @Override
    public int extractEnergy() {
        int cal = energy;
        energy = 0;
        return cal;
    }
    
    @Override
    public void sing(){
        System.out.println("tweet tweet tweet");
    }
    
    // Determines if and how the canary can eat its food
    @Override
    public boolean eat(Food food) {
    	// A check to see if the Food object is empty
        if (food == null) {
            return false;
        }
        
        // Checks to see if the Food object is a seed, extracts the energy and prints the result
        if (food instanceof Seed) {
            Seed seed = (Seed) food;
        
            energy += seed.extractEnergy();
            System.out.println(getName() + " has eaten " + seed.getName() + ". Energy level at " + energy + ".");
            return true;
        }
        
        // Returns false otherwise
        return false;
    }
    
    // Determines how the canary will move
    @Override
    public boolean move(Location destination) {
        if (energy > 0) {
        	// Calculates the distance to travel to the destination and sets the new location
            double distance = this.location.distanceTo(destination);
            setLocation(destination);

            System.out.printf("%s flies %f metres. ", getName(), distance);
            energy--;	// Decrements by 1 energy for the movement
            
            // A check to ensure the energy doesn't become negative
            if (energy < 0) {
                energy = 0;
            }
            
            System.out.printf("%s now has Energy level: %d\n", getName(), energy);
            printLocation();
            
            return true;
        }
        // Prints if the canary is out of energy
        else{
            System.out.println(getName() + " is out of energy and cannot move.");
        }
        
        // Returns false if the canary is out of energy
        return false;
    }
    
    @Override
    public void printLocation() {
        System.out.println(getName() + " is at " + location + "."); 
    }
}
