package org.outlaw.avito.repositories;

import org.outlaw.avito.models.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdRepository extends JpaRepository<Ad, UUID> {

}
