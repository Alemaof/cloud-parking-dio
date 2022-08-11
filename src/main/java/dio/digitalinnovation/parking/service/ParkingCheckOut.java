package dio.digitalinnovation.parking.service;

import dio.digitalinnovation.parking.model.Parking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ParkingCheckOut {

    public static final int TOLERANCE = 20;
    public static final int ONE_HOUR = 60;
    public static final int ONE_DAY = ONE_HOUR * 24;
    public static final Double ONE_HOUR_VALUE = 10.00;
    public static final Double ADDITIONAL_HOUR_VALUE = 3.00;
    public static final Double DAY_VALUE = 40.00;



    public static Double getBill(Parking parking) {
        return getBill(parking.getEntryTime(), parking.getExitTime());
    }

    public static Double getBill(LocalDateTime entryTime, LocalDateTime exitTime) {

        long minutes = entryTime.until(exitTime, ChronoUnit.MINUTES);
        Double bill = 0.0;

        if (minutes <= TOLERANCE){

            return bill;

        }
        if (minutes <= ONE_HOUR) {

            return ONE_HOUR_VALUE;

        }
        if (minutes <= ONE_DAY) {

            bill = ONE_HOUR_VALUE;

            int hours = (int) minutes / ONE_HOUR;
            for (int i = 0; i < hours; i++) {
                bill += ADDITIONAL_HOUR_VALUE;

            }
            return bill;
        }

        int days = (int) minutes / ONE_DAY;
        for (int i = 0; i < days; i++) {
            bill += DAY_VALUE;
        }
        return bill;
    }
}
