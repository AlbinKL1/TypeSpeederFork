package se.ju23.typespeeder.Entitys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatchRepo extends JpaRepository<Patch,Integer> {
}
