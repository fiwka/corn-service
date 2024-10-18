package xyz.fiwka.cornservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "corn_admissions")
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class CornAdmission {

    @Id
    @GeneratedValue
    private Long id;
    private final int robustPercent;
    private final int arabicaPercent;
    private final String cornType;
    private final String country;
    private long weight;
}
