package cn.xilio.leopard.domain.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;
@Getter
public class GroupDeleteEvent extends ApplicationEvent {

    private final List<String> groupIds;
    public GroupDeleteEvent(Object source, List<String> groupIds) {
        super(source);
        this.groupIds = groupIds;
    }
}
