package com.real.interview.service;

import com.real.interview.dto.MovieRequestDTO;
import com.real.interview.dto.MovieResponseDTO;
import com.real.interview.model.Movie;
import com.real.interview.repository.MovieRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class MovieServiceTest {

    @Mock
    MovieRepository movieRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    MovieService movieService;

    @Test
    public void createMovieTest() {
        MovieRequestDTO requestDTO = MovieRequestDTO.builder()
                .title("Hum Tum")
                .releaseYear(1995)
                .build();

        Movie movieReqMap = Movie.builder()
                .title(requestDTO.getTitle())
                .releaseYear(requestDTO.getReleaseYear())
                .build();

        Movie movieRes = Movie.builder()
                .title(requestDTO.getTitle())
                .releaseYear(requestDTO.getReleaseYear())
                .Id(1L)
                .build();

        MovieResponseDTO responseDto = MovieResponseDTO.builder()
                .title(movieRes.getTitle())
                .releaseYear(movieRes.getReleaseYear())
                .Id(movieRes.getId())
                .build();

        Mockito.when(modelMapper.map(requestDTO, Movie.class)).thenReturn(movieReqMap);
        Mockito.when(modelMapper.map(movieRes, MovieResponseDTO.class)).thenReturn(responseDto);

        Mockito.when(movieRepository.save(movieReqMap)).thenReturn(movieRes);

        MovieResponseDTO response = movieService.create(requestDTO);

        Assert.assertEquals(1995, response.getReleaseYear());
        Assert.assertNotNull("Id is not null", response.getId());
        Assert.assertEquals("Hum Tum", response.getTitle());
    }

    @Test
    public void findMovieUsingTitleAndReleaseYearTest() {
        String title = "Iron Man";
        int year = 2001;

        Movie movieRes = Movie.builder()
                .title(title)
                .releaseYear(year)
                .Id(1L)
                .build();

        MovieResponseDTO responseDto = MovieResponseDTO.builder()
                .title(movieRes.getTitle())
                .releaseYear(movieRes.getReleaseYear())
                .Id(movieRes.getId())
                .build();
        Mockito.when(movieRepository.findByTitleAndYear(title, year)).thenReturn(movieRes);
        Mockito.when(modelMapper.map(movieRes, MovieResponseDTO.class)).thenReturn(responseDto);


        MovieResponseDTO response = movieService.findMovieUsingTitleAndReleaseYear(title, year);

        Assert.assertNotNull("Id should not be null", response.getId());
        Assert.assertEquals("Iron Man", response.getTitle());
        Assert.assertEquals(2001, response.getReleaseYear());
    }
}
