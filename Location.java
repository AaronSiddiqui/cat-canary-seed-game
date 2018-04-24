
/**
 * Write a description of class Location here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Location
{
    public final int x; //x coordinate that is globally available and can't be changed
    public final int y; //y coordinate that is globally available and can't be changed
    private GridObject gridObject; //GridObject in this location
    private boolean occupied; //boolean to see if the location is occupied by a GridObject

    /**
     * Constructor for objects of class Location
     */
    public Location(int x, int y)
    {
       this.x = x;  //initialises the variables
       this.y = y;
    }

    public GridObject getGridObject(){ //returns the GridObject
        return gridObject;
    }
    
    public void setGridObject(GridObject gridObject){ //sets the GridObject in this location
        if(gridObject == null){
            this.gridObject = null;  //if the GridObject is null, it changes the GridObject in this location to null and changes occupied to false
            occupied = false;
            return;
        }
        
        this.gridObject = gridObject; //sets the GridObject in this location and changes occupied to true
        occupied = true;
    }
    
    public boolean isOccupied(){ //checks to see if this location is occupied or not
        return occupied;
    }
    
    /**
     * calacultes the shortest distance between this location
     * and the input location
     */
    public double distanceTo(Location location){ //calculates the distance that the GridObject travelled
        double temp = (this.x - location.x)* (this.x - location.x) + (this.y - location.y)*(this.y - location.y); //Pythagoras' Theorem
        return Math.sqrt(temp); //returns the square root of temp
    }
    
    @Override
    public String toString(){ //converts coordinates to String
        String str = "";
        str += "Location (" + x +","+ y + ")";
        return str;
    }
    
    @Override
    public boolean equals(Object object){ //checks to see if the Location Objects are equal
        if (object==null){
          return false;  
        }
        
        if (object instanceof Location){
            Location location = (Location) object;
            
            if(location.x == x && location.y ==y){
                return true;
            }
        }
        return false;
    }
}
