package com.project.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;


@Getter
@Setter
@EntityListeners({AuditingEntityListener.class})
@MappedSuperclass//Don't create a separate table, instead put its attributes to every child
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date lastModified;
}
