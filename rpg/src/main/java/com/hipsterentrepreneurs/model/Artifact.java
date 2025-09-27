/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Artifact.java                                      :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: xmatute- <xmatute-@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/09/26 16:12:54 by xmatute-          #+#    #+#             */
/*   Updated: 2025/09/27 12:09:05 by xmatute-         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package com.hipsterentrepreneurs.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class Artifact {
    @NotNull(message = "Artifact name cannot be null")
    private final String name;
    @NotNull(message = "Artifact type cannot be null")
    @Pattern(regexp = "^(Weapon|Armor|Helm)$", message = "Type must be 'Weapon', 'Armor' or 'Helm'")
    private final String type;
    private final int bonus;

    public Artifact(Builder builder) {
        this.name = builder.name;
        this.type = builder.type;
        this.bonus = builder.bonus;
    }
    
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getBonus() {
        return bonus;
    }

    @Override
    public String toString() {
        return name + " (" + type + " +" + bonus + ")";
    }

    public static class Builder {
        private String name = "artifact";
        private String type;
        private int bonus = 0;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder bonus(int bonus) {
            this.bonus = bonus;
            return this;
        }

        public Artifact build() {
            return new Artifact(this);
        }

        public Artifact builtWeapon() {
            this.type = "Weapon";
            return build();
        }

        public Artifact builtArmor() {
            this.type = "Armor";
            return build();
        }

        public Artifact builtHelm() {
            this.type = "Helm";
            return build();
        }
    }
}
