package me.anthonybruno.personalnewsletter.connector.trello;


public interface TrelloService {

    TrelloList getList(String boardName, String listName);
}
