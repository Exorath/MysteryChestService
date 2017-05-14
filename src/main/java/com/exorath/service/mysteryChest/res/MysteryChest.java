package com.exorath.service.mysteryChest.res;

/**
 * Created by toonsev on 5/14/2017.
 */
public class MysteryChest {
    private String uuid;
    private int quality;
    private String reason;
    private String rewardCategoryId;
    public MysteryChest() {}

    public MysteryChest(String uuid, int quality, String reason, String rewardCategoryId) {
        this.uuid = uuid;
        this.quality = quality;
        this.reason = reason;
        this.rewardCategoryId = rewardCategoryId;
    }

    public String getRewardCategoryId() {
        return rewardCategoryId;
    }

    public String getUuid() {
        return uuid;
    }

    public int getQuality() {
        return quality;
    }

    public String getReason() {
        return reason;
    }
}
