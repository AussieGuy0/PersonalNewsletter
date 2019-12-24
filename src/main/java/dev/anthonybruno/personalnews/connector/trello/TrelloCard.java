package dev.anthonybruno.personalnews.connector.trello;

import java.time.Instant;

public interface TrelloCard {

    String getName();

    Instant getLastModified();

    void delete();
}
