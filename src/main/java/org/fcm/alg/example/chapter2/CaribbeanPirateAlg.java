package org.fcm.alg.example.chapter2;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 在北美洲东南部,有一片神秘的海域,那里碧海蓝天、阳光明媚,这正是传说中海盗最活跃的加勒比海,这里更是欧洲大陆的商旅舰队到达美洲的必经之地,
 * 所以当时的海盗活皇家舰......动非常猖獗,海盗不仅攻击过往商人,甚至攻击英国有一天,海盗们截获了一艘装满各种各样古董的货船,每一件古董都价值连城,一旦打碎就失去了它的价值。
 * 虽然海盗船足够大,但载重量为 C,每件古董的重量为 w ,海盗们该如何把尽可能多数量的宝贝装上海盗船呢?
 */
public class CaribbeanPirateAlg {

    public static int[] getTreasures(int amount) {
        int[] results = new int[amount];
        for (int i = 0; i < amount; i++) {
            results[i] = Double.valueOf(Math.random() * amount).intValue();
        }
        return results;
    }

    public static void loadTreasures(int[] treasures, int capacity) {
        Arrays.sort(treasures);
        System.out.println("treasures = " + Arrays.toString(treasures));
        List loadedTreasures = new ArrayList();
        int capacityLoaded = 0;
        for (int i = 0; i < treasures.length; i++) {
            if (capacityLoaded+ treasures[i]<= capacity) {
                capacityLoaded += treasures[i];
                loadedTreasures.add(treasures[i]);
            } else {
                break;
            }
        }

        System.out.println("Loaded treasures = " + loadedTreasures.toString() + "\nLoaded capacity:" + capacityLoaded);
    }

    public static void main(String[] args) {
        int[] treasures = getTreasures(100);
        CaribbeanPirateAlg.loadTreasures(treasures, 558);
    }
}
