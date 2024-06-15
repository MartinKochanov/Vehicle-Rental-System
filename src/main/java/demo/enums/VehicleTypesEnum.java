package demo.enums;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

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

    public static boolean contains(String simpleName) {
        Set<String> values = Arrays.stream(VehicleTypesEnum.values()).map(VehicleTypesEnum::getSimpleName).collect(Collectors.toSet());
        return values.contains(simpleName);
    }
}
