package demo.enums;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Enum representing different types of vehicles available for rental.
 */
public enum VehicleTypesEnum {
    CAR("car"),
    MOTORCYCLE("motorcycle"),
    CARGO_VAN("cargo van");

    private final String simpleName;

    VehicleTypesEnum(String simpleName) {
        this.simpleName = simpleName;
    }

    public String getSimpleName() {
        return simpleName;
    }

    /**
     * Checks if the provided simple name exists in the VehicleTypesEnum.
     *
     * @param simpleName The simple name to check.
     * @return true if the simple name exists in the enum; false otherwise.
     */
    public static boolean contains(String simpleName) {
        Set<String> values = Arrays.stream(VehicleTypesEnum.values()).map(VehicleTypesEnum::getSimpleName).collect(Collectors.toSet());
        return values.contains(simpleName);
    }
}
