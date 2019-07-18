public class Round {
    public static String round(double value) {

        String textfrom = Double.toString(value);
        String[] decimal_split = textfrom.split("\\.");
        String before_decimal = decimal_split[0];

        int length = before_decimal.length();

        if (length>5) {
            double a = Math.pow(10, (length - 1));
            double new_value = value / a;
            return String.format("%.3f", new_value) + " * 10^" + (length - 1);

        }
        else if (length == 1){

            String[] new_text = textfrom.split("E");

            if(new_text.length != 1) {

                String number = new_text[0];
                String power = new_text[1];

                double number_before_decimal = Double.parseDouble(number);

                if (number_before_decimal % 1 == 0){
                    return number.format("%.0f", number_before_decimal) + " * 10^" + power;
                }


                return number.format("%.3f", number_before_decimal) + " * 10^" + power;
            }

            else{
                if (check_if_decimals_zero(value)){
                    return String.format("%.0f", value);
                }

                return String.format("%.3f", value);
            }
        }

        else{

            if (check_if_decimals_zero(value)){
                return String.format("%.0f", value);
            }

            return String.format("%.3f", value);
        }
    }

    private static boolean check_if_decimals_zero(double value){
        String a =  String.format("%.3f", value);
        String b[] = a.split("\\.");
        int c = Integer.parseInt(b[1]);
        if (c == 0)
            return true;
        else
            return false;

    }
}
