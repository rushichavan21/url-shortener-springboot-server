package com.rushi.urlShortener.repository;

import com.rushi.urlShortener.entity.ShortUrl;
import com.rushi.urlShortener.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ShortUrlRepository extends MongoRepository<ShortUrl, ObjectId> {
    Optional<ShortUrl> findByShortUrl(String shortUrl);
    boolean existsByShortUrl(String shortUrl);
    List<ShortUrl> findByUser(User user);

}
