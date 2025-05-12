package teamzesa.Util.UserUIGenerator.UIGenerator;

public enum InventoryUICustomModeData {

//        Enhance
    PANEL_STUFF_CUSTOM_DATA(20000),
    EXECUTE_STUFF_DATA(30000),
    EXECUTE_DISCORD_DATA(40000),
    EXECUTE_NOTION_DATA(50000),


//    GSit 
    SIT(20000),
    LAY(30000),
    SPIN(40000),
    CRAWL(50000);

    private final int customModelData;

    InventoryUICustomModeData(int customModelData) {
        this.customModelData = customModelData;
    }

    public int getValues() {
        return customModelData;
    }
}
