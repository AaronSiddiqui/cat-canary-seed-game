
/**
 * Abstract class Vegetable - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Vegetable implements Food
{
    int calories;
    String name;

    public String getName(){ // concrete method
        return name;
    }
}
