package com.spring.librarydata.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Genre implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    private String genre;
}
