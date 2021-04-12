package by.epam.training.jwd.godot.service.util;

import java.time.LocalDate;
import java.time.Month;

public class SeasonManager {

    public static Month getCurrentMonth(){
        LocalDate currentdate = LocalDate.now();
        return currentdate.getMonth();
    }
}
