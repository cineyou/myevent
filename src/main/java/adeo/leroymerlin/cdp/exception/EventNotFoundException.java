package adeo.leroymerlin.cdp.exception;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException() {
        super("Event not found");
    }
}
