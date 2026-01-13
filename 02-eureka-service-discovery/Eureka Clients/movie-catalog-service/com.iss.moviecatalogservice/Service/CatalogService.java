package com.example.moviecatalogservice.Services;

import com.example.moviecatalogservice.Models.CatalogItem;
import com.example.moviecatalogservice.Models.Movie;
import com.example.moviecatalogservice.Models.UserRating;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogService
{
    @Autowired
    private RestTemplate restTemplate;
    private List<CatalogItem> catalogItems;
    public CatalogService()
    {
        catalogItems = new ArrayList<>();
        catalogItems.add(CatalogItem.builder().name("Breaking Bad").desc("Crime Drama").rating(5).build());
        catalogItems.add(CatalogItem.builder().name("Dark").desc("Sci-Fi Thriller").rating(7).build());
        catalogItems.add(CatalogItem.builder().name("Interstellar").desc("Space & Time").rating(10).build());
    }
    public List<CatalogItem> getCatalogItems(String userId)
    {
        //return catalogItems;

        //Call Rating Data Service
        UserRating userRating = restTemplate.getForObject(
                "http://ratings-data-service/rating/users/" + userId,
                UserRating.class
        );

        //Call Movie Info Service for each movieId
        return userRating.getRatings().stream().map(rating -> {

            Movie movie = restTemplate.getForObject(
                    "http://movie-info-service/movies/" + rating.getMovieId(),
                    Movie.class
            );

            return CatalogItem.builder()
                    .name(movie.getName())
                    .desc("Description not available")
                    .rating(rating.getRating())
                    .build();

        }).toList();
    }

}
