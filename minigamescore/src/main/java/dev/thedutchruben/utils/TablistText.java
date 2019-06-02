package dev.thedutchruben.utils;

public class TablistText {

    private boolean updated = false;
    private String text = "";

    public TablistText addLine(String extra) {
        if (updated) text += "\n";
        updated = true;
        text+= extra;
        return this;
    }

    public String asText() {
        return text;
    }

}
