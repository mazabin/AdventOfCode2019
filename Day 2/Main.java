import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
  public static void main(String[] args) {    
    List<Integer> initialMemory = new ArrayList<>();

    File input = new File("input.txt");
    try {
        Scanner scanner = new Scanner(input);
        scanner.useDelimiter("\\D");
        while (scanner.hasNext())
            initialMemory.add(scanner.nextInt());
        System.out.println("There are " + initialMemory.size() + " elements loaded into the memory.");
        scanner.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }

        System.out.println("The computer is restarted at value: " + getAddressZeroValue(initialMemory, 12,2));

        for(int noun=0; noun<100; noun++){
            for(int verb=0;verb<100;verb++){
                if(getAddressZeroValue(initialMemory,noun,verb) == 19690720)
                    System.out.println("Noun is " + noun + " and verb is " + verb + ". \nThe gravity assist is ready!");
            }
        }
    }

    private static int getAddressZeroValue(List<Integer> memory, int noun, int verb){
        List<Integer> workingMemory = new ArrayList<>(memory);
        int i=0;
        workingMemory.set(1, noun);
        workingMemory.set(2, verb);
        boolean programIsRunning = true;
        while(programIsRunning) {
            int task = workingMemory.get(i);
            switch (task) {
                case 1: {
                    workingMemory.set(workingMemory.get(i + 3), workingMemory.get(workingMemory.get(i + 1)) + workingMemory.get(workingMemory.get(i + 2)));
                    i += 4;
                    break;
                }
                case 2: {
                    workingMemory.set(workingMemory.get(i + 3), workingMemory.get(workingMemory.get(i + 1)) * workingMemory.get(workingMemory.get(i + 2)));
                    i+=4;
                    break;
                }
                case 99:
                    programIsRunning = false;
            }
        }
        return workingMemory.get(0);
    }
}