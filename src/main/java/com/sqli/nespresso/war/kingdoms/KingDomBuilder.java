package com.sqli.nespresso.war.kingdoms;

import java.util.ArrayList;
import java.util.Collection;

import com.sqli.nespresso.war.kingdoms.parsers.CountryParser;
import com.sqli.nespresso.war.kingdoms.parsers.DefaultCountryParser;

public final class KingDomBuilder
{
  private final CountryParser countryParser = new DefaultCountryParser();

  private String king;
  private final Collection<Country> countries = new ArrayList<>();

  public KingDomBuilder addKing(final String king)
  {
    this.king = king;
    return this;
  }

  public KingDomBuilder addCountry(final String... country)
  {
    countries.add(countryParser.parseCountry(country));
    return this;
  }

  public KingDomBuilder addSoldiersOnEdges(final String soldiersOnEdges)
  {
    countries.forEach(country -> country.setSoldiersOnEdge(Integer.valueOf(soldiersOnEdges)));
    return this;
  }

  public KingDom build()
  {
    return new KingDom(king, countries);
  }
}
