package com.badlogic.drop.component;

import com.badlogic.ashley.core.Component;

public class Collectible implements Component {
    private int score = 1;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
