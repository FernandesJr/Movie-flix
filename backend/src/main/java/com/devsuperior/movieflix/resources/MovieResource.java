package com.devsuperior.movieflix.resources;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Autowired
    private MovieService service;

    @GetMapping
    private ResponseEntity<Page<MovieDTO>> findpaged(
                Pageable pageable,
                @RequestParam(name = "genreId", defaultValue = "0") Long genreId){

        //PageRequest pageRequest = PageRequest.of(0, 12, Sort.Direction.ASC, "title");
        Page<MovieDTO> page = service.findPages(pageable, genreId);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieCardDTO> findById(@PathVariable Long id){
        MovieCardDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{movieId}/reviews")
    public ResponseEntity<List<ReviewDTO>> reviews(@PathVariable(name = "movieId") Long id){
        List<ReviewDTO> list = service.findReviews(id);
        return ResponseEntity.ok(list);
    }
}
