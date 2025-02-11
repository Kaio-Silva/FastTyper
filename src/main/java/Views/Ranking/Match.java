package Views.Ranking;

import Utils.HandleInputs;
import Utils.HandleText;
import Utils.HandleType;
import Utils.Terminal;
import com.mycompany.fasttyper.FastTyper;

public class Match {
    static double points = 0;
    public static void mainMatch(){
        boolean wannaPlay = true;

        do {
            Terminal.continueTo("\nPress \"Enter\" para iniciar a partida...");  
            handleMatch();

            HandleText.align("Você deseja jogar novamente? Sim/Nao", "left", true);
            String whichOption = HandleInputs.getTextInput("Ops, você inseriu uma reposta invalida!!!");

            if(whichOption.equals("nao")){
                wannaPlay = false;
            } else {
                Terminal.continueTo("\nPress \"Enter\" para jogar novamente...");
            }
        
        } while(wannaPlay);
        
        Terminal.continueTo("\nPress \"Enter\" para ao menu...");
    }
    
    public static void handleMatch(){
        HandleText.align("MODO RANKING", "center", true);
        
        String whatType = Utils.TextsToBeTyped.RankingLevels[FastTyper.random.nextInt(0,5)];
        
        double[] performance = HandleType.mainType(100, whatType);
       

        if(Database.Db.getPlayerPos(FastTyper.name) == 0)
            Database.Db.insertPlayer(FastTyper.name, performance[0]);
        else {
            if(points < performance[0])
                Database.Db.updateByName(FastTyper.name, performance[0]);
        }
        
        String resp = "Player:\nSeu Desempenho: " + HandleType.wrongsTyped + "\nPontos: " + performance[0] + "\nTempo: " + performance[1] + "\nPosição: " + Database.Db.getPlayerPos(FastTyper.name);
        HandleText.align(resp, "left", true);
        
        Database.Db.getRank();
        points = performance[0];
    }
}
