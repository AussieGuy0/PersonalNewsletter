package me.anthonybruno.personalnewsletter.connector.trello;

import java.time.Instant;

public interface TrelloCard {

    String getName();

    Instant getLastModified();
}
