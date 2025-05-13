package teamzesa.Event.Enhance.Enumeration;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.apache.commons.lang3.BooleanUtils;
import teamzesa.Enumeration.Type.ColorType;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum EnhanceItemLore {
    ONE_STEP(1, Component.text("★ +1")
            .color(ColorType.WHITE.getTextColor())
    ),
    TWO_STEP(2, Component.text("★★ +2")
            .color(ColorType.WHITE_TO_RED1.getTextColor())
    ),
    THREE_STEP(3, Component.text("★★★ +3")
            .color(ColorType.WHITE_TO_RED2.getTextColor())
    ),
    FOUR_STEP(4, Component.text("★★★★ +4")
            .color(ColorType.WHITE_TO_RED3.getTextColor())
    ),
    FIVE_STEP(5, Component.text("★★★★★ +5")
            .color(ColorType.WHITE_TO_RED4.getTextColor())
    ),
    SIX_STEP(6, Component.text("★★★★★★ +6")
            .color(ColorType.WHITE_TO_RED5.getTextColor())
    ),
    SEVEN_STEP(7, Component.text("★★★★★★★ +7")
            .color(ColorType.WHITE_TO_RED6.getTextColor())
    ),
    EIGHT_STEP(8, Component.text("★★★★★★★★ +8")
            .color(ColorType.WHITE_TO_RED7.getTextColor())
    ),
    NINE_STEP(9, Component.text("★★★★★★★★★ +9")
            .color(ColorType.WHITE_TO_RED8.getTextColor())
    ),
    TEN_STEP(10, Component.text("★★★★★★★★★★ +10")
            .color(ColorType.RED.getTextColor())
    );

    private final int enhanceStack;
    private final Component component;
    private final static Map<Integer, EnhanceItemLore> CACHED_ITEM =
            Arrays.stream(values()).collect(
                    Collectors.toMap(
                            EnhanceItemLore::getEnhanceStack,
                            Function.identity()
                    )
            );


    EnhanceItemLore(int enhanceStack, Component component) {
        this.enhanceStack = enhanceStack;
        this.component = component;
    }

    public static Component getEnhancementLoreByLevel(int enhanceLevel) {
        if (BooleanUtils.isFalse(CACHED_ITEM.containsKey(enhanceLevel)))
            return Component.text("Unknown Enhancement Status")
                    .color(ColorType.RED.getTextColor())
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
