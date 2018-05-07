package com.sqli.nespresso.war;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.sqli.nespresso.war.kingdoms.KingDom;
import com.sqli.nespresso.war.kingdoms.KingDomBuilder;

public class WarSimulationTest
{
  @Test
  public void showKingDom() {
      KingDom kingdom = new KingDomBuilder()
              .addKing("Youness")
              .addCountry("France","20","100","50","200","100","100")  //(name, nbrOfSoldiersInCity1, nbrOfCitizenInCity1, .....)
              .addCountry("Spain","30","200","40","300")
              .build();

      assertEquals("Youness:|F:<Fc1:20-100,Fc2:50-200,Fc3:100-100>, S:<Sc1:30-200,Sc2:40-300>|",kingdom.report());
      //NF : the first Lettre of the king's name + the first lettre of the CountryName
      //Fc1 : the presentation of the first city in France
  }


  /************
   every kingDom has a power it's the sum of soldiers power on all his kingdoms
   *************/

  @Test
  public void showKingDomPower() {
      KingDom kingdom = new KingDomBuilder()
              .addKing("Idriss")
              .addCountry("France","20","100","50","200","100","100")
              .addCountry("Spain","30","200","40","300")
              .build();
      assertEquals(240, kingdom.currentPower());
  }

  /************
   a kingDom have soldiers on edges of each country to protect or attack an other kingdoms
   *************/

  @Test
  public void aKingDomHaveSoldiersOnEdges() {
      KingDom kingdom1 = new KingDomBuilder()
              .addKing("Idriss")
              .addCountry("France","20","100","50","200","100","100")
              .addCountry("Spain","30","200","40","300")
              .addSoldiersOnEdges("500")
              .build();

      KingDom kingdom2 = new KingDomBuilder()
              .addKing("MOHA")
              .addCountry("USA","500","1000","400","500","200","300","2000","300")
              .build();

      assertEquals("Idriss:|F:<Fc1:20-100,Fc2:50-200,Fc3:100-100>-500, S:<Sc1:30-200,Sc2:40-300>-500|",kingdom1.report());
      assertEquals("MOHA:|U:<Uc1:500-1000,Uc2:400-500,Uc3:200-300,Uc4:2000-300>|",kingdom2.report());

      assertEquals(240, kingdom1.currentPower());
      assertEquals(3100, kingdom2.currentPower());


  }
}
