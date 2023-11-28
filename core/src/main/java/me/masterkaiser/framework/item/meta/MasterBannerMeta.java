package me.masterkaiser.framework.item.meta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.masterkaiser.framework.item.MasterMeta;
import org.bukkit.block.banner.Pattern;
import org.bukkit.inventory.meta.BannerMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@AllArgsConstructor
@Getter
public class MasterBannerMeta implements MasterMeta<BannerMeta> {
    private @NotNull List<Pattern> patterns;

    public MasterBannerMeta setPatterns(@NotNull List<Pattern> patterns) {
        this.patterns = patterns;
        return this;
    }

    @Override
    public void applyFor(BannerMeta bannerMeta) {
        bannerMeta.setPatterns(this.patterns);
    }
}
