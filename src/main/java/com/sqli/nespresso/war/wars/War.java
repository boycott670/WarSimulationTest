package com.sqli.nespresso.war.wars;

import java.util.Collection;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.StreamSupport;

import com.sqli.nespresso.war.kingdoms.KingDom;

public final class War
{
  private final Collection<KingDom> kingDoms;
  private final Map map;
  
  War(Collection<KingDom> kingDoms, Map map)
  {
    this.kingDoms = kingDoms;
    this.map = map;
  }
  
  public void prepareAttack()
  {
    final KingDom powerfulKingDom = kingDoms.stream()
        .max(Comparator.naturalOrder())
        .get();
    final Predicate<MapEntry> inPowerfulKingDom = entry -> powerfulKingDom.containsCountry(entry.getCountry1())
        ^ powerfulKingDom.containsCountry(entry.getCountry2());

    final MapEntry nearestEedge = StreamSupport.stream(map.spliterator(), false)
        .filter(inPowerfulKingDom)
        .min(Comparator.naturalOrder())
        .get();

    final String countryEdge = powerfulKingDom.containsCountry(nearestEedge.getCountry1()) ? nearestEedge.getCountry1()
        : nearestEedge.getCountry2();
    
    powerfulKingDom.prepareAttack(countryEdge);
  }
}
