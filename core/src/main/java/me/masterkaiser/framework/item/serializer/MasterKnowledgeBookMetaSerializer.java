package me.masterkaiser.framework.item.serializer;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import me.masterkaiser.framework.item.meta.MasterKnowledgeBookMeta;
import org.bukkit.NamespacedKey;

public class MasterKnowledgeBookMetaSerializer implements ObjectSerializer<MasterKnowledgeBookMeta> {
    @Override
    public boolean supports(@NonNull Class<? super MasterKnowledgeBookMeta> type) {
        return MasterKnowledgeBookMeta.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull MasterKnowledgeBookMeta object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("recipes", object.getRecipes());
    }

    @Override
    public MasterKnowledgeBookMeta deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        return new MasterKnowledgeBookMeta(data.getAsList("recipes", NamespacedKey.class));
    }
}
