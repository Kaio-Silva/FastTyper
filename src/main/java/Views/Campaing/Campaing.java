package Views.Campaing;

import Utils.*;
import com.mycompany.fasttyper.FastTyper;


public class Campaing {
    public static boolean winGame = false;
    public static boolean tournamentStarted = false;
    
    public static int mainCampaing() {        
        if(!FastTyper.first){
            StoryTexts.story[0][0] = "Bem vindo de volta, %s! Parece que você não aprendeu sua lição... Então, vamos recomeçar tudo!";
        }
        
        for(int i = 0; i < 3; i++){
            HandleText.align(String.format(StoryTexts.story[0][i], FastTyper.name), "left");
            FastTyper.name = FastTyper.name.isEmpty() ? HandleInputs.getTextInput() : FastTyper.name;  
            Terminal.continueTo("\nPress \"Enter\" para continuar....");
        }
        
        boolean lostTournament = Tournament.mainTournament();
        
        if(lostTournament)
            HandleText.align("\n------- GAME OVER -------\n", "center", "red");
        
        if(!winGame && !lostTournament){
            HandleText.align("\n### MODO RANKING DESBLOQUEADO ###\n", "center", "green");
            winGame = true;
        }
           
         Terminal.continueTo("\nPress \"Enter\" para ao menu...");
         FastTyper.first = false;
         tournamentStarted = false;
         
         return 0;
    }
}
