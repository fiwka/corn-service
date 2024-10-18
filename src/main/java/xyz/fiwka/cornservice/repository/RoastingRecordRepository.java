package xyz.fiwka.cornservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.fiwka.cornservice.entity.RoastingRecord;

import java.util.UUID;

public interface RoastingRecordRepository extends JpaRepository<RoastingRecord, Long> {

    @Query(value = "SELECT 100.0 - SUM(output_weight) * 100.0 / SUM(weight) FROM roasting_records WHERE team_uuid = :teamId", nativeQuery = true)
    double getLossPercentByTeamId(@Param("teamId") UUID teamId);

    @Query(value = "SELECT 100.0 - SUM(output_weight) * 100.0 / SUM(weight) FROM roasting_records WHERE team_uuid = :teamId AND country = :country", nativeQuery = true)
    double getLossPercentByTeamIdAndCountry(@Param("teamId") UUID teamId, @Param("country") String country);
}
