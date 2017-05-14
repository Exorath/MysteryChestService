package com.exorath.service.mysteryChest;

import com.exorath.service.mysteryChest.res.GetMysteryChestsRes;
import com.exorath.service.mysteryChest.res.GiveMysteryChestReq;
import com.exorath.service.mysteryChest.res.MysteryChest;
import com.exorath.service.mysteryChest.res.Success;
import com.exorath.service.reward.res.GrantRewardSuccess;

/**
 * Created by toonsev on 5/14/2017.
 */
public interface Service {

    Success giveMysteryChest(GiveMysteryChestReq req);

    /**
     * Gets a lists of chests of a player
     *
     * @param uuid the player uuid
     * @return response
     */
    GetMysteryChestsRes getMysteryChests(String uuid);

    /**
     * consumes the mystery chest (sends a request to the reward service to grant the reward)
     *
     * @param uuid             the player uuid
     * @param mysteryChestUuid the uuid of the mystery chest
     * @return whether or not the mystery chest was consumed
     */
    GrantRewardSuccess consumeMysteryChest(String uuid, String mysteryChestUuid);

    MysteryChest getMysteryChest(String mysteryChestUuid);

}
