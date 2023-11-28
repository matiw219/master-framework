package me.masterkaiser.framework.serializer;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import me.masterkaiser.framework.scheduler.SchedulerMeta;

public class SchedulerMetaSerializer implements ObjectSerializer<SchedulerMeta> {
    @Override
    public boolean supports(@NonNull Class<? super SchedulerMeta> type) {
        return SchedulerMeta.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull SchedulerMeta object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("async", object.isAsync());
        data.add("delay", object.getDelay());
        data.add("interval", object.getInterval());
    }

    @Override
    public SchedulerMeta deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        final boolean async = data.get("async", Boolean.class);
        final int delay = data.get("delay", Integer.class);
        final int interval = data.get("interval", Integer.class);

        return new SchedulerMeta(async, delay, interval);
    }
}
