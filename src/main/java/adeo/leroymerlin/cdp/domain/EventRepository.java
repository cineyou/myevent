package adeo.leroymerlin.cdp.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Modifying
    @Query("UPDATE Event e SET e.nbStars = :nbStars WHERE e.id = :id")
    void updateNbStars(@Param("id") Long id, @Param("nbStars") Integer nbStars);

    @Modifying
    @Query("UPDATE Event e SET e.comment = :comment WHERE e.id = :id")
    void updateComment(@Param("id") Long id, @Param("comment") String comment);
}
