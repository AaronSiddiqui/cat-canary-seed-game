
/**
 * Write a description of class Seed here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Seed extends Vegetable implements Food,GridObject
{
    private Location location; //location of the Seed Object

    /**
     * Constructor for objects of class Seed
     */
    public Seed(String name)
    {
        super();
        calories = 10;
        this.name = name;
    }
    
    @Override
    public void setLocation(Location location){ //sets the location
        this.location = location;
    }
    
    @Override
    public Location getLocation(){ //get the location
        return location;
    }
    
    @Override
    public int getCalories(){ //gets the calories
        return calories;
    }
    
    @Override
    public int extractEnergy(){ //gets the energy from a Seed Object
        int cal = calories;
        calories = 0;
        return cal;
    }
    
    @Override
    public boolean move(Location destination){ //move method of a Seed Object
        return false; //returns false because a Seed Object can't move
    }
    
    public void printLocation(){ //prints the location of the Seed Object
        System.out.println(name + " at " + location + " with calories " + calories + ", cannot move."); 
    }
}
