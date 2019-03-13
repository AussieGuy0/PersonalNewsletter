package me.anthonybruno.personalnewsletter.connector.trello.taskadaptor;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Argument;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.TList;
import com.julienvey.trello.impl.TrelloImpl;
import me.anthonybruno.personalnewsletter.connector.trello.TrelloCard;
import me.anthonybruno.personalnewsletter.connector.trello.TrelloList;
import me.anthonybruno.personalnewsletter.connector.trello.TrelloService;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class TrelloTaskAdaptor implements TrelloService  {

    private final Trello trelloApi;

    public TrelloTaskAdaptor(String apiKey, String token) {
        trelloApi = new TrelloImpl(apiKey, token);
    }

    @Override
    public TrelloList getList(String boardName, String listName) {
        List<TList> lists = trelloApi.getBoard(boardName).fetchLists(new Argument("cards", "open"));

        for (TList list : lists) {
            if (list.getName().equals(listName)) {
                return new TrelloListTaskAdaptor(list);
            }
        }
        return null;
    }

    private static class TrelloListTaskAdaptor implements TrelloList {
        private final String name;
        private final List<TrelloCard> cards;

        public TrelloListTaskAdaptor(TList tList) {
            this.name = tList.getName();
            this.cards = tList.getCards().stream().map((card -> new TrelloCard() {
                @Override
                public String getName() {
                    return card.getName();
                }

                @Override
                public Instant getLastModified() {
                    return card.getDateLastActivity().toInstant();
                }
            })).collect(Collectors.toList());
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<TrelloCard> getCards() {
            return cards;
        }
    }
}
