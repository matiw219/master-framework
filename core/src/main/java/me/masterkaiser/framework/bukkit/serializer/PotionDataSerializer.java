package me.masterkaiser.framework.bukkit.serializer;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

public class PotionDataSerializer implements ObjectSerializer<PotionData> {
    @Override
    public boolean supports(@NonNull Class<? super PotionData> type) {
        return PotionData.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull PotionData object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("type", object.getType());
        data.add("extended", object.isExtended());
        data.add("upgraded", object.isUpgraded());
    }

    @Override
    public PotionData deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        final PotionType potionType = data.get("type", PotionType.class);
        boolean extended = data.get("extended", boolean.class);
        boolean upgraded = data.get("upgraded", boolean.class);

        return new PotionData(potionType, extended, upgraded);
    }
}
