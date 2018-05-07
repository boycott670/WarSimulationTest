package com.sqli.nespresso.war.wars;

import java.util.Collection;
import java.util.Iterator;

public final class Map implements Iterable<MapEntry>
{
  private final Collection<MapEntry> entries;

  public Map(Collection<MapEntry> entries)
  {
    this.entries = entries;
  }

  @Override
  public Iterator<MapEntry> iterator()
  {
    return entries.iterator();
  }
}
