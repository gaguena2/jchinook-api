package com.gaguena.jchinookapi.data;

import java.util.List;

public record Artist(Integer artistId, String name, List<Album> albuns) {
}
