package Views.Ranking;

import Utils.HandleInputs;
import Utils.HandleText;
import Utils.HandleType;
import Utils.Terminal;
import com.mycompany.fasttyper.FastTyper;

public class Match {
    public static void mainMatch(){
        boolean wannaPlay = true;
        
        do {
            String difficultyLevel = "Lista de dificuldades:\n\n" + "1- Facil\n" + "2- Medio\n" + "3- Dificil\n\n";
            HandleText.align(difficultyLevel, "left", false);

            int whichLevel = HandleInputs.getIntInput("Escolha a dificuldade: ", "Ops, você inseriu uma dificuldade invalida!!!\nInsira novamente: ", 3);
            Terminal.continueTo("\nPress \"Enter\" para iniciar a partida...");  

            handleMatch(whichLevel);

            HandleText.align("Você deseja jogar novamente? Sim/Nao", "left", true);
            String whichOption = HandleInputs.getTextInput("Ops, você inseriu uma reposta invalida!!!", whichLevel != 0);

            if(whichOption.equals("nao")){
                wannaPlay = false;
            } else {
                Terminal.continueTo("\nPress \"Enter\" para jogar novamente...");
            }
        
        } while(wannaPlay);
        
        Terminal.continueTo("\nPress \"Enter\" para ao menu...");
    }
    
    public static void handleMatch(int whichLevel){
        HandleText.align("MODO RANKING", "center", true);
        
        String whatType = Utils.TextsToBeTyped.RankingLevels[whichLevel][FastTyper.random.nextInt(0,5)];
        
        double[] performance = HandleType.mainType(whichLevel * 10, whatType);
        Database.Db.insertPlayer(FastTyper.name, performance[0]);
        
        String resp = "Player:\n Pontos: " + performance[0] + "\nTempo: " + performance[1] + "Posição: " + Database.Db.getPlayerPos(FastTyper.name);
        HandleText.align(resp, whatType, true);
        
        String[] ranking = Database.Db.getRank();
        HandleText.align(ranking, "center", true);
    }
}
