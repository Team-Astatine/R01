package teamzesa.dataValue;

import net.kyori.adventure.text.format.TextColor;

public enum ColorList{
    RED(TextColor.color(233, 30, 100)),
    PINK(TextColor.color(255,182,193)),
    ORANGE(TextColor.color(255, 142, 0)),
    YELLOW(TextColor.color(255, 228, 0)),
    PURPLE(TextColor.color(139, 0, 255)),
    SKY_BLUE(TextColor.color(173,216,230)),
    VOTE_COLOR(TextColor.color(167, 123, 202)),
    COMMAND_COLOR(TextColor.color(139, 195, 74)),
    NOTIOIN_COLOR(TextColor.color(112, 71, 157)),
    DISCORD_COLOR(TextColor.color(114, 137, 218));

    private final TextColor textColor;

    ColorList(TextColor colorName) {
        this.textColor = colorName;
    }

    public TextColor getTextColor() {
        return textColor;
    }
}