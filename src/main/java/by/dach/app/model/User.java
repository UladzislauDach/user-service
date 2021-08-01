package by.dach.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table (name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;
    @OneToOne (cascade = CascadeType.ALL)
    private Role role;
}
