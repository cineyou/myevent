package adeo.leroymerlin.cdp;

import adeo.leroymerlin.cdp.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static adeo.leroymerlin.cdp.EventDataFactory.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class EventFunctionalTest {

    @Mock
    private EventRepository eventRepository;

    private List<Event> eventTestList;

    private EventServiceInterface eventService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);

        //tester le filtre directement sans le mocker car il fait partie de la logique métier
        EventFilterHelper eventFilterHelper = new EventFilterHelper();

        eventService = new EventService(eventRepository, eventFilterHelper);

        eventTestList = List.of(
                createEventWithIronMaiden(),
                createEventWithACDC(),
                createEventWithMetallica(),
                createEventWithSlayer(),
                createEventWithFooFighters(),
                createEventWithLinkinPark(),
                createEventWithGreenDay(),
                createEventWithRedHotChiliPeppers(),
                createEventWithOffspring(),
                createEventWithRollingStones()
        );
    }

    @Test
    void testGetEvents_whenNotEmpty_shouldReturnAllEventsList() {

        when(eventRepository.findAll()).thenReturn(eventTestList);

        List<Event> events = eventService.getEvents();

        assertNotNull(events);
        assertEquals(10, events.size());
        verify(eventRepository, times(1)).findAll();
    }

    @Test
    void testGetFilteredEvents_whenLowerCaseMatch_shouldReturnEventsList() {

        when(eventRepository.findAll()).thenReturn(eventTestList);

        List<Event> filteredEvents = eventService.getFilteredEvents("ar");

        assertNotNull(filteredEvents);
        assertEquals(6, filteredEvents.size());
        verify(eventRepository, times(1)).findAll();
    }

    @Test
    void testGetFilteredEvents_whenUpperCaseMatch_shouldReturnEventsList() {

        when(eventRepository.findAll()).thenReturn(eventTestList);

        List<Event> filteredEvents = eventService.getFilteredEvents("AR");

        assertNotNull(filteredEvents);
        assertEquals(6, filteredEvents.size());
        verify(eventRepository, times(1)).findAll();
    }

    @Test
    void testGetFilteredEvents_whenNoMatch_shouldReturnEmptyList() {

        when(eventRepository.findAll()).thenReturn(eventTestList);

        List<Event> filteredEvents = eventService.getFilteredEvents("NonMatching");

        assertTrue(filteredEvents.isEmpty());
        verify(eventRepository, times(1)).findAll();
    }

    @Test
    void testDeleteEvent_whenDelete_shouldDelete() {

        Long eventId = 1L;
        doNothing().when(eventRepository).deleteById(eventId);

        eventService.delete(eventId);

        verify(eventRepository, times(1)).deleteById(eventId);
    }

    @Test
    void testUpdateEvent_whenChangeNbStarsValue_shouldUpdateNbStarsOnly() {

        Event updatedEvent = new Event();
        updatedEvent.setId(1L);
        updatedEvent.setNbStars(5);

        final Event oldEvent = eventTestList.getFirst();

        when(eventRepository.findById(1L)).thenReturn(Optional.of(oldEvent));
        doNothing().when(eventRepository).updateNbStars(1L, 5);

        eventService.updateEvent(updatedEvent);

        // Capture les arguments passés à la méthode updateNbStars pour vérifier que le nombre d'étoiles a été mis à jour
        // sans utilisé une base de donnée de test, rester le plus indépendant possible de l'infra
        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<Integer> nbStarsCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(eventRepository, times(1)).updateNbStars(idCaptor.capture(), nbStarsCaptor.capture());

        assertEquals(5, nbStarsCaptor.getValue());
    }

    @Test
    void testUpdateEvent_whenChangeCommentValue_shouldUpdateCommentOnly() {

        Event updatedEvent = new Event();
        updatedEvent.setId(1L);
        updatedEvent.setComment("Great event!!!!");

        final Event oldEvent = eventTestList.getFirst();

        when(eventRepository.findById(1L)).thenReturn(Optional.of(oldEvent));
        doNothing().when(eventRepository).updateComment(1L, "Great event!!!!");

        eventService.updateEvent(updatedEvent);

        // Capture les arguments passés à la méthode updateComment pour vérifier que le commentaire a été mis à jour
        // sans utilisé une base de donnée de test, rester le plus indépendant possible de l'infra
        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<String> commentCaptor = ArgumentCaptor.forClass(String.class);
        verify(eventRepository, times(1)).updateComment(idCaptor.capture(), commentCaptor.capture());

        assertEquals("Great event!!!!", commentCaptor.getValue());
    }

    @Test
    void testUpdateEvent_whenNoChangeValues_shouldNotUpdate() {

        Event event = eventTestList.getFirst();

        when(eventRepository.findById(event.getId())).thenReturn(Optional.of(event));

        eventService.updateEvent(event);

        verify(eventRepository, times(0)).updateNbStars(event.getId(), event.getNbStars());
        verify(eventRepository, times(0)).updateComment(event.getId(), event.getComment());
    }

    @Test
    void testFindEventById_notFound() {
        Event updatedEvent = new Event();
        updatedEvent.setId(1L);
        updatedEvent.setNbStars(4);
        updatedEvent.setComment("Awesome event!");

        when(eventRepository.findById(1L)).thenReturn(Optional.empty());


        Exception exception = assertThrows(RuntimeException.class, () -> eventService.updateEvent(updatedEvent));
        assertEquals("Event not found", exception.getMessage());
    }
}
