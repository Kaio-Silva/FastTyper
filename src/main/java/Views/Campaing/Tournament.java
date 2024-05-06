package Views.Campaing;

import java.util.Random;

import Utils.HandleInputs;
import Utils.HandleText;
import Utils.HandleType;
import Utils.Terminal;
import Utils.TextsToBeTyped;
import com.mycompany.fasttyper.FastTyper;

public class Tournament {
    static int whichPower = 0;
    static int powerOpponent = 0;
    static Random random = new Random();

    public static boolean mainTournament() {
        boolean gameOver = false;
        String playerSignature = "";
        
        int lifes = 3;
        int i = 1;

        do {
            Campaing.tournamentStarted = true;
            String whichOpponent = "";

            switch(i){
                case 1 -> whichOpponent = "Avareza"; 
                case 2 -> whichOpponent = "Gula"; 
                case 3 -> whichOpponent = "Inveja"; 
                case 4 -> whichOpponent = "ira"; 
                case 5 -> whichOpponent = "luxúria"; 
                case 6 -> whichOpponent = "Preguiça"; 
                case 7 -> whichOpponent = "Morte"; 
                default -> whichOpponent = "Rei do Submundo"; 
            }
            
            HandleText.align(String.format(StoryTexts.story[i][0], FastTyper.name), "left", true);

            if(i > 7 && playerSignature.isEmpty()){
                Terminal.continueTo("\nPress \"Enter\" para ver o contrato...");
                HandleText.align(StoryTexts.agreement, "left", false);
                playerSignature = HandleInputs.getTextInput(FastTyper.name + ", NÃO TENTE ME ENGANAR!!!\nAssine o seu nome no contrato: ",  FastTyper.name); 
            }
          
            Terminal.continueTo("\nPress \"Enter\" para iniciar a partida...");
            double[][] performance = handleRound(i, lifes, whichOpponent);
            
            String winner = whoWin(i, lifes, performance, whichOpponent, gameOver);
            HandleText.align("\n\nVencedor: " + winner, "left", "green");
            
            if(winner.equals(FastTyper.name))
                i++;
                
            if(i == 8){
                Terminal.continueTo("\nPress \"Enter\" para sair do torneio...");
            } else {
                Terminal.continueTo("\nPress \"Enter\" para continuar...");
            }
            
        } while(i < 8 && !gameOver);  
        
        return gameOver;
    }


    public static double[][] handleRound(int i, int lifes, String whichOpponent) {
            HandleText.align("\nROUND " + i + "\nSuas vidas " + "♥".repeat(lifes) +
                            "\n\nVOCÊ QUER GASTAR ALGUM PODER: " + HandleText.colorText("Sim", "green") + " / " + 
                            HandleText.colorText("Nao", "red") + "\n", "left", false);
            
            
            String usePower = HandleInputs.getTextInput("Ops, você inseriu uma opção invalida!!!\nInsira novamente: ", whichPower != 0);
            
            if(usePower.equalsIgnoreCase("sim")){
                HandleText.align(Powers.listPlayerPowers[0], "left", true);
                whichPower = HandleInputs.getIntInput("Escolha qual você ira usar: ", 3);
            } else {
                whichPower = 0; 
            } 

            String whatType = TextsToBeTyped.fases[i][random.nextInt(0,5)]; 
            double[] round = HandleType.mainType(10, whatType);
            
            powerOpponent = random.nextInt(0, 2); 
            double[] roundOpponent = HandleType.mainType(0, whatType, 5, 10);
            
            double[][] newPerformance = HandleType.reCalculatePoints(round, roundOpponent, Powers.listPlayerPowers[1][whichPower],  Powers.listOpponentsPowers[1][powerOpponent]); 
            
            HandleText.align("\nCALCULANDO DESEMPENHO...\n", "left", "yellow");
            Terminal.cleanTerminal(3000);
            
            HandleText.align("Total round " + i + "\n" + "texto do round: \"" + whatType + "\"\n\n" + 
                    "Jogador:\n" + 
                    "Magia Utilizada: " + Powers.listPlayerPowers[0][whichPower] + "\n" + "Magia do Oponente: " + Powers.listOpponentsPowers[0][powerOpponent] + "\n" + 
                    "Tempo: " + newPerformance[0][1] + " segundos\n" + "Pontos: " + newPerformance[0][0] + " pontos\n\n" + 
                    whichOpponent + ":\n" + 
                    "Magia Utilizada: " + Powers.listOpponentsPowers[0][powerOpponent] + "\n" + "Magia do Oponente: " + Powers.listPlayerPowers[0][whichPower]  + "\n" + 
                    "Tempo: " + newPerformance[1][1] + " segundos\n" + "Pontos: " + newPerformance[1][0] + " pontos", "left", true);

            return newPerformance;
    }

    public static String whoWin(int i, int lifes, double[][] performance, String whichOpponent, boolean gameOver){
        String messageEnd = "";

            String winner;
            if(performance[0][0] > performance[1][0]){ 
                messageEnd = String.format(StoryTexts.story[i][2], FastTyper.name);
                HandleText.align(messageEnd, "left", true);
                winner = FastTyper.name;
            } else {     
                if(lifes >= 1) {
                    messageEnd = String.format(StoryTexts.story[i][1], FastTyper.name);
                } else {
                    messageEnd = String.format(StoryTexts.story[i][3], FastTyper.name);
                    gameOver = true;
                }
                
                HandleText.align(messageEnd, "left", true);
                winner = whichOpponent;
                lifes--;
            }

            
            return winner;
    }
}
