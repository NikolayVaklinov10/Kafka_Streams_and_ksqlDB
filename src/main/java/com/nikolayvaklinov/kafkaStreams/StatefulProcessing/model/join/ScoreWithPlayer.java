package com.nikolayvaklinov.kafkaStreams.StatefulProcessing.model.join;

import com.nikolayvaklinov.kafkaStreams.StatefulProcessing.model.Player;
import com.nikolayvaklinov.kafkaStreams.StatefulProcessing.model.ScoreEvent;

public class ScoreWithPlayer {
    private ScoreEvent scoreEvent;
    private Player player;

    public ScoreWithPlayer(ScoreEvent scoreEvent, Player player) {
        this.scoreEvent = scoreEvent;
        this.player = player;
    }

    public ScoreEvent getScoreEvent() {
        return this.scoreEvent;
    }

    public Player getPlayer() {
        return this.player;
    }

    @Override
    public String toString() {
        return "{" + " scoreEvent='" + getScoreEvent() + "'" + ", player='" + getPlayer() + "'" + "}";
    }
}
