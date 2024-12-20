package adeo.leroymerlin.cdp.web;

import adeo.leroymerlin.cdp.application.EventFacade;
import adeo.leroymerlin.cdp.application.dto.EventResponse;
import adeo.leroymerlin.cdp.application.dto.EventUpdateRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventFacade eventFacade;

    public EventController(EventFacade eventFacade) {
        this.eventFacade = eventFacade;
    }

    @GetMapping(value = "/")
    public List<EventResponse> findEvents() {
        return eventFacade.getEvents();
    }

    @GetMapping(value = "/search/{query}")
    public List<EventResponse> findEvents(@PathVariable String query) {
        return eventFacade.getFilteredEvents(query);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventFacade.delete(id);
    }

    //Le PathVariable est inutile ici, non recommandé lors d'un put
    //il n'a pas été supprimé pour ne pas casser le client (front)
    @PutMapping(value = "/{id}")
    public void updateEvent(@RequestBody EventUpdateRequest eventUpdateRequest) {
        eventFacade.updateEvent(eventUpdateRequest);

    }
}
