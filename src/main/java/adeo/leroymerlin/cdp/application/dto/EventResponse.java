package adeo.leroymerlin.cdp.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record EventResponse(Long id,
                            String title,
                            String imgUrl,
                            Integer nbStars,
                            String comment,
                            @JsonProperty("bands")
                            List<BandResponse> bandResponseList) {

}
