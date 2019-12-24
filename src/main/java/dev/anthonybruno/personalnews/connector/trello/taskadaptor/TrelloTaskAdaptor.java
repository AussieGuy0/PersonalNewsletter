package dev.anthonybruno.personalnews.connector.trello.taskadaptor;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Argument;
import com.julienvey.trello.domain.TList;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.JDKTrelloHttpClient;
import dev.anthonybruno.personalnews.connector.trello.TrelloList;
import dev.anthonybruno.personalnews.connector.trello.TrelloService;

import java.util.List;

public class TrelloTaskAdaptor implements TrelloService {

    private final Trello trelloApi;

    public TrelloTaskAdaptor(String apiKey, String token) {
        trelloApi = new TrelloImpl(apiKey, token, new JDKTrelloHttpClient());
    }

    @Override
    public TrelloList getList(String boardName, String listName) {
        List<TList> lists = trelloApi.getBoard(boardName).fetchLists(new Argument("cards", "open"));

        for (TList list : lists) {
            if (list.getName().equals(listName)) {
                return new TrelloListTaskAdaptor(list);
            }
        }
        throw new IllegalArgumentException("No list found with name: '" + listName + "' in board: " + boardName);
    }

}
