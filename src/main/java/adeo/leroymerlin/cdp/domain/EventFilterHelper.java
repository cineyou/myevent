package adeo.leroymerlin.cdp.domain;

import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EventFilterHelper {


    //méthode qui remplace la liste d'un groupe d'un event que part les groupes qui contiennent un membre qui correspond à la recherche
    public void filterBands(Event event, String query) {
        Set<Band> filteredBands = event.getBands().stream()
                .filter(band -> hasMatchingMember(band, query))
                .peek(band -> filterMembers(band, query))
                .collect(Collectors.toSet());
        event.setBands(filteredBands);
    }

    //méthode qui remplace la liste des membres d'un groupe que par les membres qui correspondent à la recherche
    private void filterMembers(Band band, String query) {
        Set<Member> filteredMembers = band.getMembers().stream()
                .filter(member -> member.getName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toSet());
        band.setMembers(filteredMembers);
    }

    //méthode qui vérifie si dans le groupe d'un event il y a un membre qui correspond à la recherche
    public boolean hasMatchingMemberInBand(Event event, String query) {
        return event.getBands() != null && event.getBands().stream()
                .anyMatch(band -> hasMatchingMember(band, query));
    }

    //méthode qui vérifie si un des membres du groupe correspond à la recherche
    private boolean hasMatchingMember(Band band, String query) {
        return band.getMembers() != null && band.getMembers().stream()
                .anyMatch(member -> member.getName().toLowerCase().contains(query.toLowerCase()));
    }


}
