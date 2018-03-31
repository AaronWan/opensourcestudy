package com.game.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;

import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author 万松(Aaron)
 * @since 6.2
 */
public class GameConfigManager {
    public static final Gson gson = new GsonBuilder().create();

    private static Random random = new Random();

    public static Set<String> getRandomStr(int n) {
        Set<String> currentChars = CurrentConfigs.instance.getCurrentConfigChars();
        if (currentChars != null) {
            return currentChars;
        } else {
            Set<String> rst = GameConfig.getRandomStr(n);
            if (rst.size() > 0) {
                CurrentConfigs.instance.save(rst);
                return rst;
            } else {
                CurrentConfigs.instance.clear();
                return getRandomStr(n);
            }
        }
    }

    @Data
    public static class GameConfig {
        public transient static final GameConfig gameConfig;

        static {
            String content = FileUtils.toString("/config/GameConfig");
            gameConfig = gson.fromJson(content, GameConfig.class);
        }

        private List<String> passwordChars;

        public static Set<String> getRandomStr(int n) {
            int size = gameConfig.passwordChars.size();
            if (n > size) {
                throw new RuntimeException("password init so big,should smallest than " + size);
            }
            Set<String> rst = Sets.newTreeSet();
            Set<String> tempRst = Sets.newTreeSet();

            while (rst.size() < n && tempRst.size() < size) {
                int num = random.nextInt(size);
                String temp = gameConfig.passwordChars.get(num);
                tempRst.add(temp);

                if (!rst.contains(temp) && !CurrentConfigs.instance.isExists(temp)) {
                    rst.add(temp);
                }
            }
            return rst;
        }
    }

    @Data
    static class CurrentConfigs {
        private transient static final CurrentConfigs instance;

        private transient static Set<String> currentAllChars;

        public Set<String> getCurrentConfigChars() {
            if (currentConfigs!=null&&currentConfigs.size() > 0) {
                CurrentConfig currentConfig = currentConfigs.get(currentConfigs.size() - 1);
                if (currentConfig.isAvaiable()) {
                    currentConfig.plusTimes();
                    save();
                    return currentConfig.currentPasswordChars;
                }
            }
            return null;
        }

        static {
            String current = FileUtils.toString("/config/Current");
            instance = gson.fromJson(current, CurrentConfigs.class);
        }

        private List<CurrentConfig> currentConfigs = Lists.newArrayList();

        public void save(Set<String> passwordChars) {
            CurrentConfig currentConfig = new CurrentConfig(passwordChars);
            currentConfigs.add(currentConfig);
            currentAllChars.addAll(passwordChars);
            save();
        }

        public boolean isExists(String ch) {
            if (currentAllChars == null) {
                currentAllChars = Sets.newHashSet();
                currentConfigs.stream().forEach(item -> currentAllChars.addAll(item.getCurrentPasswordChars()));
            }
            return currentAllChars.contains(ch);
        }

        public void clear() {
            currentConfigs.clear();
            currentAllChars.clear();
            save();
        }

        private void save() {
            FileUtils.write("/config/Current", gson.toJson(this));
        }

        @Data
        private static class CurrentConfig {
            /**
             * 当使用次数大于2 或 该密码使用时长超过7天就进行更新游戏
             */
            private Long start;
            private Set<String> currentPasswordChars;
            private Integer times;
            private transient Integer day = 7;
            private transient Integer maxTimes = 7;

            public CurrentConfig() {
            }

            public CurrentConfig(Set<String> currentPasswordChars) {
                this.start = System.currentTimeMillis();
                this.times = 1;
                this.currentPasswordChars = currentPasswordChars;
            }

            public boolean isAvaiable() {
                return (start != null && (((start + day * 3600 * 24 * 1000)-System.currentTimeMillis() ) > 0) )&& times < maxTimes;
            }

            public void plusTimes() {
                times++;
            }
        }
    }


    public static void main(String[] args) {
        int n = 0;
        while (n++ < 40)
            System.out.println(getRandomStr(20));
    }
}
