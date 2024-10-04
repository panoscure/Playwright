package utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class dateTime {

    public String dateTime() {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Add 2 minutes to the current date and time
        LocalDateTime futureDateTime = now.plusMinutes(2);

        // Define the desired date-time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        // Format the future date and time
        String formattedFutureDateTime = futureDateTime.format(formatter);

        // Print the formatted future date and time
        //Log.info("Future Date Time: " + formattedFutureDateTime);

        return formattedFutureDateTime;
    }

}
