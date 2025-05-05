package teamzesa.Data.Function.Restricted;

import org.bukkit.entity.Player;

public record ChatMessage(
        Player player,
        String comment
) {
}
