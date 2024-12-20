package adeo.leroymerlin.cdp.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record BandResponse(String name,
                           @JsonProperty("members")
                           List<MemberResponse> memberResponseList) {


}
