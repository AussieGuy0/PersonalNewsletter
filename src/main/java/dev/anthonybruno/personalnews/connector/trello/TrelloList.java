package dev.anthonybruno.personalnews.connector.trello;


import java.util.List;

public interface TrelloList {

    String getName();

    List<TrelloCard> getCards();

    void deleteAllCards();
}
