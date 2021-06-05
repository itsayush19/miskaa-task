package com.example.miskaatask;

import java.util.List;

public class Details {
    String CountryName;
    String Capital;
    String FlagUrl;
    String Region;
    String SubRegion;
    long Population;
    List<String> Borders;

    public Details(String countryName, String capital, String flagUrl, String region, String subRegion, long population, List<String> borders) {
        CountryName = countryName;
        Capital = capital;
        FlagUrl = flagUrl;
        Region = region;
        SubRegion = subRegion;
        Population = population;
        Borders = borders;
    }

    public String getCapital() {
        return Capital;
    }

    public String getFlagUrl() {
        return FlagUrl;
    }

    public String getRegion() {
        return Region;
    }

    public String getSubRegion() {
        return SubRegion;
    }

    public long getPopulation() {
        return Population;
    }

    public List<String> getBorders() {
        return Borders;
    }

    public String getCountryName() {
        return CountryName;
    }
}
