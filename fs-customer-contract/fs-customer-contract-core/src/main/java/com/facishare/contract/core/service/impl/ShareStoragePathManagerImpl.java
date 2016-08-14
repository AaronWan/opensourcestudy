package com.facishare.contract.core.service.impl;

import com.facishare.contract.core.service.ShareStoragePathManager;
import com.github.autoconf.ConfigFactory;
import com.google.common.collect.Lists;
import org.mongodb.morphia.annotations.PostLoad;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;

/**
 * Created by Aaron on 16/4/19.
 */
public class ShareStoragePathManagerImpl implements ShareStoragePathManager {
    Logger LOG = LoggerFactory.getLogger(ShareStoragePathManagerImpl.class);
    private String configName;
    private final Random random = new Random();
    private List<PathConfig> paths = Lists.newArrayList();

    @PostConstruct
    @Override
    public void init() {
//        path=/dsfdsf/sdf/dsf,1;/sdlf/dsfjl/dlkfj,2
        ConfigFactory.getInstance().getConfig(configName, config -> {
            LOG.info("===============LOAD " + configName + "  config===================");
            String path = config.get("path");
            paths.clear();
            process(path);
            LOG.info(path);
        });
    }

    private void process(String pathStr) {
        List<PathConfig> pathConfigs = Lists.newArrayList();
        String[] path = pathStr.split(";");
        int totalPercent = 0;
        for (String pathPercent : path) {
            String[] temps = pathPercent.split(",");
            String finalPath = temps[0];
            int percent = temps.length == 2 ? Integer.valueOf(temps[1]) : 1;
            totalPercent += percent;
            pathConfigs.add(new PathConfig(finalPath, percent));
        }

        for (PathConfig pathConfig : pathConfigs) {
            int percent = pathConfig.getPercent() * 100 / totalPercent;
            for (int i = 0; i < percent; i++) {
                paths.add(pathConfig);
            }
        }

    }

    @Override
    public String getShareStoragePath() {
        return paths.get(random.nextInt(paths.size())).getPath();
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    private static class PathConfig {
        private String path;
        private int percent;

        public PathConfig(String path, int percent) {
            this.path = path;
            this.percent = percent;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getPercent() {
            return percent;
        }

        public void setPercent(int percent) {
            this.percent = percent;
        }
    }
}
