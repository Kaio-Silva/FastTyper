package Utils;

public class HandleText {

	public static int globalWidth = 45;

	public static void align(String text) {
		align(text, "center", true, "");
	}
        
	public static void align(String[] texts, String alignment, boolean ln){
                
		for(int i = 0; i < texts.length; i++){   
                    align(texts[i] == null ? "" : (i + 1) + " - " + texts[i], alignment, ln, "");
		}
	}

	public static void align(String text, String alignment) {
		align(text, alignment, true, "");
	}

	public static void align(String text, String alignment, String color) {
		align(text, alignment, true, color);
	}

	public static void align(String text, String alignment, boolean ln) {
		align(text, alignment, true, "");
	}

	public static void align(String text, String alignment, boolean ln, String color) {
                if(text.isEmpty()) {
                    System.out.print("");
                } else {
                    int width = globalWidth;
                    switch (alignment) {
                            case "center" -> width *= 1;
                            case "right" -> width *= 1.2;
                            case "left" -> width *= 0;
                            default -> width *= 1;
                    }
                    String space = " ";
                    String transformText = space.repeat((alignment != "left") ? width - text.length() / 2 : width / 2) + text;

                    if(!color.isEmpty())
                            transformText = colorText(transformText, color);

                    if (ln == true)
                            System.out.println(transformText);
                    else
                            System.out.print(transformText);
                }

	}
	public static String colorText(String text,String color) {
		final String ANSI_RESET = "\u001B[0m";
		final String ANSI_BLACK = "\u001B[30m";
		final String ANSI_RED = "\u001B[31m";
		final String ANSI_GREEN = "\u001B[32m";
		final String ANSI_YELLOW = "\u001B[33m";
		final String ANSI_BLUE = "\u001B[34m";
		final String ANSI_PURPLE = "\u001B[35m";
		final String ANSI_CYAN = "\u001B[36m";
		final String ANSI_WHITE = "\u001B[37m";
		String finalText = "";
		switch(color){
			case "black" -> finalText = ANSI_BLACK + text + ANSI_RESET;
			case "red" -> finalText = ANSI_RED + text + ANSI_RESET;
			case "green" -> finalText = ANSI_GREEN + text + ANSI_RESET;
			case "yellow" -> finalText = ANSI_YELLOW + text + ANSI_RESET;
			case "blue" -> finalText = ANSI_BLUE + text + ANSI_RESET;
			case "purple" -> finalText = ANSI_PURPLE + text + ANSI_RESET;
			case "cyan" -> finalText = ANSI_CYAN + text + ANSI_RESET;
			case "white" -> finalText = ANSI_WHITE + text + ANSI_RESET;
			default -> color = "white";
		}

		return finalText;
	}

	public static String colorText(String text,String color,String background) {
		final String ANSI_RESET = "\u001B[0m";
		final String ANSI_BLACK = "\u001B[30m";
		final String ANSI_RED = "\u001B[31m";
		final String ANSI_GREEN = "\u001B[32m";
		final String ANSI_YELLOW = "\u001B[33m";
		final String ANSI_BLUE = "\u001B[34m";
		final String ANSI_PURPLE = "\u001B[35m";
		final String ANSI_CYAN = "\u001B[36m";
		final String ANSI_WHITE = "\u001B[37m";

		final String BLACK_BACKGROUND = "\033[40m";  
		final String RED_BACKGROUND = "\033[41m";  
    final String GREEN_BACKGROUND = "\033[42m"; 
    final String YELLOW_BACKGROUND = "\033[43m";
    final String BLUE_BACKGROUND = "\033[44m";   
    final String PURPLE_BACKGROUND = "\033[45m";
    final String CYAN_BACKGROUND = "\033[46m";   
    final String WHITE_BACKGROUND = "\033[47m";  

		String chosenColor = "";
		String finalText = "";
		switch(color){
			case "black" -> chosenColor = ANSI_BLACK;
			case "red" -> chosenColor = ANSI_RED;
			case "green" -> chosenColor = ANSI_GREEN;
			case "yellow" -> chosenColor = ANSI_YELLOW;
			case "blue" -> chosenColor = ANSI_BLUE;
			case "purple" -> chosenColor = ANSI_PURPLE;
			case "cyan" -> chosenColor = ANSI_CYAN;
			case "white" -> chosenColor = ANSI_WHITE;
			default -> color = "white";
		}
		switch(background){
			case "black" -> finalText = BLACK_BACKGROUND + chosenColor + text + ANSI_RESET;
			case "red" -> finalText = RED_BACKGROUND + chosenColor + text + ANSI_RESET;
			case "green" -> finalText = GREEN_BACKGROUND + chosenColor + text + ANSI_RESET;
			case "yellow" -> finalText = YELLOW_BACKGROUND + chosenColor + text + ANSI_RESET;
			case "blue" -> finalText = BLUE_BACKGROUND + chosenColor + text + ANSI_RESET;
			case "purple" -> finalText = PURPLE_BACKGROUND + chosenColor  + text + ANSI_RESET; 
			case "cyan" -> finalText = CYAN_BACKGROUND + chosenColor + text + ANSI_RESET;
			case "white" -> finalText = WHITE_BACKGROUND + chosenColor + text + ANSI_RESET; 
                        default -> finalText = "";
                }
                return finalText;
        }
}
