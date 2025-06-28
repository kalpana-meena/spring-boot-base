package com.real.interview.repository;

import com.real.interview.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Movie findByTitleAndYear(String title, int year);

    @Query("Update movie set title = :title where id=:id")
    Movie updateTitleById(Long id, String title);
}
