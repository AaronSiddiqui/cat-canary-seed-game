package GridObjects;

// Abstract class for the food classes
public interface Food {
    public int getCalories();
    
    // Removes the energy from the Food object
    public int extractEnergy();
}
