package org.sscholl.bible.common.model;

import javax.persistence.*;

/**
 * Created by Simon on 02.10.2017.
 */
@Entity
@Table
public class Passage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String query;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "PassageDTO{" +
                "title='" + title + '\'' +
                ", query='" + query +
                '}';
    }
}
