package GridObjects;

import Grid.Location;

// Concrete class for the see
public class Seed extends Vegetable implements Food,GridObject {
    private Location location;

    public Seed(String name) {
        super();
        calories = 10;
        this.name = name;
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
    public int getCalories( ){
        return calories;
    }
    
    // Removes the energy from the seed
    @Override
    public int extractEnergy() {
        int cal = calories;
        calories = 0;
        return cal;
    }
    
    // Determines how the seed will move
    // SHOCK HORROR, it doesn't
    @Override
    public boolean move(Location destination) {
        return false;
    }
    
    public void printLocation() {
        System.out.println(name + " at " + location + " with calories " + calories + ", cannot move."); 
    }
}
