package by.dach.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Role {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private LocalDateTime createdAt;
    @OneToMany (cascade = CascadeType.ALL)
    @JoinColumn(name = "role")
    public List<Permission> permission;
}