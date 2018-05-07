package com.sqli.nespresso.war.kingdoms.parsers;

import com.sqli.nespresso.war.kingdoms.Country;

public interface CountryParser
{
  Country parseCountry(final String[] tokens);
}
