package com.real.interview.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MovieRequestDTO {

    @JsonProperty
    @NotBlank(message = "title should not be blank")
    private String title;

    @JsonProperty("release_year")
    @Min(4)
    @Max(4)
    private int releaseYear;
}
