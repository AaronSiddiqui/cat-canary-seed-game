package Grid;

import GridObjects.GridObject;

// Concrete class for the location
// Uses x and y coordinates
public class Location {
	// x and y are global (anyone grid object should be able to access them) and immutable (they don't change)
    public final int x;
    public final int y;
    private GridObject gridObject; 	//Current grid object in this location
    private boolean occupied;		//Boolean to see if the location is occupied by a grid object

    public Location(int x, int y) {
       this.x = x;
       this.y = y;
    }

    public GridObject getGridObject() {
        return gridObject;
    }
    
    public void setGridObject(GridObject gridObject) {
    	// First checks if the current location is unoccupied
        if (gridObject == null) {
            this.gridObject = null;
            occupied = false;
            return;
        }
        
        // If it's not occupied it sets the grid object and occupation
        this.gridObject = gridObject;
        occupied = true;
    }
    
    public boolean isOccupied() {
        return occupied;
    }
    
    // Calculates the shortest distance between this location and the input location
    public double distanceTo(Location location) {
    	// Uses Pythagoras' Theorem to do this
        double temp = (this.x - location.x)* (this.x - location.x) + (this.y - location.y)*(this.y - location.y);
        return Math.sqrt(temp);
    }
    
    // Returns the coordinates as a string
    @Override
    public String toString() {
        String str = "";
        str += "Location (" + x +","+ y + ")";
        return str;
    }
    
    @Override
    public boolean equals(Object object) {
    	// A check to see if the object is null
        if (object == null) {
          return false;  
        }
        
        if (object instanceof Location) {
            Location location = (Location) object;
            
            if(location.x == x && location.y ==y){
                return true;
            }
        }
        return false;
    }
}
