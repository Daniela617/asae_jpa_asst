package co.edu.unicauca.asae.taller_jpa_asst.taller_jpa_asst.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.unicauca.asae.taller_jpa_asst.taller_jpa_asst.models.Telefono;

@Repository
public interface TelefonosRepository extends CrudRepository<Telefono, Integer> {
}
