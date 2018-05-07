package com.sqli.nespresso.war.wars;

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.StreamSupport;

import com.sqli.nespresso.war.kingdoms.KingDom;

public final class War
{
  private final Collection<KingDom> kingDoms;
  private final Map map;

  private KingDom powerfulKingDom;
  private KingDom againstPowerfulKingDom;

  private String powerfulKingDomEdgeCountry;
  private String againstPowerfulKingDomEdgeCountry;

  War(Collection<KingDom> kingDoms, Map map)
  {
    this.kingDoms = kingDoms;
    this.map = map;
  }

  public void prepareAttack()
  {
    powerfulKingDom = kingDoms.stream()
        .max(Comparator.naturalOrder())
        .get();

    final Predicate<MapEntry> inPowerfulKingDom = entry -> powerfulKingDom.containsCountry(entry.getCountry1())
        ^ powerfulKingDom.containsCountry(entry.getCountry2());

    final MapEntry nearestEedge = StreamSupport.stream(map.spliterator(), false)
        .filter(inPowerfulKingDom)
        .min(Comparator.naturalOrder())
        .get();

    powerfulKingDomEdgeCountry = powerfulKingDom.containsCountry(nearestEedge.getCountry1())
        ? nearestEedge.getCountry1()
        : nearestEedge.getCountry2();

    againstPowerfulKingDomEdgeCountry = Objects.equals(powerfulKingDomEdgeCountry, nearestEedge.getCountry1())
        ? nearestEedge.getCountry2()
        : nearestEedge.getCountry1();

    againstPowerfulKingDom = kingDoms.stream()
        .filter(kingDom -> kingDom.containsCountry(againstPowerfulKingDomEdgeCountry))
        .findFirst()
        .get();

    powerfulKingDom.prepareAttack(powerfulKingDomEdgeCountry);
  }

  public void start()
  {
    final int powerfulKingDomEdgeCountrySoldiersOnEdge = powerfulKingDom.soldiersOnEdge(powerfulKingDomEdgeCountry);

    powerfulKingDom.looseSoldiersOnEdge(powerfulKingDomEdgeCountry,
        againstPowerfulKingDom.soldiersOnEdge(againstPowerfulKingDomEdgeCountry));

    againstPowerfulKingDom.looseSoldiersOnEdge(againstPowerfulKingDomEdgeCountry,
        powerfulKingDomEdgeCountrySoldiersOnEdge);
  }
}
