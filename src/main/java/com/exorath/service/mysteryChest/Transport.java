package com.exorath.service.mysteryChest;

import com.exorath.service.commons.portProvider.PortProvider;
import com.exorath.service.mysteryChest.res.GiveMysteryChestReq;
import com.exorath.service.mysteryChest.res.MysteryChest;
import com.exorath.service.reward.*;
import com.google.gson.Gson;
import spark.Route;
import spark.Spark;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

/**
 * Created by toonsev on 5/14/2017.
 */
public class Transport {
    private static final Gson GSON = new Gson();

    public static void setup(Service service, PortProvider portProvider) {
        port(portProvider.getPort());

        get("/players/:uuid/chests", getGetMysteryChestsRoute(service), GSON::toJson);
        get("chests/:chestUuid", getGetMysteryChestRoute(service), GSON::toJson);
        post("/players/:uuid/chests", getGiveMysteryChestRoute(service), GSON::toJson);
        post("/players/:uuid/chests/:chestUuid/consume", getConsumeMysteryChestRoute(service), GSON::toJson);

    }

    private static Route getGiveMysteryChestRoute(Service service) {
        return (req, res) -> {
            GiveMysteryChestReq giveMysteryChestReq = GSON.fromJson(req.body(), GiveMysteryChestReq.class);
            giveMysteryChestReq.setPlayerUuid(req.params("uuid"));
            return service.giveMysteryChest(giveMysteryChestReq);
        };
    }

    private static Route getGetMysteryChestsRoute(Service service) {
        return (req, res) -> service.getMysteryChests(req.params("uuid"));
    }

    private static Route getGetMysteryChestRoute(Service service) {
        return (req, res) -> service.getMysteryChest(req.params("chestUuid"));
    }

    private static Route getConsumeMysteryChestRoute(Service service) {
        return (req, res) -> service.consumeMysteryChest(req.params("uuid"), req.params("chestUuid"));
    }
}
