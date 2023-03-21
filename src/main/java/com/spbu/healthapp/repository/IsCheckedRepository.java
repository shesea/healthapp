package com.spbu.healthapp.repository;

import com.spbu.healthapp.entity.IsChecked;
import com.spbu.healthapp.entity.Medicine;
import com.spbu.healthapp.entity.ScheduledEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IsCheckedRepository extends JpaRepository<IsChecked, Integer> {
    @Query(value = "SELECT * FROM is_checked c WHERE c.entity_id = :entityId AND datetime = :datetime",
            nativeQuery = true)
    public IsChecked getCheckedByDate(@Param("entityId") int id, @Param("datetime") String datetime);
}
