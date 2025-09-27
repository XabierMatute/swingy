/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Hero.java                                          :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: xmatute- <xmatute-@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/09/26 12:21:54 by xmatute-          #+#    #+#             */
/*   Updated: 2025/09/27 12:51:11 by xmatute-         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package com.hipsterentrepreneurs.model;

import jakarta.validation.constraints.*;

public class Hero {
    @NotNull(message = "Hero name cannot be null")
    private final String heroName;
    @NotNull(message = "Hero class cannot be null")
    private final String heroClass;
    @Min(value = 1, message = "Level must be at least {value}")
    private int level;
    @Min(value = 0, message = "Experience must be at least {value}")
    private int experience;
    @Min(value = 0, message = "Attack must be at least {value}")
    private int attack;
    @Min(value = 0, message = "Defense must be at least {value}")
    private int defense;
    @Min(value = 1, message = "Hit Points must be at least {value}")
    private int hitPoints;

    @Min(value = 0, message = "Health must be at least {value}")
    private int health;

    // En la línea 36, reemplaza el @ incompleto con:
    @AssertTrue(message = "Weapon must have type 'Weapon'")
    private boolean isWeaponValid() {
        return weapon == null || "Weapon".equals(weapon.getType());
    }
    
    private Artifact weapon;

    @AssertTrue(message = "Armor must have type 'Armor'")
    private boolean isArmorValid() {
        return armor == null || "Armor".equals(armor.getType());
    }
    
    private Artifact armor;

    @AssertTrue(message = "Helm must have type 'Helm'")
    private boolean isHelmValid() {
        return helm == null || "Helm".equals(helm.getType());
    }

    private Artifact helm;

    private Hero(Builder builder) {
        this.heroName = builder.heroName;
        this.heroClass = builder.heroClass;
        this.level = builder.level;
        this.experience = builder.experience;
        this.attack = builder.getAttack();
        this.defense = builder.defense;
        this.hitPoints = builder.getHitPoints();
        this.health = this.hitPoints;
        this.weapon = builder.weapon;
        this.armor = builder.armor;
        this.helm = builder.helm;
    }

    static private int getArtifactBonus(Artifact artifact) {
        if (artifact != null) {
            return artifact.getBonus();
        }
        return 0;
    }

    public String getName() {
        return heroName;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public String getCompleteName() {
        return heroName + " the " + heroClass;
    }

    public int getLevel() {
        return level;
    }

    public int LevelUp() {
        level++;
        switch (heroClass.toLowerCase()) {
            default:
                attack += 1;
                defense += 1;
                hitPoints += 10;
                break;
        }
        return level;
    }

    public int getRequiredExperienceForLevelUp() {
        return level * 1000 + (level - 1) * (level - 1) * 450;
    }

    public int getExperience() {
        return experience;
    }

    public int gainExperience(int exp) {
        experience += exp;
        while (experience >= getRequiredExperienceForLevelUp()) {
            experience -= getRequiredExperienceForLevelUp();
            LevelUp();
        }
        return experience;
    }

    public int getAttack() {
        return attack + getArtifactBonus(weapon);
    }

    public int getDefense() {
        return defense + getArtifactBonus(armor);
    }

    public int getHitPoints() {
        return hitPoints + getArtifactBonus(helm);
    }

    public int getHealth() {
        return health;
    }

    public int takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
        return health;
    }

    public int heal() {
        health = getHitPoints();
        return health;
    }

    public Artifact getWeapon() {
        return weapon;
    }

    public Artifact getArmor() {
        return armor;
    }

    public Artifact getHelm() {
        return helm;
    }

    public void equipArtifact(Artifact artifact) {
        if (artifact == null) {
            return;
        }
        switch (artifact.getType()) {
            case "Weapon":
                weapon = artifact;
                break;
            case "Armor":
                armor = artifact;
                break;
            case "Helm":
                helm = artifact;
                break;
            default:
                throw new IllegalArgumentException("Invalid artifact type: " + artifact.getType());
        }
    }

    public void attack(Villain villain) {
        villain.getAttacked(this.getAttack());
    }

    public void getAttacked(int damage) {
        int damageTaken = damage - this.getDefense();
        if (damageTaken < 0) {
            damageTaken = 0;
        }
        this.takeDamage(damageTaken);
    }

    @Override
    public String toString() {
        return heroName + " the " + heroClass + ", Level " + level + ", " + experience + "XP" + ", Attack: " + attack + " + " + getArtifactBonus(weapon) + " = " + getAttack() + ", Defense: " + defense + " + " + getArtifactBonus(armor) + " = " + getDefense() + ", Hit Points: " + hitPoints + " + " + getArtifactBonus(helm) + " = " + getHitPoints() + "\n" + this.health + "/" + getHitPoints() + " HP\n" + "Equipped with: " + (weapon != null ? weapon : "No Weapon") + ", " + (armor != null ? armor : "No Armor") + ", " + (helm != null ? helm : "No Helm");
    }

    public String toStatsString() {
        return "[" + getCompleteName() + "🃏]\n" + getLvlString() + " " + getXPString() + "\n" + getHPString() + "\n" + "⚔️ " + getAttack() + " 🛡️ " + getDefense() + "\n" + getEquippedString();
    }

    public String getHPString() {
        return health + "/" + getHitPoints() + "HP🩸";
    }

    public String getXPString() {
        return experience + "/" + getRequiredExperienceForLevelUp() + "XP✨";
    }

    public String getLvlString() {
        return "Lvl:" + level;
    }

    public String getEquippedString() {
        return getHelmString() + "\n" + getArmorString() + "\n" + getWeaponString();
    }

    public String getHelmString() {
        if (helm != null) {
            return "🪖  " + helm.toString();
        }
        return "🦲 No Helm";
    }

    public String getArmorString() {
        if (armor != null) {
            return "🧥 " + armor.toString();
        }
        return "👕 No Armor";
    }

    public String getWeaponString() {
        if (weapon != null) {
            return "🗡️  " + weapon.toString();
        }
        return "👊 No Weapon";
    }

    public static class Builder {
        private String heroName = "Hero";
        private String heroClass = "Hero";
        private int level = 1;
        private int experience = 0;
        private int attack = 0;
        private int defense = 0;
        private int hitPoints = 0;
        private Artifact weapon = null;
        private Artifact armor = null;
        private Artifact helm = null;

        public Builder heroName(String heroName) {
            this.heroName = heroName;
            return this;
        }

        public Builder heroClass(String heroClass) {
            this.heroClass = heroClass;
            return this;
        }

        public Builder level(int level) {
            this.level = level;
            return this;
        }

        public Builder experience(int experience) {
            this.experience = experience;
            return this;
        }

        public Builder attack(int attack) {
            this.attack = attack;
            return this;
        }

        public int getAttack() {
            if (attack != 0) {
                return attack;
            }
            switch (heroClass.toLowerCase()) {
                default:
                    return 10 + (level * 2);
            }
        }

        public Builder defense(int defense) {
            this.defense = defense;
            return this;
        }

        public Builder hitPoints(int hitPoints) {
            this.hitPoints = hitPoints;
            return this;
        }

        public int getHitPoints() {
            if (hitPoints != 0) {
                return hitPoints;
            }
            switch (heroClass.toLowerCase()) {
                default:
                    return 100 + (level * 10);
            }
        }

        public Builder weapon(Artifact weapon) {
            this.weapon = weapon;
            return this;
        }

        public Builder armor(Artifact armor) {
            this.armor = armor;
            return this;
        }

        public Builder helm(Artifact helm) {
            this.helm = helm;
            return this;
        }

        public Hero build() {
            return new Hero(this);
        }
    }
 }