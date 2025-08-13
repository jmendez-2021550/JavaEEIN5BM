package com.kinalitosclothes.modelo;

import com.kinalitosclothes.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpleadosDAO {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;

    public Empleados validar(String correoEmpleado, String telefonoEmpleado) {
// instanciar el objeto de la entidad Empleado
        Empleados empleado = new Empleados();
        //agregar una cariable de tipo string para muestra de consulta sql
        String sql = "select * from Empleados where correoEmpleado = ? and telefonoEmpleado = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareCall(sql);
            ps.setString(1, correoEmpleado);
            ps.setString(2, telefonoEmpleado);
            rs = ps.executeQuery();
            while (rs.next()) {
                empleado.setCodigoEmpleado(rs.getInt("codigoEmpleado"));
                empleado.setNombreEmpleado(rs.getString("nombreEmpleado"));
                empleado.setApellidoEmpleado(rs.getString("apellidoEmpleado"));
                empleado.setCorreoEmpleado(rs.getString("correoEmpleado"));
                empleado.setTelefonoEmpleado(rs.getString("telefonoEmpleado"));
            }
        } catch (Exception e) {
            System.out.println("El usuario o contraseña son incorrectos");
            e.printStackTrace();
        }
        return empleado;
    }
    // Operacion Listar
   public List listar() {
    String sql = "select * from Empleados";
    List<Empleados> listaEmpleado = new ArrayList<>();

    try {
        con = cn.Conexion();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

       while (rs.next()) {
        Empleados em = new Empleados();
        em.setCodigoEmpleado(rs.getInt("codigoEmpleado"));
        em.setNombreEmpleado(rs.getString("nombreEmpleado"));
        em.setApellidoEmpleado(rs.getString("apellidoEmpleado"));
        em.setCorreoEmpleado(rs.getString("correoEmpleado"));
        em.setTelefonoEmpleado(rs.getString("telefonoEmpleado"));
        em.setDireccionEmpleado(rs.getString("direccionEmpleado"));
        em.setCodigoUsuario(rs.getInt("codigoUsuario"));
        listaEmpleado.add(em);
}

    } catch (Exception e) {
        e.printStackTrace();
    }

    return listaEmpleado;
}
// MÉTODO AGREGAR
public int agregar(Empleados emp) {
    String sql = "insert into Empleados (nombreEmpleado, apellidoEmpleado, correoEmpleado, telefonoEmpleado, direccionEmpleado, codigoUsuario) values (?, ?, ?, ?, ?, ?)";
    try {
        con = cn.Conexion();
        ps = con.prepareStatement(sql);
        ps.setString(1, emp.getNombreEmpleado());
        ps.setString(2, emp.getApellidoEmpleado());
        ps.setString(3, emp.getCorreoEmpleado());
        ps.setString(4, emp.getTelefonoEmpleado());
        ps.setString(5, emp.getDireccionEmpleado());
        ps.setInt(6, emp.getCodigoUsuario());
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return resp;
}

}

