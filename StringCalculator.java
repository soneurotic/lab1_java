import java.sql.SQLOutput;
import java.util.*;
import org.apache.commons.lang3.*;

import java.util.regex.*;
public class StringCalculator {
    private String delimiters = ",\\n";
    private int sum;
    private int DelimitersCount;

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
        String userInput;
        String[] del;
        if(input.startsWith("//")){
            del = (input.substring(2, input.indexOf("\\n"))).split("\\[|\\]");
            userInput = input.substring(input.indexOf("\\n") + 2);
        }
        else{
            del = input.split("");
            userInput = input;
        }
        /*String[] UpdatedDel = ArrayUtils.insert(2, del, ",", "\\n");

        for(String element: UpdatedDel)
            DelimitersCount+= StringUtils.countMatches(userInput, element);*/

        if(input.startsWith("//")){
            String DelimitersInSubstring = input.substring(2, input.indexOf("\\n"));
            delimiters += DelimitersInSubstring.replace("[", "");
            delimiters = delimiters.replace("]", "");
            StringTokenizer result = new StringTokenizer(userInput, delimiters);
            if(DelimitersCount >= result.countTokens())
                throw new Exception("Wrong delimiters positioning");

            while(result.hasMoreTokens()) {
                OperatingNumbers(result.nextToken());
            }
        }
        else if(input.equals("")){
            sum = 0;
        }
        else{
            StringTokenizer output = new StringTokenizer(input, delimiters);
            if(DelimitersCount >= output.countTokens())
                throw new Exception("Wrong delimiters positioning");
            while(output.hasMoreTokens()) {
                OperatingNumbers(output.nextToken());
            }
        }

        if(NegativeNumbers.size() != 0)
            System.out.println("Negative numbers are not allowed\n" + NegativeNumbers);
            //throw new Exception("Negative numbers are not allowed\n" + NegativeNumbers);

        return sum;
    }
    private void OperatingNumbers(String PreOutput){
        if(Integer.parseInt(PreOutput) < 0){
            NegativeNumbers.add(Integer.parseInt(PreOutput));
            return;
        }
        if(Integer.parseInt(PreOutput) > 1000)
            return;
        sum+=Integer.parseInt(PreOutput);
    }
}