package dev.dima.betservice.models.archive;

import dev.dima.betservice.models.Event;
import dev.dima.betservice.models.base.BaseEvent;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;


@Entity
@Table(name = "events_archive")
@NoArgsConstructor
@Getter
@Setter
public class ArchivedEvent extends BaseEvent {

    public ArchivedEvent(Event event) {
        super();
        BeanUtils.copyProperties(event, this);
    }
}
