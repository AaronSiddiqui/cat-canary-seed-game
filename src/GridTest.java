
/**
 * Write a description of class GridTest here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GridTest
{
    public static void main(String[] args){
        GridTest gridTest = new GridTest();
        gridTest.testv1();
    }
    
    public void testv1(){
        Grid grid = new Grid(4); //creates a Grid Object
        
        Seed daisy = new Seed("Daisy seed");
        Seed barley = new Seed("Barley seed"); //creates 3 Seed Objects
        Seed rose = new Seed("Rose seed");
        
        grid.addAvatar(daisy, 0, 1);
        grid.addAvatar(barley, 1, 3); //adds the 3 Seed Objects to the grid in certain locations
        grid.addAvatar(rose, 3, 1);
        
        Canary tweety = new Canary("Tweetie");
        Canary twooty = new Canary("Twooty"); //creates 3 Canary Objects
        Canary twitty = new Canary("Twitty");
        
        grid.addAvatar(tweety, 1, 2);
        grid.addAvatar(twooty, 2, 2); //adds 3 CanaryObjects to the grid in certain locations
        grid.addAvatar(twitty, 0, 2);
        
        Cat tom = new Cat("Tom"); //creates a Cat Object
        
        grid.addAvatar(tom, 0, 0); //adds the Cat Object to the grid in a certain location
        
        grid.start(5); //starts the game
    }
}
