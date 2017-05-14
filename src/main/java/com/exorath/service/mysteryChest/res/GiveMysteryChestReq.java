package com.exorath.service.mysteryChest.res;

/**
 * Created by toonsev on 5/14/2017.
 */
public class GiveMysteryChestReq {
    private String playerUuid;
    //this is the categoryId for the rewardService
    private String rewardCategoryId;
    private int quality;
    private String reason;

    public GiveMysteryChestReq() {}

    public GiveMysteryChestReq(String playerUuid, String rewardCategoryId, int quality, String reason) {
        this.playerUuid = playerUuid;
        this.rewardCategoryId = rewardCategoryId;
        this.quality = quality;
        this.reason = reason;
    }

    public String getRewardCategoryId() {
        return rewardCategoryId;
    }

    public String getPlayerUuid() {
        return playerUuid;
    }

    public int getQuality() {
        return quality;
    }

    public String getReason() {
        return reason;
    }
}
