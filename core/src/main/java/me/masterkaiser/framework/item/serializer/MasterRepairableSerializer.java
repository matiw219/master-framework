package me.masterkaiser.framework.item.serializer;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import me.masterkaiser.framework.item.meta.MasterRepairable;

public class MasterRepairableSerializer implements ObjectSerializer<MasterRepairable> {
    @Override
    public boolean supports(@NonNull Class<? super MasterRepairable> type) {
        return MasterRepairable.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull MasterRepairable object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("repairCost", object.getRepairCost());
    }

    @Override
    public MasterRepairable deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        return new MasterRepairable(data.get("repairCost", Integer.class));
    }
}
