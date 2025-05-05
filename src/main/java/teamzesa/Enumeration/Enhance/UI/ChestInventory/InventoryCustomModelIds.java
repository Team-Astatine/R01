package teamzesa.Enumeration.Enhance.UI.ChestInventory;

public enum InventoryCustomModelIds {
//    Enhance ModelData
    PANEL_STUFF_CUSTOM_DATA(20000),
    EXECUTE_STUFF_DATA(30000),
    EXECUTE_DISCORD_DATA(40000),
    EXECUTE_NOTION_DATA(50000);

    private final int customModelData;

    InventoryCustomModelIds(int customModelData) {
        this.customModelData = customModelData;
    }

    public int getValues() {
        return customModelData;
    }
}
