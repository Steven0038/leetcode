package hackerank;

public class TimeConversion {
    public static void main(String[] args) {

    }

    public static String timeConversion(String s) {
        // convert to DateTime object
        String dayPart = s.substring(8, 10);
        int hour = Integer.parseInt(s.substring(0, 2));
        String middle = s.substring(2, 8);
        String sf = "%02d%s";
        if (dayPart.equals("PM") && hour < 12)
            return String.format(sf, hour + 12, middle);
        if (dayPart.equals("AM") && hour >= 12)
            return String.format(sf, hour - 12, middle);
        else
            return String.format(sf, hour, middle);

    }
}


