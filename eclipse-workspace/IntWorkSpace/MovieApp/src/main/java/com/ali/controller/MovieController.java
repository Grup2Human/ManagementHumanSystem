package com.ali.controller;

import com.ali.repository.entity.Movie;
import com.ali.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;
    @GetMapping("/gtrating/{rate}")
    public ResponseEntity<List<Movie>> findAllByRatingGreaterThan(@PathVariable double rate){
        //localhost:8085/movie/gtrating/5 ==> pathVariable
        //localhost:8085/movie/gtrating?rate=5 ==>requestParam
        return  ResponseEntity.ok(movieService.findAllByRatingGreaterThan(rate));
    }
    @GetMapping("/findbyusersgenres/{userid}")
    public  ResponseEntity<List<Movie>> findAllByUsersGenres(@PathVariable Long userid){
        return ResponseEntity.ok(movieService.findAllByUsersGenres(userid));
    }

    @GetMapping("/beforedate")
    public ResponseEntity<List<Movie>> findAllByPremiredBefore(@RequestParam(defaultValue = "2023-03-07") String date){
        return ResponseEntity.ok(movieService.findAllByPremiredBefore(date));
    }

    @GetMapping("/searchbyrating")
    public  ResponseEntity< Object []> searchbyRating(){
        return ResponseEntity.ok(movieService.searchbyRating());
    }
    @GetMapping("/searchbyrating2")
    public ResponseEntity< Object >  searchbyRating(double rate){
        return ResponseEntity.ok(movieService.searchbyRating(rate));
    }
    @GetMapping("/findbyratings/{ratings}")
    public ResponseEntity< List<Movie>> findAllByRatingIn(@PathVariable Double[] ratings){
        return  ResponseEntity.ok(movieService.findAllByRatingIn(ratings));
    }
    @GetMapping("/findbymovienames")
    public ResponseEntity< List<Movie>>  findAllByNameIn(@RequestParam  String [] movieNames ){
        return ResponseEntity.ok( movieService.findAllByNameIn(movieNames));
    }
}