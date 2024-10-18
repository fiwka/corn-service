package xyz.fiwka.cornservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "teams")
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    @Id
    private UUID uuid;

}
