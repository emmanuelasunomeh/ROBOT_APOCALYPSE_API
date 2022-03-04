package com.api.robotapocalypse.repo;

import com.api.robotapocalypse.entity.Inventory;
import com.api.robotapocalypse.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
