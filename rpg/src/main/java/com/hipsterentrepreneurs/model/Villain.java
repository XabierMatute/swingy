/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Villain.java                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: xmatute- <xmatute-@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/09/27 11:50:18 by xmatute-          #+#    #+#             */
/*   Updated: 2025/09/27 12:13:53 by xmatute-         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package com.hipsterentrepreneurs.model;

import jakarta.validation.constraints.*;

public class Villain {
    @NotNull(message = "Villain name cannot be null")
    private final String name;
    @Min(value = 0, message = "Attack must be at least {value}")
    private int attack;
    @Min(value = 0, message = "Defense must be at least {value}")
    private int defense;
    @Min(value = 1, message = "Hit Points must be at least {value}")
    private int hitPoints;

    @Min(value = 0, message = "Health must be at least {value}")
    private int health;

    private Artifact loot;

    private Villain(Builder builder) {
        this.name = builder.name;
        this.attack = builder.attack;
        this.defense = builder.defense;
        this.hitPoints = builder.hitPoints;
        this.health = this.hitPoints;
        this.loot = builder.loot;
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public Artifact getLoot() {
        return loot;
    }

    public void Attack(Hero hero) {
        hero.getAttacked(this.getAttack());
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
        // return name + " the Villain, Attack: " + attack + ", Defense: " + defense + ", Hit Points: " + hitPoints + ", Current Health: " + health + (loot != null ? ", Loot: " + loot : "");
        return "[" + name + "] A:" + attack + " D:" + defense + " " + health + "/" + hitPoints + " HP" + (loot != null ? ", Loot: " + loot : "");
    }

    public static class Builder {
        private String name = "villain";
        private int attack = 5;
        private int defense = 0;
        private int hitPoints = 20;
        private Artifact loot = null;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder attack(int attack) {
            this.attack = attack;
            return this;
        }

        public Builder defense(int defense) {
            this.defense = defense;
            return this;
        }

        public Builder hitPoints(int hitPoints) {
            this.hitPoints = hitPoints;
            return this;
        }

        public Builder loot(Artifact loot) {
            this.loot = loot;
            return this;
        }

        public Villain build() {
            return new Villain(this);
        }
    }
    
}
