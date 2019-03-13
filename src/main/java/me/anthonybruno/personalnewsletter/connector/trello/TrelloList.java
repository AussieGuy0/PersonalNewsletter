package me.anthonybruno.personalnewsletter.connector.trello;


import java.util.List;

public interface TrelloList {

    String getName();

    List<TrelloCard> getCards();
}
