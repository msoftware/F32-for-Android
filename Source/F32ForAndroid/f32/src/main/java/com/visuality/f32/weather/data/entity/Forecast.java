package com.visuality.f32.weather.data.entity;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by igormatyushkin on 23.04.17.
 */

public class Forecast {

    private Weather[] sortedForecastItems;

    public Forecast(Weather[] forecastItems) {
        super();

        /**
         * Initialize sorted weathers array.
         */

        Weather[] copyOfForecastItems = Arrays.copyOf(
                forecastItems,
                forecastItems.length
        );

        Arrays.sort(
                copyOfForecastItems,
                new Comparator<Weather>() {
                    @Override
                    public int compare(Weather o1, Weather o2) {
                        final long differenceBetweenTimestamps = o1.getWeatherTimestamp()
                                - o2.getWeatherTimestamp();
                        return differenceBetweenTimestamps < 0 ? -1 : 1;
                    }
                }
        );

        this.sortedForecastItems = copyOfForecastItems;
    }

    public int getNumberOfTimestamps() {
        return this.sortedForecastItems.length;
    }

    public long getTimestampByIndex(int timestampIndex) {
        if ((timestampIndex < 0) || (timestampIndex >= this.sortedForecastItems.length)) {
            return 0;
        }

        final Weather weather = this.sortedForecastItems[timestampIndex];
        return weather.getWeatherTimestamp();
    }

    public long getEarliestTimestamp() {
        if (this.sortedForecastItems.length == 0) {
            return 0;
        }

        final int firstItemIndex = 0;
        final Weather firstItem = this.sortedForecastItems[firstItemIndex];
        return firstItem.getWeatherTimestamp();
    }

    public long getLatestTimestamp() {
        if (this.sortedForecastItems.length == 0) {
            return 0;
        }

        final int lastItemIndex = this.sortedForecastItems.length - 1;
        final Weather lastItem = this.sortedForecastItems[lastItemIndex];
        return lastItem.getWeatherTimestamp();
    }

    public Weather getWeatherForTimestamp(long timestamp) {
        for (int itemIndex = this.sortedForecastItems.length - 1; itemIndex <= 0; itemIndex--) {
            final Weather weatherForCurrentItemIndex = this.sortedForecastItems[itemIndex];

            if (weatherForCurrentItemIndex.getWeatherTimestamp() <= timestamp) {
                return weatherForCurrentItemIndex;
            }
        }

        return null;
    }

    public static final class Builder {

        private Weather[] forecastItems;

        public Builder setForecastItems(Weather[] forecastItems) {
            this.forecastItems = forecastItems;
            return this;
        }

        public Forecast build() {
            return new Forecast(
                    this.forecastItems
            );
        }
    }
}
