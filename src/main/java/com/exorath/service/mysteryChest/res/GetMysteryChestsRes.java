package com.exorath.service.mysteryChest.res;

import java.util.Set;

/**
 * Created by toonsev on 5/14/2017.
 */
public class GetMysteryChestsRes {
    private Set<MysteryChest> chests;

    public GetMysteryChestsRes() {
    }

    public GetMysteryChestsRes(Set<MysteryChest> chests) {
        this.chests = chests;
    }

    public Set<MysteryChest> getChests() {
        return chests;
    }
}
