package teamzesa.Event.UserInterface.Function.Executor;

import org.apache.commons.lang3.ObjectUtils;
import org.bukkit.event.inventory.InventoryCloseEvent;
import teamzesa.Event.UserInterface.Enhance.EnhanceUICloseEvent;
import teamzesa.Event.UserInterface.Function.Interface.Type;
import teamzesa.Event.UserInterface.Function.Interface.UIHolder;
import teamzesa.Event.UserInterface.Function.Interface.UIType;

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
