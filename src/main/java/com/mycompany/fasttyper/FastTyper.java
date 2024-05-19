package com.mycompany.fasttyper;

import Views.Ranking.Ranking;
import Utils.AsciiArts;
import Utils.HandleText;
import Utils.Terminal;
import Views.*;
import Views.Campaing.Campaing;
import java.util.Random;


public class FastTyper { 
    public static String name = "";
    public static boolean first = true;
    public static int attempts = 0;
    public static Random random = new Random();
    
    public static void main(String[] args) {
        int i = Menu.mainMenu(-1);
        System.out.println("\n");
        boolean playing = true;
        
        do {     
            switch (i) {
                case 0 -> 
                    i = Menu.mainMenu(i);
                case 1 ->
                    i = Ranking.mainRanking();
                case 2 ->
                    i = Campaing.mainCampaing();
                default -> {
                    playing = false;
                    Terminal.cleanTerminal(0);
                    System.out.println(AsciiArts.LOGO + "\n");
                    HandleText.align("Até a proxima, volte sempre!!!");
                }
            }
        } while(playing);
        System.out.println("\n");

    }

}