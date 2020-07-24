import Grid.Grid;
import GridObjects.Seed;
import GridObjects.Canary;
import GridObjects.Cat;

public class GridTest {
    public static void main(String[] args) {
        GridTest gridTest = new GridTest();
        gridTest.testv1();
    }
    
    // 1st test
    public void testv1() {
    	// Creates a 4x4 grid
        Grid grid = new Grid(4);
        
        // Adds 3 seeds, 3 birds and 1 cat to specified locations on the grid
        Seed daisy = new Seed("Daisy seed");
        Seed barley = new Seed("Barley seed");
        Seed rose = new Seed("Rose seed");
        
        grid.addAvatar(daisy, 0, 1);
        grid.addAvatar(barley, 1, 3);
        grid.addAvatar(rose, 3, 1);
        
        Canary tweety = new Canary("Tweetie");
        Canary twooty = new Canary("Twooty");
        Canary twitty = new Canary("Twitty");
        
        grid.addAvatar(tweety, 1, 2);
        grid.addAvatar(twooty, 2, 2);
        grid.addAvatar(twitty, 0, 2);
        
        Cat tom = new Cat("Tom");
        
        grid.addAvatar(tom, 0, 0);
        
        // Starts the game with 5 rounds
        grid.start(5);
    }
}
