package com.real.interview.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponseDTO {
    private Long Id;

    private String title;

    private int releaseYear;
}
