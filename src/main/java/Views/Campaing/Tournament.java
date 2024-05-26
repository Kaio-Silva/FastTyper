package Views.Campaing;

import Utils.HandleInputs;
import Utils.HandleText;
import Utils.HandleType;
import Utils.Terminal;
import Utils.TextsToBeTyped;
import com.mycompany.fasttyper.FastTyper;

public class Tournament {
    public static String wrongsTypedOpponent = "";
    static int whichPower = 0;
    
    public static boolean mainTournament(boolean gameOver) {
        String[][] listPlayerPowers = new String[3][3];
        String[][] listOpponentsPowers = {{
            "Nenhum poder selecionado",
            "1 - Poder fraco" +  HandleText.colorText("(+2seg)", "red"),
            "2 - Poder medio" +  HandleText.colorText("(+4seg)", "red"),
            "3 - Poder roubado" + HandleText.colorText("(+30seg)", "red")
        }, {"0", "2", "4", "30"}};

        String playerSignature = "";
        int qtdPowersUser = 0;
        int i = 1;   
       
        do {
            Campaing.tournamentStarted = true;
            String whichOpponent = "";

            switch(i){
                case 1 -> whichOpponent = "Avareza"; 
                case 2 -> whichOpponent = "Gula"; 
                case 3 -> {
                    if(listPlayerPowers[0][0] == null){
                        qtdPowersUser++;
                        listPlayerPowers[0][0] = "Aumentar o tempo inimigo" + HandleText.colorText("(+5seg)", "green"); 
                        listPlayerPowers[1][0] = "5";
                    }
                        
                    whichOpponent = "Inveja";   
                }
                case 4 -> whichOpponent = "ira"; 
                case 5 -> {
                    if(listPlayerPowers[0][1] == null){
                        qtdPowersUser++;
                        listPlayerPowers[0][1] = "Diminuir o seu tempo" + HandleText.colorText("(-2seg)", "green"); 
                        listPlayerPowers[1][1] = "-2";
                    }
                    
                    whichOpponent = "luxúria"; 
                }
                case 6 -> whichOpponent = "Preguiça"; 
                case 7 -> {
                     if(listPlayerPowers[0][2] == null){
                        qtdPowersUser++;
                        listPlayerPowers[0][2] = "Travar o terminal inimigo" + HandleText.colorText("(+30seg)", "green");
                        listPlayerPowers[1][2] = "30";
                    }
                    whichOpponent = "Morte"; 
                }
                default -> whichOpponent = "Rei do Submundo"; 
            }
            
            HandleText.align(String.format(StoryTexts.story[i][0], FastTyper.name), "left", true);
            
            if((i > 7 && playerSignature.isEmpty()) && FastTyper.first){
                Terminal.continueTo("\nPress \"Enter\" para ver o contrato...");
                HandleText.align(StoryTexts.agreement, "left", false); 
                playerSignature = HandleInputs.getTextInput("", FastTyper.name + ", NÃO TENTE ME ENGANAR!!!\nAssine o seu nome no contrato: ",  FastTyper.name, whichPower != 0); 
            }
            
            Terminal.continueTo("\nPress \"Enter\" para iniciar a partida..."); 
            
            double[][] performance = handleRound(i, Campaing.lifes, whichOpponent, listPlayerPowers, listOpponentsPowers, playerSignature, qtdPowersUser);
           

            String winner = whoWin(i, performance, whichOpponent, gameOver);

            gameOver = !winner.equals(FastTyper.name) && Campaing.lifes == -1 ? true : false;

            HandleText.align("\n\nVencedor: " + winner, "left", "green");
            
            if(winner.equals(FastTyper.name)){
                if(i == 7) {
                    HandleText.align("\nA Morte lhe entregou uma alma como recompensa" + HandleText.colorText("(+1 Vida)", "green"));
                    Campaing.lifes++;
                }
                i++;
            }
                
            if(i > 8 || gameOver){
                qtdPowersUser = 0;
                Terminal.continueTo("\nPress \"Enter\" para sair do torneio...");  // I guess, it ain't necessary if the player win the game and play again!!!
            } else {
                Terminal.continueTo("\nPress \"Enter\" para continuar...");
            }
            
            HandleType.wrongsTyped = "";
            wrongsTypedOpponent = "";
            
        } while(i <= 8 && !gameOver);  

        return gameOver;
    }
    


    
    public static double[][] handleRound(int i, int lifes, String whichOpponent, String[][] listPlayerPowers, String[][] listOpponentsPowers, String playerSignature, int qtdPowersUser) {
            HandleText.align("\nROUND " + i + "\nVidas Extras " + "♥".repeat(lifes), "left", false);
            
            String usePower = "";
            
            if(qtdPowersUser > 0){
                HandleText.align("\n\nVocê gostaria de usar algum dos seus poderes? " + HandleText.colorText("Sim", "green") + " / " + 
                                 HandleText.colorText("Nao", "red") + "\n", "left", false);     
                
                
                boolean condition = whichPower != 0 && playerSignature.isEmpty();
                
//                if(!condition && !FastTyper.first){
//                    condition = true;
//                }
                
                usePower = HandleInputs.getTextInput("Ops, você inseriu uma opção invalida!!!\nInsira novamente: ", condition);
            }
            
            
            if(usePower.equalsIgnoreCase("sim")){
                HandleText.align(listPlayerPowers[0], "left", true);
                whichPower = HandleInputs.getIntInput("Escolha qual você ira usar: ", qtdPowersUser);
            } else if(usePower.equalsIgnoreCase("nao")){
                whichPower = 0; 
            } 
            
            int pointsWord = i * 10;
            
            String whatType = TextsToBeTyped.fases[i - 1][FastTyper.random.nextInt(0,5)]; 
            double[] round = HandleType.mainType(pointsWord, whatType); 
            
            int powerOpponent = FastTyper.random.nextInt(0, 2); //pointsWord * 30
            double[] roundOpponent = HandleType.mainType(0, whatType, pointsWord / 100,  pointsWord * 2, i);
            
            double[][] newPerformance = HandleType.reCalculatePoints(round, roundOpponent, whichPower == 0 ? "0" : listPlayerPowers[1][whichPower - 1],  listOpponentsPowers[1][powerOpponent]); 
         
            String powerPlayer = usePower.equalsIgnoreCase("nao") || usePower.isEmpty() ? "Nenhum poder selecionado" : listPlayerPowers[0][whichPower - 1];
            
            HandleText.align("Total round " + i + "\n" + "texto do round: \"" + whatType + "\"\n\n" + 
                    "Jogador:\n" + 
                    "Seu Desempenho: " + HandleType.wrongsTyped + "\n" + 
                    "Magia Utilizada: " + powerPlayer + "\n" + 
                    "Magia do Oponente: " + listOpponentsPowers[0][powerOpponent] + "\n" + 
                    "Tempo: " + newPerformance[0][1] + " segundos\n" + 
                    "Pontos: " + newPerformance[0][0] + " pontos\n\n" + 
                    whichOpponent + ":\n" + 
                    "Desempenho do Oponente: " + wrongsTypedOpponent + "\n" +
                    "Magia Utilizada: " + listOpponentsPowers[0][powerOpponent] + "\n" + 
                    "Magia do Oponente: " + powerPlayer + "\n" + 
                    "Tempo: " + newPerformance[1][1] + " segundos\n" + 
                    "Pontos: " + newPerformance[1][0] + " pontos", "left", true);

            return newPerformance;
    }

    
    
    
    public static String whoWin(int i, double[][] performance, String whichOpponent, boolean gameOver){
        String messageEnd = "";

            String winner;
            if(performance[0][0] > performance[1][0]){ 
                messageEnd = String.format(StoryTexts.story[i][2], FastTyper.name);
                HandleText.align(messageEnd, "left", true);
                winner = FastTyper.name;
            } else {     
                if(Campaing.lifes >= 1) {
                    messageEnd = String.format(StoryTexts.story[i][1], FastTyper.name);
                } else {
                    messageEnd = String.format(StoryTexts.story[i][3], FastTyper.name);
                }
                
                HandleText.align(messageEnd, "left", true);
                winner = whichOpponent;
                Campaing.lifes--;
            }

            return winner;
    }
   
}
