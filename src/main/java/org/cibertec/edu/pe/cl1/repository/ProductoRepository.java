package org.cibertec.edu.pe.cl1.repository;

import org.cibertec.edu.pe.cl1.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}

