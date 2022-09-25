import java.text.DecimalFormat;
import java.util.Scanner;

public class SayTheNumber {

    private static final String[] tensNames = {"", " ten", " twenty", " thirty",
            " forty", " fifty", " sixty", " seventy", " eighty", " ninety"};

    private static final String[] firstNineteenNames = {"", " one", " two", " three",
            " four", " five", " six", " seven", " eight", " nine", " ten", " eleven",
            " twelve", " thirteen", " fourteen", " fifteen", " sixteen", " seventeen",
            " eighteen", " nineteen"};

    private static String convertBelowThousand(int integer) {
        String digitsSoFar;

        if (integer % 100 < 20)  {
            digitsSoFar = firstNineteenNames[integer % 100];
            integer /= 100;
        }

        else {
            digitsSoFar = firstNineteenNames[integer % 10];
            integer /= 10;

            digitsSoFar = tensNames[integer % 10] + digitsSoFar;
            integer /= 10;
        }
        if (integer == 0) return digitsSoFar;
        return firstNineteenNames[integer] + " hundred" + digitsSoFar;
    }


    public static String convertToWords(long integer) {
        // 0 to 999 999 999 999
        if (integer == 0) { return "zero"; }

        String sNumber;

        // pad with "0"
        String mask = "000000000000";
        DecimalFormat df = new DecimalFormat(mask);
        sNumber = df.format(integer);

        int billions = Integer.parseInt(sNumber.substring(0,3));

        int millions  = Integer.parseInt(sNumber.substring(3,6));

        int hundredThousands = Integer.parseInt(sNumber.substring(6,9));

        int thousands = Integer.parseInt(sNumber.substring(9,12));

        String tradBillions;
        if (billions == 0) {
            tradBillions = "";
        } else {
            tradBillions = convertBelowThousand(billions) + " billion ";
        }
        String result =  tradBillions;

        String tradMillions = switch (millions) {
            case 0 -> "";
            default -> convertBelowThousand(millions) + " million ";
        };

        result =  result + tradMillions;

        String tradHundredThousands = switch (hundredThousands) {
            case 0 -> "";
            case 1 -> "one thousand ";
            default -> convertBelowThousand(hundredThousands)
                    + " thousand ";
        };

        result =  result + tradHundredThousands;

        String tradThousand;
        tradThousand = convertBelowThousand(thousands);
        result =  result + tradThousand;

        // remove unwanted white spaces
        return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
    }

    public static void main(String[] args) {
        // Request integer from user
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter any number from 0 - 999999999999999: ");
        long integer = Long.parseLong(input.nextLine());
        System.out.println(integer + " in words is: " + SayTheNumber.convertToWords(integer));
    }
}
