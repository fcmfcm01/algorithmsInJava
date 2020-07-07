package org.fcm.alg.example.chapter2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.sound.midi.Track;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BinaryOperator;

/**
 * 有一天,阿里巴巴赶着一头毛驴上山砍柴。砍好柴准备下山时,远处突然出现一股烟尘,弥漫着直向上空飞扬,朝他这儿卷过来,而且越来越近。
 * 靠近以后,他才看清原来是一支马队,他们共有四十人,一个个年轻力壮、行动敏捷。
 * 一个首领模样的人背负沉重的鞍袋,从丛林中一直来到那个大石头跟前,喃喃地说道:“芝麻,开门吧!”随着那个头目的喊声,大石头前突然出现一道宽阔的门路,于是强盗们鱼贯而入。
 * 阿里巴巴待在树上观察他们,直到他们走得无影无踪之后,才从树上下来。他大声喊道:他小心翼翼地走了进去,一下子惊呆了,洞中堆满了财物,还有多得无法计数的金银珠宝,有的散堆在地区上,有的盛在皮袋中。
 * 突然看见这么多的金银财富,“芝麻,开门吧!”他的喊声刚落,洞门立刻打开了。阿里巴巴深信这肯定是一个强盗们数代经营、掠夺所积累起来的宝窟。
 * 为了让乡亲们开开眼界,见识一下这些宝物,他想一种宝物只拿一个,如果太重就用锤子凿开,但毛驴的运载能力是有限的,怎么才能用驴子运走最大价值的财宝分给穷人呢?阿里巴巴与四十大盗阿里巴巴陷入沉思中......
 */
public class AlibabaBag {

    public static List<Treasure> getTreasures(int amount) {
        List<Treasure> treasureList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            treasureList.add(new Treasure(i + "", getRandomValue(amount), getRandomValue(amount)));
        }
        return treasureList;
    }

    private static Double getRandomValue(int amount) {
        return Double.valueOf(Math.random() * amount);
    }

    public static void main(String[] args) {
        List<Treasure> treasureList = getTreasures(100);
        loadTreasures(treasureList, 100);

    }

    public static void loadTreasures(List<Treasure> treasureList, double capacity) {
        Collections.sort(treasureList);
        System.out.println("treasures = " + treasureList.toString());
        List<Treasure> loadedTreasures = new ArrayList();
        double capacityLoaded = 0;
        for (int i = 0; i < treasureList.size(); i++) {
            Treasure treasure = treasureList.get(i);
            //当前已装载重量小于载重
            if ((capacityLoaded + treasure.getWeight()) <= capacity) {
                capacityLoaded += treasure.getWeight();
                loadedTreasures.add(treasure);
            } else //当前剩余容量已不足以装载剩下的整个货物，则只取剩余的容量
                if (capacity > capacityLoaded && (capacity - capacityLoaded < treasure.getWeight())) {
                    double leftCapacity = capacity - capacityLoaded;
                    capacityLoaded += leftCapacity;
                    treasure.setValue((leftCapacity) * treasure.getPricePerformanceRatio());
                    treasure.setWeight(leftCapacity);
                    loadedTreasures.add(treasure);
                    break;
                }
        }
        System.out.println("Loaded treasures weight:" + loadedTreasures.stream().map(treasure -> treasure.getWeight()).reduce((aDouble, aDouble2) -> aDouble + aDouble2));
        System.out.println("Loaded Treasures value :" + loadedTreasures.stream().map(o -> o.getValue()).reduce((aDouble, aDouble2) -> aDouble + aDouble2));
        System.out.println("loadedTreasures = " + loadedTreasures.toString());
    }
}

@Data
class Treasure implements Comparable {
    private String id;
    private Double weight;
    private Double value;
    private Double pricePerformanceRatio;

    public Treasure(String id, Double weight, Double value) {
        this.id = id;
        this.weight = weight;
        this.value = value;
        this.pricePerformanceRatio = value / weight;
    }

    @Override
    public int compareTo(Object o) {
        //降序排列
        return ((Treasure) o).getPricePerformanceRatio().compareTo(this.getPricePerformanceRatio());
    }
}