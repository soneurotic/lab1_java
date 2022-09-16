import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner UserStringInput = new Scanner(System.in);
        StringCalculator string_calculator = new StringCalculator();

        System.out.println("Enter a string: ");
        String string_input = UserStringInput.nextLine();

        try{
            int sum = string_calculator.add(string_input);
            System.out.println("Sum = " + sum);
        }
        catch(Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }
}