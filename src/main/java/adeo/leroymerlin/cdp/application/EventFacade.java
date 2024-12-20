package adeo.leroymerlin.cdp.application;

import adeo.leroymerlin.cdp.application.dto.EventResponse;
import adeo.leroymerlin.cdp.application.dto.EventUpdateRequest;
import adeo.leroymerlin.cdp.application.mapper.EventMapper;
import adeo.leroymerlin.cdp.domain.Event;
import adeo.leroymerlin.cdp.domain.EventServiceInterface;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * classe Facade pour Event qui permet de faire le lien entre le controller et le service
 */
@Component
public class EventFacade {

    private final EventServiceInterface eventServiceInterface;
    private final EventMapper eventMapper;

    public EventFacade(EventServiceInterface eventServiceInterface, EventMapper eventMapper) {
        this.eventServiceInterface = eventServiceInterface;
        this.eventMapper = eventMapper;
    }

    public List<EventResponse> getEvents() {
        return eventMapper.mapToEventResponse(eventServiceInterface.getEvents());
    }

    public List<EventResponse> getFilteredEvents(String query) {
        return eventMapper.mapToEventResponseWithChildNumber(eventServiceInterface.getFilteredEvents(query));
    }

    public void delete(Long id) {
        eventServiceInterface.delete(id);
    }

    public void updateEvent(EventUpdateRequest eventUpdateRequest) {
        Event event = eventMapper.mapToEvent(eventUpdateRequest);
        eventServiceInterface.updateEvent(event);
    }
}
