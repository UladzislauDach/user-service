package by.dach.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Permission {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private String value;
    private LocalDateTime createdAt;
}
