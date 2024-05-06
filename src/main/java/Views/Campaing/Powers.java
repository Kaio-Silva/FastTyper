package Views.Campaing;

import Utils.HandleText;

public class Powers {
    public static String[][] listPlayerPowers = {{
        "",
        "1 Aumentar o tempo inimigo" + HandleText.colorText("(+5seg)", "green"),
        "2 Diminuir o seu tempo" + HandleText.colorText("(-2seg)", "green"),
        "3 Travar o terminal inimigo" + HandleText.colorText("(+30seg)", "green")
    }, {"0", "5", "-2", "20"}};
    
    public static String[][] listOpponentsPowers = {{
        "",
        "1 - Poder fraco" +  HandleText.colorText("(+2seg)", "red"),
        "2 - Poder medio" +  HandleText.colorText("(+4seg)", "red"),
        "3 - Poder roubado" + HandleText.colorText("(+30seg)", "red")
    }, {"0", "2", "4", "30"}};
}
