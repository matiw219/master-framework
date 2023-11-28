package me.masterkaiser.framework.item.meta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.masterkaiser.framework.item.MasterMeta;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.meta.KnowledgeBookMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@AllArgsConstructor
@Getter
public class MasterKnowledgeBookMeta implements MasterMeta<KnowledgeBookMeta> {
    private @NotNull List<NamespacedKey> recipes;

    public MasterKnowledgeBookMeta setRecipes(@NotNull List<NamespacedKey> recipes) {
        this.recipes = recipes;
        return this;
    }

    @Override
    public void applyFor(KnowledgeBookMeta knowledgeBookMeta) {
        knowledgeBookMeta.setRecipes(this.recipes);
    }
}
