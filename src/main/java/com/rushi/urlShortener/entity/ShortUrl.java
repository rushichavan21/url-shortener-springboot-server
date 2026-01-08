package com.rushi.urlShortener.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "urls")
public class ShortUrl {
    @Id
    private ObjectId id;

    @Indexed
    private String originalUrl;

    @Indexed(unique = true)
    private String shortUrl;

    private long clicks = 0;

    private LocalDateTime createdAt = LocalDateTime.now();

    /**
     * Nullable → means URL created without authentication
     */
    @DBRef(lazy = true)
    private User user;
}
