package adeo.leroymerlin.cdp.domain;


import adeo.leroymerlin.cdp.exception.EventNotFoundException;
import adeo.leroymerlin.cdp.exception.GlobalBusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService extends GenericConditionalUpdateField<Event> implements EventServiceInterface {

    private final EventRepository eventRepository;
    private final EventFilterHelper eventFilterHelper;

    public EventService(EventRepository eventRepository, EventFilterHelper eventFilterHelper) {
        this.eventRepository = eventRepository;
        this.eventFilterHelper = eventFilterHelper;
    }

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    @Transactional
    public void delete(Long id) {
        eventRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Event> getFilteredEvents(String query) {
        // Filter the events list in pure JAVA here
        return eventRepository.findAll().stream()
                .filter(event -> eventFilterHelper.hasMatchingMemberInBand(event, query))
                .peek(event -> eventFilterHelper.filterBands(event, query))
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateEvent(Event pEvent) {
        Long id = pEvent.getId();
        Event oldEvent = findEventById(id);

        conditionalUpdateField(pEvent,
                newEvent -> newEvent.getNbStars() != null && !newEvent.getNbStars().equals(oldEvent.getNbStars()),
                newEvent -> updateNbStars(newEvent.getId(), newEvent.getNbStars()));

        conditionalUpdateField(pEvent,
                newEvent -> newEvent.getComment() != null && !newEvent.getComment().equals(oldEvent.getComment()),
                newEvent -> updateComment(newEvent.getId(), newEvent.getComment()));
    }

    private Event findEventById(Long id) {
        return eventRepository.findById(id).orElseThrow(EventNotFoundException::new);
    }

    private void updateNbStars(Long id, Integer nbStars) {
        if (nbStars < 0 || nbStars > 5) {
            throw new GlobalBusinessException("nbStars must be positive and less than 5");
        }
        eventRepository.updateNbStars(id, nbStars);
    }

    private void updateComment(Long id, String comment) {
        if (comment.length() > 255) {
            throw new GlobalBusinessException("comment must be less than 255 characters");
        }
        eventRepository.updateComment(id, comment);
    }

}
