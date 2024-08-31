package teamzesa.event.Restricted;

import org.bukkit.Material;

import java.util.HashSet;
import java.util.Set;

public class RestrictedChatElement {
    public final Set<Material> restrictedItem = new HashSet<>();
    public final Set<String> restrictedCommand = new HashSet<>();

    public RestrictedChatElement() {
        restrictedItem.add(Material.ARMOR_STAND);

        restrictedCommand.add("/pl");
        restrictedCommand.add("/plugins");
        restrictedCommand.add("/bukkit:pl");
        restrictedCommand.add("/bukkit:plugins");
    }
}
