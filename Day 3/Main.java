import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.PrintStream;
import static java.lang.Math.*;

class Main {
  public static void main(String[] args) {    
        List<String> firstWireInstruction = new ArrayList<>();
        List<String> secondWireInstruction = new ArrayList<>();

        File input = new File("input.txt");
        try {
            Scanner firstWire;
            Scanner secondWire;
            Scanner scanner = new Scanner(input);
            if(scanner.hasNext()) {
                firstWire = new Scanner(scanner.next());
                firstWire.useDelimiter("\\,");
                while (firstWire.hasNext())
                    firstWireInstruction.add(firstWire.next());
                firstWire.close();
            }
            if(scanner.hasNext()) {
                secondWire = new Scanner(scanner.next());
                secondWire.useDelimiter("\\,");
                while(secondWire.hasNext())
                    secondWireInstruction.add(secondWire.next());
                secondWire.close();
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<String> firstWire = new ArrayList<>();
        List<String> secondWire = new ArrayList<>();

        drawWireGrid(firstWireInstruction, firstWire);
        drawWireGrid(secondWireInstruction, secondWire);

        List<String> intersections = new ArrayList<>(firstWire);
        intersections.retainAll(secondWire);

        System.out.println(calculateShortestRoute(intersections));
        System.out.println("Shortest wire needed is " + getTotalWireLengthToCrosssection(intersections, firstWire, secondWire) + " meters long.");
    }

    private static int getTotalWireLengthToCrosssection(List<String> intersection, List<String> firstWire, List<String> secondWire){
        int shortest = 0;
        String shortestIndex ="";
        for(int i=0; i<intersection.size(); i++){
            String intersectionValue = intersection.get(i);
            int latest = firstWire.indexOf(intersectionValue) + secondWire.indexOf(intersectionValue);
            if(i==1 || latest < shortest) {
                shortest = latest;
                shortestIndex = intersectionValue;
            }
        }
        System.out.println("The least wire goes to intersection " + shortestIndex);
        return shortest;
    }

    private static String calculateShortestRoute(List<String> intersections) {
        int shortestRoute = 0;
        int startX = 0;
        int startY = 0;
        String end;

        for(int i=1; i<intersections.size(); i++){
            end = intersections.get(i);
            try{
                Scanner endScanner = new Scanner(end);
                endScanner.useDelimiter("\\,");
                int endX = endScanner.nextInt();
                int endY = endScanner.nextInt();
                int countedRoute = Math.abs(Math.abs(endX) - Math.abs(startX)) + Math.abs(Math.abs(endY) - Math.abs(startY));
                if(i==1 || countedRoute < shortestRoute)
                    shortestRoute = countedRoute;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "Shortest path is " + shortestRoute +" long";
    }

    private static void drawWireGrid(List<String> instruction, List<String> wire){
        int x=0;
        int y=0;
        for(int i=0; i<instruction.size(); i++){
            switch(instruction.get(i).substring(0,1)){
                case "R":
                {
                    int length = Integer.parseInt(instruction.get(i).substring(1));
                    for(int j=0; j<length; j++)
                        wire.add(x + "," + (y+j));
                    y=y+length;
                    break;
                }
                case "L":
                {
                    int length = Integer.parseInt(instruction.get(i).substring(1));
                    for(int j=0; j<length; j++)
                        wire.add(x + "," + (y-j));
                    y=y-length;
                    break;
                }

                case "U":
                {
                    int length = Integer.parseInt(instruction.get(i).substring(1));
                    for(int j=0; j<length; j++)
                        wire.add((x+j) + "," + y);
                    x=x+length;
                    break;
                }
                case "D":
                {
                    int length = Integer.parseInt(instruction.get(i).substring(1));
                    for(int j=0; j<length; j++)
                        wire.add((x-j) + "," + y);
                    x=x-length;
                    break;
                }
            }
        }
    }
}