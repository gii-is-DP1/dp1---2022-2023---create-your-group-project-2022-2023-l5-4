package org.springframework.samples.petclinic.game;

public enum GameModeEnum {
    THE_TOWER {
        public String toString() {
            return "The Tower";
        }
    },
    THE_WELL {
        public String toString() {
            return "The Well";
        }
    },
    THE_POISONED_GIFT {
        public String toString() {
            return "The Poisoned Gift";
        }
    }
}
