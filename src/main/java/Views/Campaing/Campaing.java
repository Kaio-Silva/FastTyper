package Views.Campaing;

import Utils.*;
import com.mycompany.fasttyper.FastTyper;

public class Campaing {
    public static boolean wonGame = false;
    public static boolean tournamentStarted = false;
    static int lifes = 3;

    public static int mainCampaing() {  
        boolean gameOver = false;

        if(!FastTyper.first){
            StoryTexts.story[0][0] = "Bem vindo de volta, %s! Parece que você não aprendeu sua lição... Então, vamos recomeçar tudo!";
        }
         char[] animationChars = new char[]{'|', '/', '-', '\\'};
        
        for(int i = 0; i < 3; i++){
            HandleText.align(String.format(StoryTexts.story[0][i], FastTyper.name), "left");
            FastTyper.name = FastTyper.name.isEmpty() ? HandleInputs.VerifyUserExist() : FastTyper.name;  
            System.out.println();
            for (int x = 0; x <= 100; x++) {
                Terminal.cleanTerminal(0);
                System.out.print("\r" + animationChars[x % 4] + " " + x + "%");
                try{
                    Thread.sleep(25);
                } catch (Exception e){
                    System.out.println("Erro ao carregar! " + e);
                }
                
            }
            System.out.println();
            Terminal.continueTo("\nPress \"Enter\" para continuar...");
        }
        
        gameOver = Tournament.mainTournament(gameOver);
        FastTyper.first = false;
        tournamentStarted = false;
        Tournament.whichPower = 0;
        lifes = 3;
         
        if(gameOver)
            HandleText.align("\n------- GAME OVER -------\n", "center", "red");
        
        if(!wonGame && !gameOver){
            HandleText.align("\n### MODO RANKING DESBLOQUEADO ###\n", "center", "green");
            wonGame = true;
        }
           
        Terminal.continueTo("\nPress \"Enter\" para ao menu..."); 
        
        return 0;
    }
}
