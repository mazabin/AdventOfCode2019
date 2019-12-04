import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Hashtable;

class Main {
  public static void main(String[] args) {
        String input = "372037-905157";

        int start = Integer.parseInt(input.substring(0,6));
        int end = Integer.parseInt(input.substring(7));

        List<Integer> possiblePasswords = new ArrayList<>();
        for(int i=start; i<=end; i++){
            if(hasDoubleDigit(i)){
                if(hasGrowingDigits(i)){
                    possiblePasswords.add(i);
                }
            }
        }
        System.out.println("There is " + possiblePasswords.size() + " possible passwords.");

        for(int i=0; i<possiblePasswords.size(); i++) {
            if(!hasTwoDoubleDigitsGroup(possiblePasswords.get(i))){
              possiblePasswords.remove(i);               
              i--;
            }
        }

        System.out.println("After narrowing criteria only " + possiblePasswords.size() + " passwords are possible.");
    }

    private static boolean hasTwoDoubleDigitsGroup(Integer possiblePassword) {
        Hashtable<Integer, HashSet<Integer>> c = hasDoubleDigitsMoreThanOnce(possiblePassword);
        for(int i=2; i<=6; i++) {
            if (c.containsKey(i)) {
              System.out.println("The password: " + possiblePassword + " contains " + i  + " double digits");
                if (c.get(i) != null) {
                  int uniqueDigits = c.get(i).size();
                  System.out.println("It has " + uniqueDigits +" unique digits in doubles.");
                  if(i == 2 && uniqueDigits == 1)
                    return false;
                  if (i == 3 && uniqueDigits == 1)
                    return false;
                  if (i == 4){
                    if(c.get(i).size() == 1)
                      return false;
                    else if(c.get(i).size() == 2){
                      if(possiblePassword%10000 / 1000 != possiblePassword%1000 / 100)
                        return false;
                    }
                  }
                  if (i == 5)
                    return false;                        
                }            
            }
        }
        return true;
    }

    private static Hashtable<Integer, HashSet<Integer>> hasDoubleDigitsMoreThanOnce(Integer number) {
        int divider = 100000;
        int doubleDigits=0;
        HashSet<Integer> differentDoubleDigits = new HashSet<>();
        for(int i=0; i<5; i++) {
            int firstDigit = number/divider;
            int secondDigit = ((number % divider)/(divider/10));
            if (firstDigit == secondDigit) {
                doubleDigits++;
                differentDoubleDigits.add(firstDigit);
            }
            number = number % divider;
            divider = divider / 10;
        }
        Hashtable<Integer, HashSet<Integer>> toReturn = new Hashtable<>();
        toReturn.put(doubleDigits, differentDoubleDigits);
        return toReturn;
    }

    private static boolean hasGrowingDigits(int number) {
        int divider = 100000;
        boolean hasGrowingDigits = true;
        for(int i=0; i<5; i++) {
          int firstDigit = number/divider;
          int secondDigit = ((number % divider)/(divider/10));
            if (secondDigit < firstDigit)
                hasGrowingDigits = false;
            number = number % divider;
            divider = divider / 10;
        }
        return hasGrowingDigits;
    }

    private static boolean hasDoubleDigit(int number){
        int divider = 100000;
        boolean hasDoubleDigit = false;
        for(int i=0; i<5; i++) {
          int firstDigit = number/divider;
          int secondDigit = ((number % divider)/(divider/10));
            if (firstDigit == secondDigit)
                hasDoubleDigit = true;
            number = number % divider;
            divider = divider / 10;
        }
        return hasDoubleDigit;
    }
}