
/**
 * Write a description of class Canary here.
 *
 * @author (Conor Hayes)
 * @version (October 5th 2017)
 */
public class Canary extends Bird implements Food,GridObject 
{
    private Location location; //location of the Canary Object

    /**
     * Constructor for objects of class Canary
     */
    public Canary(String name)
    {
        super(); // call the constructor of the superclass Bird
        this.name = name;
        colour = "yellow"; // this overrides the value inherited from Bird
        energy = 5;
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
    public int getCalories(){ //gets the calories
        return energy;
    }
    
    @Override
    public int extractEnergy(){ //gets the energy from a Seed Object
        int cal = energy;
        energy = 0;
        return cal;
    }
    
    /**
     * Sing method overrides the sing method
     * inherited from superclass Bird
     */
    @Override // good programming practice to use @Override to denote overridden methods
    public void sing(){
        System.out.println("tweet tweet tweet");
    }
    
    @Override
    public boolean eat(Food food){
        if(food == null){
            return false; //check to see if the Food Object is null and returns false
        }
        
        if(food instanceof Seed){ //checks to see if the Food Object is a Seed Object
            Seed seed = (Seed) food; //casts reference to the Seed Object
        
            energy += seed.extractEnergy(); // extract the energy from the Seed Object
            System.out.println(getName() + " has eaten " + seed.getName() + ". Energy level at " + energy + "."); //prints that the Canary Object has eaten the Seed Object and its energy
            return true; //returns true if the Canary Object has eaten the Seed Object
        }
        
        return false; //returns false if there was a problem eating
    }
    
    @Override
    public boolean move(Location destination){
        if(energy > 0){
            double distance = this.location.distanceTo(destination); //calculates the distance travelled to the destination
            setLocation(destination); //sets that destination as the location of the Canary Object

            System.out.printf("%s flies %f metres. ", getName(), distance); //prints the distance the Canary Object move
            energy--; //loses one unit of energy for every move
            
            if(energy < 0){
                energy = 0; //if the energy get goes below 0, this changes it to 0
            }
            
            System.out.printf("%s now has Energy level: %d\n", getName(), energy); //prints the energy of the Canary Object
            printLocation(); //prints the location of the Canary Object
            
            return true; //returns true if the Canary Object has enough energy
        }
        else{
            System.out.println(getName() + " is out of energy and cannot move."); //prints that the Canary Object is out of energy and can't move
        }
        
        return false; //returns false if the Canary Object doesn't have enough energy
    }
    
    @Override
    public void printLocation(){ //prints the location of the Canary Object
        System.out.println(getName() + " is at " + location + "."); 
    }
}
