package com.exorath.service.mysteryChest;

import com.exorath.service.commons.mongoProvider.MongoProvider;
import com.exorath.service.commons.portProvider.PortProvider;
import com.exorath.service.commons.tableNameProvider.TableNameProvider;
import com.exorath.service.mysteryChest.service.MongoService;
import com.exorath.service.reward.api.RewardServiceAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by toonsev on 5/14/2017.
 */
public class Main {
    private Service service;
    private static final Logger LOG = LoggerFactory.getLogger(com.exorath.service.reward.Main.class);

    public Main() {
        this.service = new MongoService(MongoProvider.getEnvironmentMongoProvider().getClient(),
                TableNameProvider.getEnvironmentTableNameProvider("DB_NAME").getTableName(),
                new RewardServiceAPI(TableNameProvider.getEnvironmentTableNameProvider("REWARD_SERVICE_ADDRESS").getTableName()));
        LOG.info("Service " + this.service.getClass() + " instantiated");
        Transport.setup(service, PortProvider.getEnvironmentPortProvider());
        LOG.info("HTTP Transport initiated");
    }


    public static void main(String[] args) {
        new com.exorath.service.reward.Main();
    }
}
