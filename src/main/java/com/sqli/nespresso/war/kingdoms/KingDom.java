package com.sqli.nespresso.war.kingdoms;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public final class KingDom implements Comparable<KingDom>
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

  @Override
  public int compareTo(KingDom other)
  {
    return Comparator.comparingInt(KingDom::currentPower)
        .compare(this, other);
  }

  public boolean containsCountry(final String country)
  {
    return countries.stream()
        .map(Country::getName)
        .filter(country::equals)
        .findAny()
        .isPresent();
  }

  public void prepareAttack(final String edgeCountry)
  {
    final int armyToEdge = countries.stream()
        .filter(country -> !country.getName()
            .equals(edgeCountry))
        .mapToInt(Country::prepareAttack)
        .sum();

    countries.stream()
        .filter(country -> country.getName()
            .equals(edgeCountry))
        .findFirst()
        .get()
        .incrementSoldiersOnEdge(armyToEdge);
  }

  public void looseSoldiersOnEdge(final String edgeCountry, final int soldiersToLoose)
  {
    countries.stream()
        .filter(country -> country.getName()
            .equals(edgeCountry))
        .findFirst()
        .get()
        .incrementSoldiersOnEdge(-soldiersToLoose);
  }

  public int soldiersOnEdge(final String edgeCountry)
  {
    return countries.stream()
        .filter(country -> country.getName()
            .equals(edgeCountry))
        .findFirst()
        .get()
        .getSoldiersOnEdge();
  }

  public String peopleMood()
  {
    return countries.stream()
        .mapToInt(Country::getSoldiersOnEdge)
        .filter(soldiers -> soldiers > 0)
        .findAny()
        .isPresent() ? "GOOD" : "BAD";
  }
}
