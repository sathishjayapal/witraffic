package me.sathish.witrafficreader.repo;

import me.sathish.witrafficreader.core.models.TrafficRoadData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrafficRoadDataRepo extends JpaRepository<TrafficRoadData, Integer> {
    Optional<TrafficRoadData> findByRoadwayName(String roadwayName);
}
