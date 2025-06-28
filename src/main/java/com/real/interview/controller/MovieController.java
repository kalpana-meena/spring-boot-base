package com.real.interview.controller;

import com.real.interview.Exception.MovieNotFoundException;
import com.real.interview.dto.MovieRequestDTO;
import com.real.interview.dto.MovieResponseDTO;
import com.real.interview.model.Movie;
import com.real.interview.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieResponseDTO> createMovie(@RequestBody MovieRequestDTO movieRequestDTO) {
        MovieResponseDTO movieResponseDTO = movieService.create(movieRequestDTO);
        return ResponseEntity.ok(movieResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<MovieResponseDTO>> getAllMovies() {
        List<MovieResponseDTO> movieResponseDTO = movieService.getAllMovies();
        return ResponseEntity.ok(movieResponseDTO);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<MovieResponseDTO> updateMovie(@PathVariable Long id, @Valid @RequestBody MovieRequestDTO movieRequestDTO) throws MovieNotFoundException
//    {
//        MovieResponseDTO movieResponseDTO = movieService.updateMovie(id, movieRequestDTO);
//        return ResponseEntity.ok(movieResponseDTO);
//    }

    @GetMapping("/{title}/{year}")
    public ResponseEntity<MovieResponseDTO> findMovieUsingTitleAndReleaseYear(@PathVariable String title, @PathVariable int year) {
        MovieResponseDTO movieResponseDTO = movieService.findMovieUsingTitleAndReleaseYear(title, year);
        return ResponseEntity.ok(movieResponseDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> updateMovieTitle(@PathVariable Long id, @RequestBody String title) {
        MovieResponseDTO movieResponseDTO = movieService.updateMovieTitle(id, title);
        return ResponseEntity.ok(movieResponseDTO);
    }

}
