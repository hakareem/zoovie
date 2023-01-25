package com.zoovie.controller;

import com.zoovie.model.Movie;
import com.zoovie.service.MovieService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> allMovies() {
        return new ResponseEntity<List<Movie>>(movieService.allMovies(), HttpStatus.OK);
    }

    @GetMapping("/{imdbId}")
    public ResponseEntity<Movie> singleMovie(@PathVariable String imdbId) throws Exception {
        Movie movie = movieService.findMovieByImdbId(imdbId).orElseThrow(() -> new Exception("Movie not found"));
        return new ResponseEntity<Movie>(movie, HttpStatus.OK);
    }
}
