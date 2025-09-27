package com.hipsterentrepreneurs;
import com.hipsterentrepreneurs.model.Hero;

import com.hipsterentrepreneurs.model.Villain;

import jakarta.validation.*;

import java.util.Set;

import com.hipsterentrepreneurs.model.Artifact;


/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Hero.Builder builder = new Hero.Builder().heroName("Jokin").heroClass("putero");
        
        builder.weapon(new Artifact.Builder().name("Sword").bonus(5).builtWeapon());
        // builder.weapon(new Artifact.Builder().name("Shield").bonus(3).builtArmor());
        builder.armor(new Artifact.Builder().name("Shield").bonus(3).builtArmor());
        builder.helm(new Artifact.Builder().name("Helmet").bonus(2).builtHelm());
        
        Hero hero = builder.build();
        System.out.println("Aupa " + hero.getName() + " the " + hero.getHeroClass() + "!");
        System.out.println(hero.toString());

        System.out.println("Weapon: " + hero.getWeapon());

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Hero>> violations = validator.validate(hero);
        
        if (!violations.isEmpty()) {
            for (ConstraintViolation<Hero> violation : violations) {
                System.out.println(violation.getMessage());
            }
        } else {
            System.out.println("Hero is valid!");
        }

        factory.close();

        hero.gainExperience(1042);
        System.out.println("After gaining experience:");
        System.out.println(hero.toString());


        Villain villain = new Villain.Builder()
            .name("Evil Underlord")
            .attack(10)
            .defense(5)
            .hitPoints(30)
            .loot(new Artifact.Builder().name("Cursed sword").bonus(7).builtWeapon())
            .build();
        
        System.out.println("Beware of " + villain.getName() + " the Villain!");
        System.out.println(villain.toString());

        hero.attack(villain);
        System.out.println("After hero attacks villain:");
        System.out.println(villain.toString());
        villain.Attack(hero);
        System.out.println("After villain attacks hero:");
        System.out.println(hero.toString());

        System.out.println(hero.toStatsString());
        Hero hero2 = new Hero.Builder().build();
        System.out.println(hero2.toStatsString());
        
    }
}
