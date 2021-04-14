package by.epam.training.jwd.godot.bean.coffee;

import java.time.Month;

public enum SeasonType {
    WINTER("winter"), SPRING("spring"), SUMMER("summer"), AUTUMN("autumn"), ANY("any");

    private final String season;

    private SeasonType(String season){
        this.season = season;
    }

    public static SeasonType of(final Month month) {
        switch (month) {

            // Spring
            case MARCH:
                return SeasonType.SPRING;

            case APRIL:
                return SeasonType.SPRING;

            case MAY:
                return SeasonType.SUMMER;

            // Summer.
            case JUNE:
                return SeasonType.SUMMER;

            case JULY:
                return SeasonType.SUMMER;

            case AUGUST:
                return SeasonType.SUMMER;

            // Fall
            case SEPTEMBER:
                return SeasonType.AUTUMN;

            case OCTOBER:
                return SeasonType.AUTUMN;

            case NOVEMBER:
                return SeasonType.WINTER;

            // Winter.
            case DECEMBER:
                return SeasonType.WINTER;

            case JANUARY:
                return SeasonType.WINTER;

            case FEBRUARY:
                return SeasonType.WINTER;

        }
        return null;
    }


    @Override
    public String toString() {
        return season;
    }
}
