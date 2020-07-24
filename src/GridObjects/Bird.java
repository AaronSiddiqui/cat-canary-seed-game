package GridObjects;

// Abstract class for all the bird classes
public abstract class Bird extends Animal {
    boolean hasFeathers;
    boolean hasWings;
    boolean flies;

    public Bird() {
    	// Overrides some default fields
        super();
        colour = "black";
        hasFeathers = true;
        hasWings = true;
        flies = true;
    }
    
    public void sing() {
        System.out.println("tra la la");
    }

    public boolean hasWings() {
        return hasWings;
    }
    
    public boolean hasFeathers() {
        return hasFeathers;
    }
}
