package adeo.leroymerlin.cdp;

import adeo.leroymerlin.cdp.domain.Band;
import adeo.leroymerlin.cdp.domain.Event;
import adeo.leroymerlin.cdp.domain.Member;

import java.util.Set;

public abstract class EventDataFactory {

    public static Event createEventWithMetallica() {
        Member member1 = new Member();
        member1.setName("James Hetfield");
        Member member2 = new Member();
        member2.setName("Lars Ulrich");
        Member member3 = new Member();
        member3.setName("Kirk Hammett");

        Band metallica = new Band();
        metallica.setName("Metallica");
        metallica.setMembers(Set.of(member1, member2, member3));

        Event event = new Event();
        event.setId(1L);
        event.setTitle("GrasPop Metal Meeting");
        event.setNbStars(4);
        event.setComment("Awesome metal event!");
        event.setBands(Set.of(metallica));

        return event;
    }

    // Création d'un événement avec Slayer
    public static Event createEventWithSlayer() {
        Member member1 = new Member();
        member1.setName("Tom Araya");
        Member member2 = new Member();
        member2.setName("Kerry King");
        Member member3 = new Member();
        member3.setName("Jeff Hanneman");

        Band slayer = new Band();
        slayer.setName("Slayer");
        slayer.setMembers(Set.of(member1, member2, member3));

        Event event = new Event();
        event.setId(2L);
        event.setTitle("Extreme Metal Fest");
        event.setNbStars(5);
        event.setComment("A killer metal lineup!");
        event.setBands(Set.of(slayer));

        return event;
    }

    // Création d'un événement avec Iron Maiden
    public static Event createEventWithIronMaiden() {
        Member member1 = new Member();
        member1.setName("Bruce Dickinson");
        Member member2 = new Member();
        member2.setName("Steve Harris");
        Member member3 = new Member();
        member3.setName("Dave Murray");

        Band ironMaiden = new Band();
        ironMaiden.setName("Iron Maiden");
        ironMaiden.setMembers(Set.of(member1, member2, member3));

        Event event = new Event();
        event.setId(3L);
        event.setTitle("Heavy Metal Glory");
        event.setNbStars(3);
        event.setComment("A classic metal show!");
        event.setBands(Set.of(ironMaiden));

        return event;
    }

    // Création d'un événement avec AC/DC
    public static Event createEventWithACDC() {
        Member member1 = new Member();
        member1.setName("Angus Young");
        Member member2 = new Member();
        member2.setName("Brian Johnson");
        Member member3 = new Member();
        member3.setName("Cliff Williams");

        Band acdc = new Band();
        acdc.setName("AC/DC");
        acdc.setMembers(Set.of(member1, member2, member3));

        Event event = new Event();
        event.setId(4L);
        event.setTitle("Rock n' Roll Legends");
        event.setNbStars(5);
        event.setComment("Rock at its finest!");
        event.setBands(Set.of(acdc));

        return event;
    }

    // Création d'un événement avec Foo Fighters
    public static Event createEventWithFooFighters() {
        Member member1 = new Member();
        member1.setName("Dave Grohl");
        Member member2 = new Member();
        member2.setName("Taylor Hawkins");
        Member member3 = new Member();
        member3.setName("Nate Mendel");

        Band fooFighters = new Band();
        fooFighters.setName("Foo Fighters");
        fooFighters.setMembers(Set.of(member1, member2, member3));

        Event event = new Event();
        event.setId(5L);
        event.setTitle("Alternative Rock Fest");
        event.setNbStars(4);
        event.setComment("Modern rock vibes!");
        event.setBands(Set.of(fooFighters));

        return event;
    }

    // Création d'un événement avec Linkin Park
    public static Event createEventWithLinkinPark() {
        Member member1 = new Member();
        member1.setName("Mike Shinoda");
        Member member2 = new Member();
        member2.setName("Brad Delson");
        Member member3 = new Member();
        member3.setName("Rob Bourdon");

        Band linkinPark = new Band();
        linkinPark.setName("Linkin Park");
        linkinPark.setMembers(Set.of(member1, member2, member3));

        Event event = new Event();
        event.setId(6L);
        event.setTitle("Hybrid Theory Live");
        event.setNbStars(4);
        event.setComment("Great alternative rock band!");
        event.setBands(Set.of(linkinPark));

        return event;
    }

    // Création d'un événement avec Green Day
    public static Event createEventWithGreenDay() {
        Member member1 = new Member();
        member1.setName("Billie Joe Armstrong");
        Member member2 = new Member();
        member2.setName("Mike Dirnt");
        Member member3 = new Member();
        member3.setName("Tré Cool");

        Band greenDay = new Band();
        greenDay.setName("Green Day");
        greenDay.setMembers(Set.of(member1, member2, member3));

        Event event = new Event();
        event.setId(7L);
        event.setTitle("Punk Rock Party");
        event.setNbStars(3);
        event.setComment("Punk rock anthems!");
        event.setBands(Set.of(greenDay));

        return event;
    }

    // Création d'un événement avec Red Hot Chili Peppers
    public static Event createEventWithRedHotChiliPeppers() {
        Member member1 = new Member();
        member1.setName("Anthony Kiedis");
        Member member2 = new Member();
        member2.setName("Flea");
        Member member3 = new Member();
        member3.setName("Chad Smith");

        Band redHotChiliPeppers = new Band();
        redHotChiliPeppers.setName("Red Hot Chili Peppers");
        redHotChiliPeppers.setMembers(Set.of(member1, member2, member3));

        Event event = new Event();
        event.setId(8L);
        event.setTitle("Funky Rock Madness");
        event.setNbStars(5);
        event.setComment("Energetic funky rock!");
        event.setBands(Set.of(redHotChiliPeppers));

        return event;
    }

    // Création d'un événement avec The Offspring
    public static Event createEventWithOffspring() {
        Member member1 = new Member();
        member1.setName("Dexter Holland");
        Member member2 = new Member();
        member2.setName("Noodles");
        Member member3 = new Member();
        member3.setName("Pete Parada");

        Band offspring = new Band();
        offspring.setName("The Offspring");
        offspring.setMembers(Set.of(member1, member2, member3));

        Event event = new Event();
        event.setId(9L);
        event.setTitle("Punk Rock Revolution");
        event.setNbStars(4);
        event.setComment("Energetic punk rock!");
        event.setBands(Set.of(offspring));

        return event;
    }

    // Création d'un événement avec The Rolling Stones
    public static Event createEventWithRollingStones() {
        Member member1 = new Member();
        member1.setName("Mick Jagger");
        Member member2 = new Member();
        member2.setName("Keith Richards");
        Member member3 = new Member();
        member3.setName("Charlie Watts");

        Band rollingStones = new Band();
        rollingStones.setName("The Rolling Stones");
        rollingStones.setMembers(Set.of(member1, member2, member3));

        Event event = new Event();
        event.setId(10L);
        event.setTitle("Classic Rock Legends");
        event.setNbStars(5);
        event.setComment("Iconic rock performance!");
        event.setBands(Set.of(rollingStones));

        return event;
    }
}
