package com.app.silvahnosbe.repositories;

import com.app.silvahnosbe.entities.FacturaEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<FacturaEntity, Long>{
    @Query(value = "SELECT * FROM factura f WHERE "+
            "YEAR(f.fecha_creacion) = :anio and "+
            "MONTH(f.fecha_creacion) = :mes and f.borrado = 0 ORDER BY f.fecha_creacion DESC", nativeQuery = true)
    List<FacturaEntity> obteberFacturas(@Param("anio") int anio, @Param("mes") int mes);

    @Query(value= "SELECT COALESCE(ROUND(SUM(factura.monto) * 0.19, 0), 0)\n" + //
            "FROM factura\n" + //
            "WHERE factura.borrado = 0\n" + //
            "  AND YEAR(factura.fecha_creacion) = :anio\n" + //
            "  AND MONTH(factura.fecha_creacion) = :mes", nativeQuery = true)
    Integer obtenerIva(@Param("anio") int anio, @Param("mes") int mes);

    @Query(value = "select *\n" + //
                    "from factura as f\n" + //
                    "where f.borrado = 0 and (f.estado = 1 or f.estado = 2) \n" + //
                    "order by f.fecha_vencimiento asc\n" + //
                    "limit 3", nativeQuery = true)
    List<FacturaEntity> obtenerProximasVencer(@Param("anio") int anio, @Param("mes") int mes);

    @Query(value="select * from factura where   DATE_ADD(CURDATE(), interval :dias day) >= factura.fecha_vencimiento and factura.borrado=0 and factura.estado=1",nativeQuery = true)
    List<FacturaEntity>facturaV(@Param("dias")int dias);

    @Query(value= "UPDATE  factura set estado =2 where DATE_ADD(CURDATE(), interval :dias day) > factura.fecha_vencimiento and factura.borrado=0 and factura.estado=1",nativeQuery = true)
    void updateEstado(@Param("dias") int dias);

    @Query(value="SELECT * " + 
                 "FROM factura f " +
                 "WHERE f.borrado = 0 AND " +
                 ":fi <= f.fecha_creacion AND " +
                 "f.fecha_creacion <= :ff", nativeQuery = true)
    List<FacturaEntity> obtenerFacturasEntre(@Param("fi") String fechaInicio, @Param("ff") String fechaFin);

    @Query(value="SELECT COALESCE(SUM(f.monto), 0) " + 
                 "FROM factura f " +
                 "WHERE f.borrado = 0 AND " +
                 ":fi <= f.fecha_creacion AND " +
                 "f.fecha_creacion <= :ff", nativeQuery = true)
    Integer obtenerTotalEntre(@Param("fi") String fechaInicio, @Param("ff") String fechaFin);
}
