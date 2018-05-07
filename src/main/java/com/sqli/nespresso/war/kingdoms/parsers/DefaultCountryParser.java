package com.sqli.nespresso.war.kingdoms.parsers;

import java.util.ArrayList;
import java.util.List;

import com.sqli.nespresso.war.kingdoms.City;
import com.sqli.nespresso.war.kingdoms.Country;

public final class DefaultCountryParser implements CountryParser
{

  @Override
  public Country parseCountry(String[] tokens)
  {
    final String countryName = tokens[0];

    final List<City> countryCities = new ArrayList<>();

    for (int index = 1; index < tokens.length - 1; index += 2)
    {
      countryCities.add(new City(Integer.valueOf(tokens[index]), Integer.valueOf(tokens[index + 1])));
    }

    return new Country(countryName, countryCities);
  }

}
