package org.outlaw.avito.repositories;

import org.outlaw.avito.models.AdCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<AdCategory, UUID> {
}
