package com.example.shdemo.domain;


import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "owner.all", query = "Select o from Owner o")
})
public class Owner {
    private Long id;

    private String firstName;
    private String lastName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName;}
    public void setFirstName(String firstName) { this.firstName = firstName;}

    public String getLastName() { return lastName;}
    public void setLastName(String lastName) { this.lastName = lastName;}
}
