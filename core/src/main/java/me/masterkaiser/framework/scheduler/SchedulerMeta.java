package me.masterkaiser.framework.scheduler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SchedulerMeta {
    private boolean async = true;
    private int delay;
    private int interval;

    public SchedulerMeta(int delay, int interval) {
        this.delay = delay;
        this.interval = interval;
    }

    public SchedulerMeta setAsync(boolean async) {
        this.async = async;
        return this;
    }

    public SchedulerMeta setDelay(int delay) {
        this.delay = delay;
        return this;
    }

    public SchedulerMeta setInterval(int interval) {
        this.interval = interval;
        return this;
    }
}
