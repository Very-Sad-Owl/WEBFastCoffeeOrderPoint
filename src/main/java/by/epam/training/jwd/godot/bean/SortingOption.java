package by.epam.training.jwd.godot.bean;

import java.util.Arrays;

public enum SortingOption {
    DEFAULT(0), BY_USER(1), BY_UID(2), BY_DATE_ASC(3), BY_DATE_DESC(4);

    private final int value;

    SortingOption(int value) {
        this.value = value;
    }

    public static SortingOption valueOf(int value) {
        return Arrays.stream(values())
                .filter(legNo -> legNo.value == value)
                .findFirst().orElse(SortingOption.DEFAULT);
    }
}
