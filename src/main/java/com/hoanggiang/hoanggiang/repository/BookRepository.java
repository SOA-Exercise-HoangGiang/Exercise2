package com.hoanggiang.hoanggiang.repository;

import com.hoanggiang.hoanggiang.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    boolean existsByName(String name);
}
