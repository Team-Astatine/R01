package teamzesa.DataBase.entity.UserInventory;

import teamzesa.DataBase.entity.RObject.RObject;

import java.util.ArrayList;
import java.util.UUID;

public record UserInventory(
        UUID uuid,
        ArrayList<Armour> armours,
        ArrayList<Item> inventory,
        Item mainHand,
        Item offHand
) implements RObject {
}
