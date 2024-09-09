package teamzesa.DataBase.entity.Restricted.Chat;

import org.bukkit.entity.Player;

public record SendChatObject(
        Player player,
        String comment
) {
}
