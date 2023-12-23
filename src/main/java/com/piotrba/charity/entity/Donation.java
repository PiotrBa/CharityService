package com.piotrba.charity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "donations")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"categories", "institutions"})
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "donation_category",
            joinColumns = @JoinColumn(name = "donation_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "institution_id")
    private Institution institutions;
    private String street;
    private String city;
    private String zipCode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime pickUpDateAndTime;
    public String getCreatePickApFormatted(){
        if (pickUpDateAndTime == null){
            return "Not updated";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm | dd/MM/yyyy");
        return formatter.format(pickUpDateAndTime);
    }
    private String pickUpComment;
}
