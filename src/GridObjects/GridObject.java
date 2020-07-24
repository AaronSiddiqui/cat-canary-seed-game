
/**
 * Write a description of interface GridObject here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface GridObject
{
    public void setLocation(Location location); //sets the location of the GridObject
    
    public Location getLocation(); //gets the location of the GridObject
    
    public boolean move(Location destination); //moves the GridObject to the destination
    
    public void printLocation(); //prints the location of the GridObject
}
