package me.masterkaiser.framework.item.meta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.masterkaiser.framework.item.MasterMeta;
import org.bukkit.DyeColor;
import org.bukkit.entity.TropicalFish;
import org.bukkit.inventory.meta.TropicalFishBucketMeta;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
@Getter
public class MasterTropicalFishBucketMeta implements MasterMeta<TropicalFishBucketMeta> {
    private @NotNull DyeColor patternColor;
    private @NotNull DyeColor bodyColor;
    private @NotNull TropicalFish.Pattern pattern;

    public MasterTropicalFishBucketMeta setBodyColor(DyeColor bodyColor) {
        this.bodyColor = bodyColor;
        return this;
    }

    public MasterTropicalFishBucketMeta setPattern(TropicalFish.Pattern pattern) {
        this.pattern = pattern;
        return this;
    }

    public MasterTropicalFishBucketMeta setPatternColor(DyeColor patternColor) {
        this.patternColor = patternColor;
        return this;
    }

    @Override
    public void applyFor(TropicalFishBucketMeta tropicalFishBucketMeta) {
        tropicalFishBucketMeta.setPatternColor(this.patternColor);
        tropicalFishBucketMeta.setBodyColor(this.bodyColor);
        tropicalFishBucketMeta.setPattern(this.pattern);
    }
}
