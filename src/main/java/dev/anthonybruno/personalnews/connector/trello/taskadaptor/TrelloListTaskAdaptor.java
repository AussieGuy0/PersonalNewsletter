package dev.anthonybruno.personalnews.connector.trello.taskadaptor;

import com.julienvey.trello.domain.TList;
import dev.anthonybruno.personalnews.connector.trello.TrelloCard;
import dev.anthonybruno.personalnews.connector.trello.TrelloList;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class TrelloListTaskAdaptor implements TrelloList {

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

            @Override
            public void delete() {
                card.delete();
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

    @Override
    public void deleteAllCards() {
        for (TrelloCard card : cards) {
            card.delete();
        }
    }

}
