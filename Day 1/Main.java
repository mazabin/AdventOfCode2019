import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
  public static void main(String[] args) {    
    List<Integer> list = new ArrayList<Integer>();
    
    File input = new File("input.txt");
    try {
        Scanner scanner = new Scanner(input);
        while(scanner.hasNext())
            list.add(Integer.parseInt(scanner.next()));
        System.out.println(" - Boss, we have " + list.size() + " modules to take! How much fuel it will take?");
        scanner.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }

    long sum = 0;
    for(int i=0; i<list.size(); i++){
        sum += (list.get(i)/3) - 2;
    }

    System.out.println(" - We need " + sum + " of fuel.");
    System.out.println(" - But, won't this fuel need some more fuel to get the rocket to fly?");
    System.out.println(" - I suppose you're right. Well, how much of it?");
    System.out.println(" - Counting, boss!");

    sum = 0;
    for(int i=0; i<list.size(); i++){
      list.set(i, calculateMoreFuego(list.get(i), 0));
      sum += list.get(i);
    }
    System.out.println("We actually need " + sum + " of fuel to fly there, boss.");
  }
      
  private static int calculateMoreFuego(int initialFuego, int allFuego){
      int moreFuego = initialFuego/3-2;
      if(moreFuego > 0) {
          allFuego += moreFuego;
          return calculateMoreFuego(moreFuego, allFuego);
      }
      else
          return allFuego;
  }
}