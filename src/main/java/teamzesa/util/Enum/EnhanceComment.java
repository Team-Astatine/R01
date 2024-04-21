package teamzesa.util.Enum;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import teamzesa.util.Enum.ColorList;

public enum EnhanceComment {
    ONE_STEP(1, Component.text("★ +1")
            .color(ColorList.WHITE.getTextColor())
    ),
    TWO_STEP(2, Component.text("★★ +2")
            .color(ColorList.WHITE_TO_RED1.getTextColor())
    ),
    THREE_STEP(3, Component.text("★★★ +3")
            .color(ColorList.WHITE_TO_RED2.getTextColor())
    ),
    FOUR_STEP(4, Component.text("★★★★ +4")
            .color(ColorList.WHITE_TO_RED3.getTextColor())
    ),
    FIVE_STEP(5, Component.text("★★★★★ +5")
            .color(ColorList.WHITE_TO_RED4.getTextColor())
    ),
    SIX_STEP(6, Component.text("★★★★★★ +6")
            .color(ColorList.WHITE_TO_RED5.getTextColor())
    ),
    SEVEN_STEP(7, Component.text("★★★★★★★ +7")
            .color(ColorList.WHITE_TO_RED6.getTextColor())
    ),
    EIGHT_STEP(8, Component.text("★★★★★★★★ +8")
            .color(ColorList.WHITE_TO_RED7.getTextColor())
    ),
    NINE_STEP(9, Component.text("★★★★★★★★★ +9")
            .color(ColorList.WHITE_TO_RED8.getTextColor())
    ),
    TEN_STEP(10, Component.text("★★★★★★★★★★ +10")
            .color(ColorList.RED.getTextColor())
    );

    private final int step;
    private final Component component;

    EnhanceComment(int step, Component component) {
        this.step = step;
        this.component = component;
    }

    public int getStep() {
        return step;
    }

    public Component getLoreComment() {
        return this.component;
    }
}
