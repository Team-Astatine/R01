package teamzesa.Util.UserUIGenerator.Executor;

import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.event.inventory.InventoryCloseEvent;
import teamzesa.Event.Enhance.PlayerInteraction.UserInterface.EnhanceUICloseEvent;
import teamzesa.Util.UserUIGenerator.Interface.Type;
import teamzesa.Util.UserUIGenerator.Interface.UIHolder;
import teamzesa.Util.UserUIGenerator.Interface.UIType;

public class UICloser {

    public UICloser(InventoryCloseEvent event) {

        if (!(event.getView().getTopInventory().getHolder() instanceof UIHolder holder))
            return;

        if (ObjectUtils.isEmpty(holder.getClass().getAnnotation(UIType.class)))
            return;

//        System.out.println("Annotations: " +
//                Arrays.toString(holder.getClass().getDeclaredAnnotations()));

        Type type = holder.getClass().getAnnotation(UIType.class).value();
        switch (type) {
            case ENHANCE    -> new EnhanceUICloseEvent(event);
        }

    }
}
