package xyz.fiwka.cornservice.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.fiwka.cornservice.entity.CornAdmission;

import java.util.List;
import java.util.Optional;

public interface CornAdmissionRepository extends JpaRepository<CornAdmission, Long> {

    Optional<CornAdmission> findCornAdmissionByRobustPercentAndArabicaPercentAndCornTypeAndCountry(int robustPercent, int arabicaPercent, String cornType, String country);
    Optional<CornAdmission> findCornAdmissionByCornTypeAndCountry(String cornType, String country);
    @Query(value = "SELECT SUM(weight) FROM corn_admissions WHERE cornType = :cornType AND country = :country", nativeQuery = true)
    long getRemainsByCornTypeAndCountry(@Param("cornType") String cornType, @Param("country") String country);
    List<CornAdmission> findAllByCornTypeAndCountry(String cornType, String country, Pageable pageable);

}
