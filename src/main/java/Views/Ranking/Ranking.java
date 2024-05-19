package Views.Ranking;

import Utils.HandleInputs;
import Utils.HandleText;
import Utils.Terminal;
import Views.Campaing.Campaing;
import com.mycompany.fasttyper.FastTyper;

public class Ranking {
    public static int mainRanking() {
        if(FastTyper.first || !Campaing.wonGame){
           HandleText.align(HandleText.colorText("Modo indisponível.", "red") + "\nConclua o Modo Campanha para desbloqueá-lo e... quem sabe... desfrutar das tentações que aguardam a partir daqui.", "left", true);
           Terminal.continueTo("\nPress \"Enter\" para voltar ao menu...");
           return 0;
        }
         
        for(int i = 0; i < StoryRanking.story.length; i++){
            HandleText.align(StoryRanking.story[i], "left", true);
            Terminal.continueTo("\nPress \"Enter\" para prosseguir..."); 
        }
        
        Match.mainMatch();
        
        return 0;
    }
}
