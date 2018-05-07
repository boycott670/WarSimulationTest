package com.sqli.nespresso.war.kingdoms;

import java.util.Collection;
import java.util.stream.Collectors;

public final class KingDom
{
  private final String king;
  private final Collection<Country> countries;
  
  KingDom(String king, Collection<Country> countries)
  {
    this.king = king;
    this.countries = countries;
  }
  
  public int currentPower()
  {
    return countries.stream()
        .mapToInt(Country::power)
        .sum();
  }
  
  public String report()
  {
    return String.format("%s:|%s|", king, countries.stream()
        .map(Country::report)
        .collect(Collectors.joining(", ")));
  }
}
