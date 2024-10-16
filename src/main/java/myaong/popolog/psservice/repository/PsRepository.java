package myaong.popolog.psservice.repository;

import myaong.popolog.psservice.entity.Ps;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PsRepository extends JpaRepository<Ps, Long> {
}
