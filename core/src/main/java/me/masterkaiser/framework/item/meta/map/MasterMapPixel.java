package me.masterkaiser.framework.item.meta.map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Color;

@AllArgsConstructor
@Getter
public class MasterMapPixel {
    private int x;
    private int y;
    private Color color;

    public MasterMapPixel setX(int x) {
        this.x = x;
        return this;
    }

    public MasterMapPixel setY(int y) {
        this.y = y;
        return this;
    }

    public MasterMapPixel setColor(Color color) {
        this.color = color;
        return this;
    }
}
