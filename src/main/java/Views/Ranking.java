package Views;

import Utils.HandleText;
import Utils.Terminal;
import Views.Campaing.Campaing;

import java.util.Scanner;
import com.mycompany.fasttyper.FastTyper;

public class Ranking {
    public static int mainRanking() {
         if(FastTyper.first || !Campaing.wonGame){
            HandleText.align(HandleText.colorText("Modo indisponível.", "red") + "\nConclua o Modo Campanha para desbloqueá-lo e... quem sabe... desfrutar das tentações que aguardam a partir daqui.", "left", true);
            Terminal.continueTo("\nPress \"Enter\" para voltar ao menu...");
            return 0;
         }
        
         Scanner input = new Scanner(System.in);
         String storyRanking ="Como posso ver, você ganhou dele, estou impressionado. Acredito que esse tenha\n" +
         "sido seu passe para vir para o torneio.\n" +
         "Aqui entram apenas aqueles que tenham bons feitos no nosso mundo. Claro, o que é bom aqui é\n" + 
         "ruim para vocês humanos, mas ainda sim, o que você esperava? Este é o torneio\n" +  
         "dos Reis; nele, você terá que lutar para ficar em primeiro no placar. O primeiro colocado é\n" + 
         "chamado de O Rei, o segundo colocado é chamado de O Príncipe, o terceiro colocado é chamado de\n" +
         "O Cavaleiro, o quarto colocado é chamado de O Carrasco e o quinto colocado é chamado de O Coringa.\n";
        
        HandleText.align(storyRanking, "left", true);
        Terminal.continueTo("\nPress \"Enter\" para prosseguir...");  
        
        storyRanking = "Para você estar entre eles, precisa ter uma pontuação e um tempo de escrita muito\n" + 
        "maior que os demais jogadores, e é claro, aqui seremos mais rigorosos, sem trapaças.\n" + 
        "Aqui terá apenas seu talento, confio em você para estar entre os primeiros. Boa sorte,\n" +
        "você irá precisar, e que Deus te acompanhe.\n";
        
        HandleText.align(storyRanking, "left", true);
        
        Terminal.continueTo("\nPress \"Enter\" para prosseguir...");
        
        String oponents = "Lista de dificuldades:\n\n" +
                "1- Facil\n" +
                "2- Medio\n" +
                "3- Dificil\n\n" + 
                "Escolha a dificuldade: ";

        HandleText.align(oponents, "left", false);
        int whichOponent = input.nextInt();
        
        Terminal.continueTo("\nPress \"Enter\" para iniciar o torneio...");  
        
        
        
        String roundFake = "ROUND DEMONSTRAÇÃO\n" + 
            "Digite o texto a seguir: \"Caminhao de bananas passou por aqui?\"\n"; 
        HandleText.align(roundFake, "left", true);
        
        String responseRound = input.nextLine();   
        
        roundFake = "A sua pontuação foi 50,550 points, vc levou 10seg para digitar o texto \"" + responseRound + "\"\n\n" +
    
        "Total Round fake\n" +
        "Player -> 50,000 pts\n";
        
        HandleText.align(roundFake, "left", true);
        
        String[] ranking = { 
            "1 - teste - 100.000pts", 
            "2 - teste - 90.000pts",
            "3 - teste - 80.000pts",
            "4 - teste - 70.000pts",
            "5 - teste - 60.000pts"
        };
        
        HandleText.align(ranking, "center", true);
        
        Terminal.continueTo("\nPress \"Enter\" para ao menu...");
        return 0;
    }
}
