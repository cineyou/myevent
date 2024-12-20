package adeo.leroymerlin.cdp.domain;

import java.util.List;

public interface EventServiceInterface {

    List<Event> getEvents();

    void delete(Long id);

    List<Event> getFilteredEvents(String query);

    void updateEvent(Event pEvent);
}
