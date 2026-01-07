package com.rushi.urlShortener.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private ObjectId id;
    private String username = "hello";
    @Indexed(unique = true)
    private String email;
    private String password;
    private LocalDateTime createdAt;

    private String avatar = "https://www.gravatar.com/avatar/11bcd8aeba5c9f04a0f50a4da559de1c?d=identicon";
}
