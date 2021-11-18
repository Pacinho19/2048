package pl.pacinho.utils;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    public static Integer getInt(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
