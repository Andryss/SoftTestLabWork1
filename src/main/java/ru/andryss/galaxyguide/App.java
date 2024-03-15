package ru.andryss.galaxyguide;

import java.util.List;

public class App {
    // https://litmir.club/br/?b=583090&p=21#:~:text=%D0%A3%D1%81%D0%BF%D0%BE%D0%BA%D0%BE%D0%B9%D1%81%D1%8F%2C%20%D0%BF%D1%80%D0%B8%D0%B4%D0%B8%20%D0%B2%20%D1%81%D0%B5%D0%B1%D1%8F%E2%80%A6%20%D0%BE%2C%20%D1%8D%D1%82%D0%BE%20%D0%B8%D0%BD%D1%82%D0%B5%D1%80%D0%B5%D1%81%D0%BD%D0%BE%D0%B5%20%D0%BE%D1%89%D1%83%D1%89%D0%B5%D0%BD%D0%B8%D0%B5%2C%20%D1%87%D1%82%D0%BE%20%D1%8D%D1%82%D0%BE%20%D1%82%D0%B0%D0%BA%D0%BE%D0%B5%3F%20%D0%AD%D1%82%D0%BE%20%D0%B2%D1%80%D0%BE%D0%B4%D0%B5%E2%80%A6%20%D0%BF%D0%BE%D1%81%D0%B0%D1%81%D1%8B%D0%B2%D0%B0%D0%BD%D0%B8%D1%8F%2C%20%D0%B4%D1%80%D0%BE%D0%B6%D0%B8%20%D1%83%20%D0%BC%D0%B5%D0%BD%D1%8F%20%D0%B2%E2%80%A6%20%D1%83%20%D0%BC%D0%B5%D0%BD%D1%8F%20%D0%B2%E2%80%A6%20%D0%BF%D0%BE%D0%B6%D0%B0%D0%BB%D1%83%D0%B9%2C%20%D0%BC%D0%BD%D0%B5%20%D0%BD%D1%83%D0%B6%D0%BD%D0%BE%20%D0%BD%D0%B0%D1%87%D0%B8%D0%BD%D0%B0%D1%82%D1%8C%20%D0%BF%D1%80%D0%B8%D0%B4%D1%83%D0%BC%D1%8B%D0%B2%D0%B0%D1%82%D1%8C%20%D0%BD%D0%B0%D0%B7%D0%B2%D0%B0%D0%BD%D0%B8%D1%8F%20%D0%B4%D0%BB%D1%8F%20%D1%80%D0%B0%D0%B7%D0%BD%D1%8B%D1%85%20%D0%B2%D0%B5%D1%89%D0%B5%D0%B9%2C%20%D0%B5%D1%81%D0%BB%D0%B8%20%D1%8F%20%D1%85%D0%BE%D1%87%D1%83%20%D1%87%D0%B5%D0%B3%D0%BE%2D%D1%82%D0%BE%20%D0%B4%D0%BE%D1%81%D1%82%D0%B8%D0%B3%D0%BD%D1%83%D1%82%D1%8C%20%D0%B2%20%D1%82%D0%BE%D0%BC%2C%20%D1%87%D1%82%D0%BE%20%D1%8F%20%D0%B4%D0%BB%D1%8F%20%D1%83%D0%B4%D0%BE%D0%B1%D1%81%D1%82%D0%B2%D0%B0%20%D0%BD%D0%B0%D0%B7%D0%BE%D0%B2%D1%83%20%D0%BC%D0%B8%D1%80%D0%BE%D0%BC%2C%20%D0%BF%D0%BE%D1%8D%D1%82%D0%BE%D0%BC%D1%83%2C%20%D1%81%D0%BA%D0%B0%D0%B6%D0%B5%D0%BC%20%D1%82%D0%B0%D0%BA%3A%20%D1%83%20%D0%BC%D0%B5%D0%BD%D1%8F%20%D0%B2%20%D0%B6%D0%B5%D0%BB%D1%83%D0%B4%D0%BA%D0%B5.
    /*
    Успокойся, приди в себя… о, это интересное ощущение, что это такое? Это вроде… посасывания,
    дрожи у меня в… у меня в… пожалуй, мне нужно начинать придумывать названия для разных вещей,
    если я хочу чего-то достигнуть в том, что я для удобства назову миром, поэтому, скажем так:
    у меня в желудке.
     */

    @SuppressWarnings("NonAsciiCharacters")
    public static void main(String[] args) {
        Organ желудок = new Organ("желудок");
        Organ глаза = new Organ("глаза");
        Whale кит = new Whale(List.of(желудок, глаза));

        Feeling посасывание = new Feeling("посасывание");
        Feeling дрожь = new Feeling("дрожь");
        желудок.feel(посасывание, дрожь);

        Description голод = new Description(
                "голод",
                "физиологическое состояние организма, вызванное нехваткой пищи",
                List.of()
        );
        String name = кит.createName(голод);

        System.out.println(name);

        Feeling напряжение = new Feeling("напряжение");
        глаза.feel(напряжение);

        Description земля = new Description(
                "Земля",
                "третья по удалённости от Солнца планета Солнечной системы",
                List.of("большая", "плоская")
        );
        name = кит.createName(земля);

        System.out.println(name);
    }
}
