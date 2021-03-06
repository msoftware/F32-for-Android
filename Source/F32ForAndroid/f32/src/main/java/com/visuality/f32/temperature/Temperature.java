package com.visuality.f32.temperature;

/**
 * Created by igormatyushkin on 17.04.17.
 */

public class Temperature {

    private double value;

    private TemperatureUnit unit;

    public Temperature(
            double value,
            TemperatureUnit unit
    ) {
        super();

        /**
         * Initialize value.
         */

        this.value = value;

        /**
         * Initialize unit.
         */

        this.unit = unit;
    }

    public double getValue(TemperatureUnit targetUnit) {
        switch (this.unit) {
            case KELVIN:
                switch (targetUnit) {
                    case KELVIN:
                        return this.value;
                    case CELCIUS:
                        return this.value - 273.15;
                    case FAHRENHEIT:
                        return (this.value * 1.8) - 459.67;
                    default:
                        return this.value;
                }
            case CELCIUS:
                switch (targetUnit) {
                    case KELVIN:
                        return this.value + 273.15;
                    case CELCIUS:
                        return this.value;
                    case FAHRENHEIT:
                        return (this.value * 1.8) + 32.0;
                    default:
                        return this.value;
                }
            case FAHRENHEIT:
                switch (targetUnit) {
                    case KELVIN:
                        return (this.value + 459.67) * 1.8;
                    case CELCIUS:
                        return (this.value - 32.0) / 1.8;
                    case FAHRENHEIT:
                        return this.value;
                    default:
                        return this.value;
                }
            default:
                return this.value;
        }
    }
}
