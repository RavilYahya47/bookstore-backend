package org.orient.bookstorebackend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Table(name = "author_details")
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(exclude = "author")
public class AuthorDetails {

    @Id
    @Column(name = "author_id")
    private Long id;

    @NotBlank
    @Column(length = 128, nullable = false, unique = true)
    String name;

    @Lob
    @Column(length = 4096)
    String bio;

    @Column(nullable = false)
    Boolean active = Boolean.TRUE;

    @MapsId
    @OneToOne
    @JoinColumn(name = "author_id")
    Author author;

}