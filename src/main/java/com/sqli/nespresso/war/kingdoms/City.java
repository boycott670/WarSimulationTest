package com.sqli.nespresso.war.kingdoms;

public final class City
{
  private final int soldiers;
  private final int citizens;
  
  public City(int soldiers, int citizens)
  {
    this.soldiers = soldiers;
    this.citizens = citizens;
  }
  
  int power()
  {
    return soldiers;
  }
  
  String report()
  {
    return String.format("%d-%d", soldiers, citizens);
  }
}
