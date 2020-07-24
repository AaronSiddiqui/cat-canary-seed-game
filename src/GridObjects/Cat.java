
/**
 * Write a description of class Cat here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Cat extends Feline implements GridObject
{ 
    private Location location; //location of the Cat Object
    
    /**
     * Constructor for objects of class Cat
     */
    public Cat(String name)
    {
        super();
        this.name = name;
        colour = "black";
        energy = 10; // starting energy
    }
    
    @Override
    public void setLocation(Location location){ //sets the location
        this.location = location;
    }
    
    @Override
    public Location getLocation(){ //gets the location
        return location;
    }
    
    @Override
    public boolean eat(Food food){
        if(food == null){
            return false; //checks to see if the Food Object is null and returns false
        }
        
        if(food instanceof Canary){ //checks to see if the Food Object is a Canary Object
            Canary canary = (Canary) food; //casts reference to the Canary Object
            
            energy += canary.extractEnergy(); // extracts the energy from the Seed Object
            System.out.println(getName() + " has eaten " + canary.getName() + ". Energy level at " + energy + "."); //prints that the Cat Object has eaten the Canary Object and its energy
            
            return true; //returns true if the Cat Object ate the Canary Object
        }
        else if(food instanceof Seed){
            Seed seed = (Seed) food; //casts reference to the Seed Object
            
            System.out.println(getName() + " cannot eat this " + seed.getName() +".");  //prints that is can't eat the Seed Object
        }
        return false; //returns false if there was a problem eating i.e. couldn't eat the Seed Object
    }
    
    @Override
    public boolean move(Location destination){
        if(energy > 0){
            double distance = this.location.distanceTo(destination); //calculates the distance travelled to the destination
            setLocation(destination); //sets that destination as the location of the Cat Object

            System.out.printf("%s runs %f metres. ", getName(), distance); //prints the distance the Cat Object moved
            energy -= 2; //loses two units of energy for every move
            
            if(energy < 0){
                energy = 0; //if the energy get goes below 0, this changes it to 0
            }
            
            System.out.printf("%s now has Energy level: %d\n", getName(), energy); //prints the energy of the Cat Object
            printLocation(); //prints the location of the Cat Object
            
            return true; //returns true if the Cat Object has enough energy
        }
        else{
            System.out.println(getName() + " is out of energy and cannot move."); //prints that the  Cat Object is out of energy and can't move
        }
        
        return false; //returns false if the Cat Object doesn't have enough energy
    }
    
    @Override
    public void printLocation(){ //prints the location of the Cat Object
        System.out.println(getName() + " is at " + location + "."); 
    }
}
