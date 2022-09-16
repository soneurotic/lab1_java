import java.util.*;
import java.util.regex.*;
public class StringCalculator {
    private String delimiters = ",\\n";
    private int sum;
    private final List<Integer> NegativeNumbers = new ArrayList<>();
    public int add(String input) throws Exception {
        if(input.startsWith(",") || input.startsWith("\\n")||input.endsWith(",") || input.endsWith("\\n"))
            throw new Exception("Wrong delimiters positioning");

        Pattern ThirdStep = Pattern.compile(",|(\\\\n)");
        Matcher WrongPositioning = ThirdStep.matcher(input);
        char[] InputInChar = input.toCharArray();
        while (WrongPositioning.find()){
            if (!(Character.isDigit(InputInChar[WrongPositioning.end()]) | InputInChar[WrongPositioning.end()]=='-')){
                throw new Exception("Wrong delimiters positioning");
            }
        }
        if(input.startsWith("//")){
            String DelimitersInSubstring = input.substring(2, input.indexOf("\\n"));
            String FactualInput = input.substring(input.indexOf("\\n") + 2);
            delimiters += DelimitersInSubstring.replace("[", "");
            delimiters = delimiters.replace("]", "");
            StringTokenizer result = new StringTokenizer(FactualInput, delimiters);
            while(result.hasMoreTokens()) {
                OperatingNumbers(result.nextToken());
            }
        }
        else if(input.equals("")){
            sum = 0;
        }
        else{
            StringTokenizer output = new StringTokenizer(input, delimiters);
            while(output.hasMoreTokens()) {
                OperatingNumbers(output.nextToken());
            }
        }

        if(NegativeNumbers.size() != 0)
            throw new Exception("Negative numbers are not allowed\n" + NegativeNumbers);

        return sum;
    }
    private void OperatingNumbers(String PreOutput){
        if(Integer.parseInt(PreOutput) < 0)
            NegativeNumbers.add(Integer.parseInt(PreOutput));
        if(Integer.parseInt(PreOutput) > 1000)
            return;
        sum+=Integer.parseInt(PreOutput);
    }
}