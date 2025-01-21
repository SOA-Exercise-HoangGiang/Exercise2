package com.hoanggiang.hoanggiang.repository;

import com.hoanggiang.hoanggiang.domain.Book;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookCustomRepository {
    private final MongoTemplate mongoTemplate;

    public BookCustomRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Book> searchBooks(String name, Double price, String category, String author) {
        Query query = new Query();

        // find by name
        // "i" => no case-sensitive
        if (name != null && !name.isEmpty()) {
            query.addCriteria(Criteria.where("name").regex(name, "i"));
        }

        // find by price
        if (price != null) {
            query.addCriteria(Criteria.where("price").is(price));
        }

        // find by category
        if (category != null && !category.isEmpty()) {
            query.addCriteria(Criteria.where("category").regex(category, "i"));
        }

        // find by author
        if (author != null && !author.isEmpty()) {
            query.addCriteria(Criteria.where("author").regex(author, "i"));
        }

        return mongoTemplate.find(query, Book.class);
    }
}
