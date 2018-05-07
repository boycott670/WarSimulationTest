package com.sqli.nespresso.war.wars.parsers;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.sqli.nespresso.war.wars.Map;
import com.sqli.nespresso.war.wars.MapEntry;

public final class DefaultMapParser implements MapParser
{

  @Override
  public Map parseMap(String mapEntries)
  {
    return new Map(Arrays.stream(mapEntries.split(","))
        .map(entry ->
        {
          final String[] entryTokens = entry.split(":");
          return new MapEntry(entryTokens[0], entryTokens[2], Integer.valueOf(entryTokens[1]));
        })
        .collect(Collectors.toList()));
  }

}
