package com.example.moviecatalogservice.Controllers;

import com.example.moviecatalogservice.Models.CatalogItem;
import com.example.moviecatalogservice.Services.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource
{
    @Autowired
    private CatalogService catalogService;

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        //get all rated movie ids

        //for each movie id call movie info service and get the details
        return catalogService.getCatalogItems(userId);
    }
}
