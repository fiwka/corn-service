package xyz.fiwka.cornservice.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "roasting_records")
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class RoastingRecord {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private final Team team;
    private final String country;
    private final String cornType;
    private final long weight;
    private final long outputWeight;
}
