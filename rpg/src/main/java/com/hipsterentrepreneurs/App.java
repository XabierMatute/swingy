package com.hipsterentrepreneurs;
import com.hipsterentrepreneurs.model.Hero;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Hero.Builder builder = new Hero.Builder().heroName("Jokin").heroClass("putero");
        Hero hero = builder.build();
        System.out.println("Aupa " + hero.getName() + " the " + hero.getHeroClass() + "!");
        System.out.println(hero.toString());
    }
}
