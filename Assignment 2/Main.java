///////////////////////////////////////////////////////////////////////////////////////////////
//Main.java
//Author:Connor Tackkett
//Date:9/25/2022
//Description: This is where CaveExplorer.java methods are called and the cave tests are run.
///////////////////////////////////////////////////////////////////////////////////////////////



public class Main {
    //This main class is testing 3 caves. the first and third caves are good working caves with paths
    //The second cave is purposefully incomplete to see if the algorithm also finds incomplete paths
    //First a CaveExplorer object is created that takes in a .txt file for input. The cave is then created
    //And output. The algorithm checks if there is a path in the cave and prints the cave again. If there is
    //A path then the path is printed, otherwise no path is printed. File exceptions are handled by the thrown exception
    public static void main(String[] args) throws Exception {
        System.out.println("Starting CaveExplorer");

        try{
            // First good cave
            CaveExplorer caveExplorer = new CaveExplorer("caveTest.txt");
            caveExplorer.createCave();
            System.out.print(caveExplorer);
            caveExplorer.findMe();
            caveExplorer.solve();
            System.out.print("\n" + caveExplorer);
            String path1 = caveExplorer.getPath();
            System.out.println(path1);

            // Cave with bad path
            CaveExplorer caveExplorer2 = new CaveExplorer("badCave.txt");
            caveExplorer2.createCave();
            System.out.print(caveExplorer2);
            caveExplorer2.findMe();
            caveExplorer2.solve();
            System.out.print("\n" + caveExplorer);
            String path2 = caveExplorer2.getPath();
            System.out.println(path2);

            // Second good cave
            CaveExplorer caveExplorer3 = new CaveExplorer("caveTest2.txt");
            caveExplorer3.createCave();
            System.out.print(caveExplorer3);
            caveExplorer3.findMe();
            caveExplorer3.solve();
            System.out.print("\n" + caveExplorer);
            String path3 = caveExplorer3.getPath();
            System.out.println(path3);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("Finished CaveExplorer");
    }
}
