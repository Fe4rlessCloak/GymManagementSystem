// ConsoleColors.java
// This file should be in the same directory as Main.java, ConsoleUI.java, etc.
// There should be NO 'package' declaration at the very top of this file.

public class ConsoleColors {
    // Reset code to clear all previous formatting
    public static final String RESET = "\u001B[0m";

    // Standard Text Colors
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    // Bright Text Colors (often more vibrant on modern terminals)
    public static final String BRIGHT_BLACK = "\u001B[90m";
    public static final String BRIGHT_RED = "\u001B[91m";
    public static final String BRIGHT_GREEN = "\u001B[92m";
    public static final String BRIGHT_YELLOW = "\u001B[93m";
    public static final String BRIGHT_BLUE = "\u001B[94m";
    public static final String BRIGHT_PURPLE = "\u001B[95m";
    public static final String BRIGHT_CYAN = "\u001B[96m";
    public static final String BRIGHT_WHITE = "\u001B[97m";
    public static final String BRIGHT_MAGENTA = "\u001B[95m"; // <--- Ensure this line is present and correct!

    // Background Colors
    public static final String BG_BLACK = "\u001B[40m";
    public static final String BG_RED = "\u001B[41m";
    public static final String BG_GREEN = "\u001B[42m";
    public static final String BG_YELLOW = "\u001B[43m";
    public static final String BG_BLUE = "\u001B[44m";
    public static final String BG_PURPLE = "\u001B[45m";
    public static final String BG_CYAN = "\u001B[46m";
    public static final String BG_WHITE = "\u001B[47m";

    // Text Styles
    public static final String BOLD = "\u001B[1m";
    public static final String UNDERLINE = "\u001B[4m";

    // Helper methods to apply colors and styles easily
    public static String applyColor(String color, String text) {
        return color + text + RESET;
    }
    public static String applyBold(String text) {
        return BOLD + text + RESET;
    }
    public static String applyUnderline(String text) {
        return UNDERLINE + text + RESET;
    }
    public static String applyBoldColor(String color, String text) {
        return BOLD + color + text + RESET;
    }
}