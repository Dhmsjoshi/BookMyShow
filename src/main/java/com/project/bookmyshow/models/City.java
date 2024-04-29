package com.project.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class City extends BaseModel{
    private String name;

    @OneToMany(mappedBy = "city") // name of the attribute in other class that is representing the relation
    private List<Theatre> theatres;

}
