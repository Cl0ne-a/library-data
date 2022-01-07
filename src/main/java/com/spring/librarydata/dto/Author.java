package com.spring.librarydata.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Author {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "author",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Book> books;
}
