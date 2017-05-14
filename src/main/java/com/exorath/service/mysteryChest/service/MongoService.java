package com.exorath.service.mysteryChest.service;

import com.exorath.service.mysteryChest.Service;
import com.exorath.service.mysteryChest.res.GetMysteryChestsRes;
import com.exorath.service.mysteryChest.res.GiveMysteryChestReq;
import com.exorath.service.mysteryChest.res.MysteryChest;
import com.exorath.service.mysteryChest.res.Success;
import com.exorath.service.reward.api.RewardServiceAPI;
import com.exorath.service.reward.res.GrantRewardReq;
import com.exorath.service.reward.res.GrantRewardSuccess;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by toonsev on 5/14/2017.
 */
public class MongoService implements Service {
    private final Gson GSON = new Gson();
    private MongoCollection<Document> chestsCollection;
    private RewardServiceAPI rewardServiceAPI;

    public MongoService(MongoClient client, String databaseName, RewardServiceAPI rewardServiceAPI) {
        this.chestsCollection = client.getDatabase(databaseName).getCollection("chests");
        this.rewardServiceAPI = rewardServiceAPI;
    }

    public MongoService() {
        chestsCollection.createIndex(new Document("owner", 1));
    }

    @Override
    public Success giveMysteryChest(GiveMysteryChestReq req) {
        try {
            Document record = new Document("_id", UUID.randomUUID().toString())
                    .append("owner", req.getPlayerUuid())
                    .append("quality", req.getQuality())
                    .append("categoryId", req.getRewardCategoryId());
            if (req.getReason() != null)
                record.append("reason", req.getReason());
            chestsCollection.insertOne(record);
            return new Success(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new Success(-1, e.getMessage());
        }
    }

    @Override
    public GetMysteryChestsRes getMysteryChests(String uuid) {
        Set<MysteryChest> mysteryChests = new HashSet<>();
        chestsCollection.find(new Document("owner", uuid)).iterator()
                .forEachRemaining(doc -> mysteryChests.add(mysteryChestFromDoc(doc)));
        return new GetMysteryChestsRes(mysteryChests);
    }

    private MysteryChest mysteryChestFromDoc(Document doc) {
        return new MysteryChest(doc.getString("_id"),
                doc.getInteger("quality"),
                doc.getString("reason"),
                doc.getString("rewardCategoryId"));
    }

    @Override
    public GrantRewardSuccess consumeMysteryChest(String uuid, String mysteryChestUuid) {
        MysteryChest mysteryChest = getMysteryChest(mysteryChestUuid);
        return rewardServiceAPI.grantReward(new GrantRewardReq(uuid, mysteryChest.getRewardCategoryId(), mysteryChest.getQuality()));
    }

    @Override
    public MysteryChest getMysteryChest(String mysteryChestUuid) {
        Document doc = chestsCollection.find(new Document("_id", mysteryChestUuid)).first();
        return doc == null ? null : mysteryChestFromDoc(doc);
    }

}
