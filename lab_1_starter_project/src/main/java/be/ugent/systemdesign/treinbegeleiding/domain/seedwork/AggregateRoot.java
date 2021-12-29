package be.ugent.systemdesign.treinbegeleiding.domain.seedwork;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class AggregateRoot {
    @Getter
    List<DomainEvent> domainEventList;

    public AggregateRoot(){
        this.domainEventList = new ArrayList<DomainEvent>();
    }

    public void addEventToList(DomainEvent e){
        this.domainEventList.add(e);
    }

    public void clearList(){
        this.domainEventList.clear();
    }
}
