package Query;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Created by Calvin on 3/16/2017.
 */
public class DateToString {
    public static String convert(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        try {
            String formattedString = date.format(formatter);
            return formattedString;
        } catch (DateTimeParseException exc) {
            System.out.printf("%s is an invalid date!%n", date);
            throw exc;
        }
    }
}
