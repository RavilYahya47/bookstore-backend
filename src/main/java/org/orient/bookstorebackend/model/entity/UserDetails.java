package org.orient.bookstorebackend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
//@ToString(exclude = {"addresses"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "user_details")
public class UserDetails {

    @Id
    @Column(name = "user_id")
    Long id;


    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "user_user_details_fl"))
    User user;

    String firstName;
    String lastName;
    Integer age;

//    @OneToMany(mappedBy = "userDetails", fetch = FetchType.EAGER, orphanRemoval = true)
//    List<Address> addresses = new ArrayList<>();


}