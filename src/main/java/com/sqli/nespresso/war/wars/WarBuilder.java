package com.sqli.nespresso.war.wars;

import java.util.ArrayList;
import java.util.Collection;

import com.sqli.nespresso.war.kingdoms.KingDom;
import com.sqli.nespresso.war.wars.parsers.DefaultMapParser;
import com.sqli.nespresso.war.wars.parsers.MapParser;

public final class WarBuilder
{
  private final MapParser mapParser = new DefaultMapParser();
  
  private final Collection<KingDom> kingDoms = new ArrayList<>();
  private Map map;
  
  public WarBuilder addKingDom(final KingDom kingDom)
  {
    kingDoms.add(kingDom);
    return this;
  }
  
  public WarBuilder addMap(final String mapEntries)
  {
    map = mapParser.parseMap(mapEntries);
    return this;
  }
  
  public War build()
  {
    return new War(kingDoms, map);
  }
}
