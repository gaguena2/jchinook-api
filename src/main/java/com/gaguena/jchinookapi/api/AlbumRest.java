package com.gaguena.jchinookapi.api;

import com.gaguena.jchinookapi.data.Album;
import com.gaguena.jchinookapi.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("albums")
public class AlbumRest {

    @Autowired
    private AlbumRepository albumRepository;

    @GetMapping("/{code}")
    public ResponseEntity<Album> get(@PathVariable Integer code) {
        return albumRepository.findBy(code).map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
