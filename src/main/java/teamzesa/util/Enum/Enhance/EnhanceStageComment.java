package teamzesa.util.Enum.Enhance;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import teamzesa.util.Enum.ColorMap;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum EnhanceStageComment {
    ONE_STEP(1, Component.text("★ +1")
            .color(ColorMap.WHITE.getTextColor())
    ),
    TWO_STEP(2, Component.text("★★ +2")
            .color(ColorMap.WHITE_TO_RED1.getTextColor())
    ),
    THREE_STEP(3, Component.text("★★★ +3")
            .color(ColorMap.WHITE_TO_RED2.getTextColor())
    ),
    FOUR_STEP(4, Component.text("★★★★ +4")
            .color(ColorMap.WHITE_TO_RED3.getTextColor())
    ),
    FIVE_STEP(5, Component.text("★★★★★ +5")
            .color(ColorMap.WHITE_TO_RED4.getTextColor())
    ),
    SIX_STEP(6, Component.text("★★★★★★ +6")
            .color(ColorMap.WHITE_TO_RED5.getTextColor())
    ),
    SEVEN_STEP(7, Component.text("★★★★★★★ +7")
            .color(ColorMap.WHITE_TO_RED6.getTextColor())
    ),
    EIGHT_STEP(8, Component.text("★★★★★★★★ +8")
            .color(ColorMap.WHITE_TO_RED7.getTextColor())
    ),
    NINE_STEP(9, Component.text("★★★★★★★★★ +9")
            .color(ColorMap.WHITE_TO_RED8.getTextColor())
    ),
    TEN_STEP(10, Component.text("★★★★★★★★★★ +10")
            .color(ColorMap.RED.getTextColor())
    );

    private final int enhanceStack;
    private final Component component;
    private final static Map<Integer, EnhanceStageComment> CACHED_ITEM = Arrays.stream(values())
                    .collect(Collectors.toMap(EnhanceStageComment::getEnhanceStack, Function.identity()));

    EnhanceStageComment(int enhanceStack, Component component) {
        this.enhanceStack = enhanceStack;
        this.component = component;
    }

    public static Component findByEnhanceLevelComment(int enhanceLevel) {
        if (!CACHED_ITEM.containsKey(enhanceLevel))
            return Component.text("Unknown Enhancement Status")
                    .color(ColorMap.RED.getTextColor())
                    .decorate(TextDecoration.ITALIC);

        return CACHED_ITEM.get(enhanceLevel).getLoreComment();
    }

    public int getEnhanceStack() {
        return enhanceStack;
    }

    public Component getLoreComment() {
        return this.component;
    }
}
