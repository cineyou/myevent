package adeo.leroymerlin.cdp.application.mapper;

import adeo.leroymerlin.cdp.application.dto.BandResponse;
import adeo.leroymerlin.cdp.application.dto.EventResponse;
import adeo.leroymerlin.cdp.application.dto.EventUpdateRequest;
import adeo.leroymerlin.cdp.application.dto.MemberResponse;
import adeo.leroymerlin.cdp.domain.Event;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventMapper {

    public Event mapToEvent(EventUpdateRequest eventUpdateRequest) {
        Event event = new Event();
        event.setId(eventUpdateRequest.id());
        event.setNbStars(eventUpdateRequest.nbStars());
        event.setComment(eventUpdateRequest.comment());
        return event;
    }

    public List<EventResponse> mapToEventResponse(List<Event> eventsList) {
        return eventsList.stream()
                .map(event -> new EventResponse(
                        event.getId(),
                        event.getTitle(),
                        event.getImgUrl(),
                        event.getNbStars(),
                        event.getComment(),
                        event.getBands().stream()
                                .map(band -> new BandResponse(band.getName(),
                                        band.getMembers().stream()
                                                .map(member -> new MemberResponse(member.getName()))
                                                .collect(Collectors.toList()))
                                )
                                .collect(Collectors.toList()))
                )
                .collect(Collectors.toList());
    }

    public List<EventResponse> mapToEventResponseWithChildNumber(List<Event> eventsList) {
        return eventsList.stream()
                .map(event -> new EventResponse(
                        event.getId(),
                        event.getTitle() + "[" + event.getBands().size() + "]",
                        event.getImgUrl(),
                        event.getNbStars(),
                        event.getComment(),
                        event.getBands().stream()
                                .map(band -> new BandResponse(band.getName() + "[" + band.getMembers().size() + "]",
                                        band.getMembers().stream()
                                                .map(member -> new MemberResponse(member.getName()))
                                                .collect(Collectors.toList()))
                                )
                                .collect(Collectors.toList()))
                )
                .collect(Collectors.toList());
    }
}
