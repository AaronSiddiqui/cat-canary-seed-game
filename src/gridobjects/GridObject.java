package GridObjects;

import Grid.Location;

// Abstract class for the grid objects
public interface GridObject {
    public void setLocation(Location location);
    
    public Location getLocation();
    
    // Determines how the object will move
    public boolean move(Location destination);
    
    public void printLocation();
}
