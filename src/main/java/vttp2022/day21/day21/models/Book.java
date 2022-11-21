package vttp2022.day21.day21.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Book {
    
    private String bookId;
    private String title;
    private String description;
    private Float ratings;
    private String imageUrl;
    private int limit;

    public int getLimit() {
        return limit;
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }
    public String getBookId() {
        return bookId;
    }
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Float getRatings() {
        return ratings;
    }
    public void setRatings(Float ratings) {
        this.ratings = ratings;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static Book create(SqlRowSet rs) {
        Book b = new Book();
        b.setBookId(rs.getString("book_id"));
        b.setDescription(rs.getString("description"));
        b.setRatings(rs.getFloat("rating"));
        b.setImageUrl(rs.getString("image_url"));
        b.setTitle(rs.getString("title"));

        return b;
    }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("bookId", getBookId())
                .add("title", getTitle())
                .add("description", getDescription())
                .add("rating", getRatings())
                .add("image", getImageUrl())
                .build();
    }



}
