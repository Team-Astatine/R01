package teamzesa.event;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockPistonExtendEvent;
import teamzesa.event.register.EventRegister;
import teamzesa.util.ComponentExchanger;

import java.util.List;

public class PistonPushEvent implements EventRegister {
    private final BlockPistonExtendEvent event;
    private List<Block> targetBlockList;

    public PistonPushEvent(BlockPistonExtendEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.targetBlockList = this.event.getBlocks();
    }

    @Override
    public void execute() {
        boolean typeChecker = this.targetBlockList.stream()
                .anyMatch(block ->
                        block.getType().equals(Material.SAND) ||
                        block.getType().equals(Material.GRAVEL)
                );

        if (typeChecker)
            this.event.setCancelled(true);
    }
}
