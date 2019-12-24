package dev.anthonybruno.personalnews.connector.trello;


public interface TrelloService {

    TrelloList getList(String boardName, String listName);
}
