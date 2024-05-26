package Views;

import Utils.HandleText;
import Utils.Terminal;

public class Instructions {
    public static int mainInstructions() {
        
        String message = 
        "Bem vindo as introcucoes do Fast Typer\n" +
        "\n" +
        "Regras Gerais\n" +
        "1 - Escreva o texto de forma que fique identico ao texto solicitado, \n" +
        "se nao, iremos discartar tudo que escreveu a partir do primeiro \n" +
        "erro(Isso sera demostrado no seu desempenho);\n" +
        " \n" +
        "\n" +
        "Modo Ranking\n" +
        "1 - Para jogar o Modo Ranking voce precisa ter exito no modo campanha;\n" +
        "2 - Usaremos a mesma ideia da \"Regras Gerais - 1\";\n" +
        "3 - Caso voce receba uma pontuacao abaixo da anterior em sua segunda \n" +
        "tentativa, nao iremos alterar sua pontucao; \n" +
        "4 - Caso voce saia do modo Ranking seu personagem nao estara disponivel \n" +
        "para ser selecionado novamente, ou seja, aquele nome nao podera ser \n" +
        "escolhido de novo; \n" +
        "\n" +
        "Modo Campanha\n" +
        "\n" +
        "1 - Para inserir um personagem no jogo, este nunca deve ter passado pelo \n" +
        "\"Modo Ranking\";\n" +
        "2 - Usaremos a mesma ideia da \"Regras Gerais - 1\";\n" +
        "3 - Voce pode gastar o mesmo poder em diversas partidas, o mesmo serve \n" +
        "para o seu oponente;\n" +
        "4 - Caso voce perca mais de 3 vezes, voce sera removido do torneio;\n" +
        "5 - Voce pode desbloquear o Modo Ranking ao terminar o torneio com exito.";
        
        HandleText.align(message, "left", false);
        Terminal.continueTo("\nPress \"Enter\" para ao menu..."); 
        
        return 0;
    }
}
