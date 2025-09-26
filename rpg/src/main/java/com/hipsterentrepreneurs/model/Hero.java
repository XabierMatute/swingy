/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Hero.java                                          :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: xmatute- <xmatute-@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/09/26 12:21:54 by xmatute-          #+#    #+#             */
/*   Updated: 2025/09/26 13:45:44 by xmatute-         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package com.hipsterentrepreneurs.model;

public class Hero {
    private final String heroName;
    private final String heroClass;
    private  int level;

    private Hero(Builder builder) {
        this.heroName = builder.heroName;
        this.heroClass = builder.heroClass;
        this.level = builder.level;
    }

    public String getName() {
        return heroName;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return heroName + " the " + heroClass + ", Level " + level;
    }

    public static class Builder {
        private String heroName = "Hero";
        private String heroClass = "Hero";
        private int level = 1;

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

        public Hero build() {
            return new Hero(this);
        }
    }
}