package teamzesa.util.Enum;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import teamzesa.util.Enum.ColorList;

public enum EnhanceComment {
    ONE_STEP(Component.text("★ +1")
            .color(ColorList.WHITE.getTextColor())
    ),
    TWO_STEP(Component.text("★★ +2")
            .color(ColorList.WHITE_TO_RED1.getTextColor())
    ),
    THREE_STEP(Component.text("★★★ +3")
            .color(ColorList.WHITE_TO_RED2.getTextColor())
    ),
    FOUR_STEP(Component.text("★★★★ +4")
            .color(ColorList.WHITE_TO_RED3.getTextColor())
    ),
    FIVE_STEP(Component.text("★★★★★ +5")
            .color(ColorList.WHITE_TO_RED4.getTextColor())
    ),
    SIX_STEP(Component.text("★★★★★★ +6")
            .color(ColorList.WHITE_TO_RED5.getTextColor())
    ),
    SEVEN_STEP(Component.text("★★★★★★★ +7")
            .color(ColorList.WHITE_TO_RED6.getTextColor())
    ),
    EIGHT_STEP(Component.text("★★★★★★★★ +8")
            .color(ColorList.WHITE_TO_RED7.getTextColor())
    ),
    NINE_STEP(Component.text("★★★★★★★★★ +9")
            .color(ColorList.WHITE_TO_RED8.getTextColor())
    ),
    TEN_STEP(Component.text("★★★★★★★★★★ +10")
            .color(ColorList.RED.getTextColor())
    );

    private final Component component;

    EnhanceComment(Component component) {
        this.component = component;
    }

    public Component getLoreComment() {
        return this.component;
    }
}
