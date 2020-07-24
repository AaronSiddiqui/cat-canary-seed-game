package gridobjects;

// Abstract class for all of the animal classes
public abstract class Animal {
    boolean hasSkin;
    boolean breathes;
    String colour;
    int energy;
    String name;
    
    public Animal() {
    	// Default fields
        breathes = true;
        hasSkin = true;
        colour = "grey";
    }
    
    // Determines if and how an animal can eat its food
    public abstract boolean eat(Food food);
    
    public String getColour() {
        return colour;
    }
    
    public int getEnergy() {
        return energy;
    }
    
    public boolean hasSkin() {
        return hasSkin;
    }
    
    // String method to return the animal's name
    public String getName() {
        String str = name;
        str+=" the ";
        Class cls = getClass();
        String className = cls.getSimpleName();
        str+= className;
        return str;
    }
}
