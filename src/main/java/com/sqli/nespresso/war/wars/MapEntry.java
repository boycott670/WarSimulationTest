package com.sqli.nespresso.war.wars;

import java.util.Comparator;

public final class MapEntry implements Comparable<MapEntry>
{
  private final String country1;
  private final String country2;
  private final int distance;

  public MapEntry(String country1, String country2, int distance)
  {
    this.country1 = country1;
    this.country2 = country2;
    this.distance = distance;
  }

  final String getCountry1()
  {
    return country1;
  }

  final String getCountry2()
  {
    return country2;
  }

  final int getDistance()
  {
    return distance;
  }

  @Override
  public int compareTo(MapEntry other)
  {
    return Comparator.comparing(MapEntry::getDistance)
        .compare(this, other);
  }
}
