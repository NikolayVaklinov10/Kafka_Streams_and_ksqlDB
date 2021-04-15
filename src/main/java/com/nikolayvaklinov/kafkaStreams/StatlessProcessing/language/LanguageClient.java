package com.nikolayvaklinov.kafkaStreams.StatlessProcessing.language;

import com.nikolayvaklinov.kafkaStreams.StatlessProcessing.serialization.Tweet;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public interface LanguageClient {
    public Tweet translate(Tweet tweet, String targetLanguage);

//    public List<EntitySentiment> getEntitySentiment(Tweet tweet);
}

