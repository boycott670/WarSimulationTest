package com.sqli.nespresso.war.kingdoms;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Country
{
  private final String name; 
  private final List<City> cities;
  private Integer soldiers;
  
  public Country(String name, List<City> cities)
  {
    this.name = name;
    this.cities = cities;
  }
  
  void setSoldiersOnEdge(final int soldiersOnEdge)
  {
    soldiers = soldiersOnEdge;
  }
  
  void incrementSoldiersOnEdge(final int soldiersOnEdge)
  {
    setSoldiersOnEdge(soldiers + soldiersOnEdge);
  }

  int power()
  {
    return cities.stream()
        .mapToInt(City::power)
        .sum();
  }
  
  String report()
  {
    return String.format("%c:<%s>%s", name.charAt(0), IntStream.range(0, cities.size())
        .mapToObj(index -> String.format("%cc%d:%s", name.charAt(0), index + 1, cities.get(index)
            .report()))
        .collect(Collectors.joining(",")),
        Optional.ofNullable(soldiers)
            .map(soldiers -> String.format("-%d", soldiers))
            .orElse(""));
  }

  String getName()
  {
    return name;
  }
  
  int prepareAttack()
  {
    return cities.stream()
        .mapToInt(City::prepareAttack)
        .sum();
  }
}
