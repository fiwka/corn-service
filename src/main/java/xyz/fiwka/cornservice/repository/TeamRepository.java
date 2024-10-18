package xyz.fiwka.cornservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.fiwka.cornservice.entity.Team;

import java.util.UUID;

public interface TeamRepository extends JpaRepository<Team, UUID> {

    Team getTeamByUuid(UUID uuid);
}
