package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    public Page<MovieDTO> findPages(Pageable pageable, Long genreId){

        Genre genre = (genreId == 0) ? null : genreRepository.getOne(genreId);
        Page<Movie> page = repository.findByGenre(pageable, genre);
        return page.map(m -> new MovieDTO(m));
    }

    @Transactional(readOnly = true)
    public MovieCardDTO findById(Long id){
        Movie movie = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie does not exists"));
        return new MovieCardDTO(movie);
    }

    @Transactional(readOnly = true)
    public List<ReviewDTO> findReviews(Long movieId){
        Movie movie = repository.getOne(movieId);
        List<Review> list = reviewRepository.findByMovie(movie);
        return list.stream().map(r -> new ReviewDTO(r)).collect(Collectors.toList());
    }
}
