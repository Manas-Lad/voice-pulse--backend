package com.semicolons.repository;

import com.semicolons.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findByUserIdOrderByTimestampDesc(Long userId);
    List<Alert> findByDeviceUuidOrderByTimestampDesc(String deviceUuid);
    Optional<Alert> findByShareToken(String shareToken);
}
