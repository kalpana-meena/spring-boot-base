package com.real.interview.service;

import com.real.interview.Exception.MovieNotFoundException;
import com.real.interview.dto.MovieRequestDTO;
import com.real.interview.dto.MovieResponseDTO;
import com.real.interview.model.Movie;
import com.real.interview.repository.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ModelMapper modelMapper;

    public MovieResponseDTO create(MovieRequestDTO movieRequestDTO) {
        Movie movieReq = modelMapper.map(movieRequestDTO, Movie.class);
        Movie movieRes = movieRepository.save(movieReq);
        return modelMapper.map(movieRes, MovieResponseDTO.class);
    }

    public List<MovieResponseDTO> getAllMovies() {
        List<Movie> movieRes = movieRepository.findAll();
        List<MovieResponseDTO> movieResponseDTOList = movieRes.stream()
                .map(movie -> modelMapper.map(movie, MovieResponseDTO.class))
                .collect(Collectors.toList());
        return movieResponseDTOList;
    }

    public MovieResponseDTO updateMovie(Long id, MovieRequestDTO movieRequestDTO) throws MovieNotFoundException{
        Movie movie = movieRepository.findById(id).orElseThrow(() -> {return new MovieNotFoundException("movie not found");});  // can throw custom exception
        // update title
        movie.setTitle(movieRequestDTO.getTitle());
        // update release year
        movie.setReleaseYear(movieRequestDTO.getReleaseYear());
        Movie movieRes = movieRepository.save(movie);
        return modelMapper.map(movieRes, MovieResponseDTO.class);
    }

    public MovieResponseDTO findMovieUsingTitleAndReleaseYear(String title, int year) {
        Movie movie = movieRepository.findByTitleAndYear(title, year);
        return modelMapper.map(movie, MovieResponseDTO.class);
    }

    public MovieResponseDTO updateMovieTitle(Long id, String title) {
        Movie movie = movieRepository.updateTitleById(id, title);
        return modelMapper.map(movie, MovieResponseDTO.class);
    }
}
