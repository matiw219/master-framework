package me.masterkaiser.framework.bukkit.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RawTitle {
    private String title;
    private String subTitle;
    private int fadeIn = 40;
    private int stay = 40;
    private int fadeOut = 40;

    public RawTitle(String title, String subTitle) {
        this.title = title;
        this.subTitle = subTitle;
    }

    public RawTitle setTitle(String title) {
        this.title = title;
        return this;
    }

    public RawTitle setSubTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    public RawTitle setFadeIn(int fadeIn) {
        this.fadeIn = fadeIn;
        return this;
    }

    public RawTitle setStay(int stay) {
        this.stay = stay;
        return this;
    }

    public RawTitle setFadeOut(int fadeOut) {
        this.fadeOut = fadeOut;
        return this;
    }
}
