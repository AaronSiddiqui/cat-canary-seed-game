package GridObjects;

// Abstract class for the vegetable classes
public abstract class Vegetable implements Food {
    int calories;
    String name;
    
    // Concrete method because it doesn't change for the children classes
    public String getName() {
        return name;
    }
}
