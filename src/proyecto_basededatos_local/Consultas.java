/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_basededatos_local;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author gerso_000
 */
public class Consultas {
    private DataSource ds;
    private Connection conn;
    
    private Connection connectDB(){
        ds = Datasource.getSQLDataSource();
        conn = null;
        try {
            conn = this.ds.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
    private void disconnectDB(Connection conn){
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getConsultaIngreso(String correo, String contrasena){
        pantallas es = new pantallas();
        String back="";
        conn = connectDB();
        String query = "select * from USUARIOS where nombre=? and password=?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps= conn.prepareStatement(query);
            ps.setString(1, correo);
            ps.setString(2, contrasena);
            rs = ps.executeQuery();
            while (rs.next()){
                back=rs.getString(2);
                String nom=rs.getString(2);
                
                if (back.equals(correo)){
                    
                if (ps != null) {
                    ps.close();
                }
                if (rs != null){
                    rs.close();
                }
                if (conn != null) {
                    disconnectDB(conn);
                }
                }
            }
                return back;
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            disconnectDB(conn);
        }    
        return back;
    }
    
    public String getConsultaIngresoContra(String correo, String contrasena){
        pantallas es = new pantallas();
        String back="";
        conn = connectDB();
        String query = "select * from USUARIOS where nombre=? and password=?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps= conn.prepareStatement(query);
            ps.setString(1, correo);
            ps.setString(2, contrasena);
            rs = ps.executeQuery();
            while (rs.next()){
                back=rs.getString(3);
                String nom=rs.getString(2);
                
                if (back.equals(correo)){
                    
                if (ps != null) {
                    ps.close();
                }
                if (rs != null){
                    rs.close();
                }
                if (conn != null) {
                    disconnectDB(conn);
                }
                }
            }
                return back;
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            disconnectDB(conn);
        }    
        return back;
    }
    
    public int insertUsuarios(String correo,String contraseña){
        conn = connectDB();
        String query = " insert into USUARIOS(nombre, password) values (?,?) ";
        PreparedStatement preStmt =null;
        try {
            preStmt = conn.prepareStatement(query);
            preStmt.setString(1, correo);
            preStmt.setString(2, contraseña);
            boolean result = preStmt.execute();
            if(result){
                return 0;
            }
            else{
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if (preStmt != null) {
                    preStmt.close();
                }
                if (conn != null) {
                    disconnectDB(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
    
    public String getConsultaUsuarios(){
        conn = connectDB();
        
        String query = "select * from USUARIOS";
        PreparedStatement consulta = null;
        ResultSet resultadotabla = null;
        String w = "";
        StringBuilder tabla = new StringBuilder(w);
        
        try{
            consulta = conn.prepareStatement(query);
            resultadotabla = consulta.executeQuery();
            tabla.append("tid|    \tcorreo|\n");
            while (resultadotabla.next()){
                tabla.append(resultadotabla.getString(1)).append("\t");
                tabla.append(resultadotabla.getString(2)).append("\n");
            }
          return tabla.toString();
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tabla.toString();
    }
    
    public String getConsultaid(String correo){
        conn = connectDB();
        String query = " select * from USUARIOS where nombre=?";
        PreparedStatement consulta = null;
        ResultSet resultadotabla = null;
        String id="";
        try{
            consulta = conn.prepareStatement(query);
            consulta.setString(1, correo);
            resultadotabla = consulta.executeQuery();
            id=resultadotabla.getString(1);
            while (resultadotabla.next()){
                
                if (id.equals(correo)){
                    
                if (consulta != null) {
                    consulta.close();
                }
                if (resultadotabla != null){
                    resultadotabla.close();
                }
                if (conn != null) {
                    disconnectDB(conn);
                }
                }
            }
          return id;
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public int insertProyectos(String codigo,String descripcion, String fecha_inicio, String fecha_final, String responsable, String usuariosid ){
        conn = connectDB();
        String query = " insert into proyectos(codigo, descripcion, fecha_inicio, fecha_final, responsable, usuariosid) values (?,?,?,?,?,?) ";
        PreparedStatement preStmt =null;
        try {
            preStmt = conn.prepareStatement(query);
            preStmt.setString(1, codigo);
            preStmt.setString(2, descripcion);
            preStmt.setString(3, fecha_inicio);
            preStmt.setString(4, fecha_final);
            preStmt.setString(5, responsable);
            preStmt.setString(6, usuariosid);
            boolean result = preStmt.execute();
            if(result){
                return 0;
            }
            else{
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if (preStmt != null) {
                    preStmt.close();
                }
                if (conn != null) {
                    disconnectDB(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
    
    public String getConsultaProyectosEmpleado(String responsable){
        conn = connectDB();
        
        String query = "select * from proyectos where responsable=?";
        PreparedStatement consulta = null;
        ResultSet resultadotabla = null;
        String w = "";
        StringBuilder tabla = new StringBuilder(w);
        
        try{
            consulta = conn.prepareStatement(query);
            consulta.setString(1, responsable);
            resultadotabla = consulta.executeQuery();
            tabla.append("tcodigo| \tdescripcion|           \tfecha inicio|\tfecha final|\n");
            while (resultadotabla.next()){
                tabla.append(resultadotabla.getString(2)).append("\t");
                tabla.append(resultadotabla.getString(3)).append("\t");
                tabla.append(resultadotabla.getString(4)).append("\t");
                tabla.append(resultadotabla.getString(5)).append("\n");
            }
          return tabla.toString();
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tabla.toString();
    }
    
    public String getConsultaProyectos(){
        conn = connectDB();
        
        String query = "select * from proyectos";
        PreparedStatement consulta = null;
        ResultSet resultadotabla = null;
        String w = "";
        StringBuilder tabla = new StringBuilder(w);
        
        try{
            consulta = conn.prepareStatement(query);
            resultadotabla = consulta.executeQuery();
            tabla.append("tcosigo|    \tdescripcion|           \tfinicio|  \tffinal|\n");
            while (resultadotabla.next()){
                tabla.append(resultadotabla.getString(2)).append("\t");
                tabla.append(resultadotabla.getString(3)).append("\t");
                tabla.append(resultadotabla.getString(4)).append("\t");
                tabla.append(resultadotabla.getString(5)).append("\n");
            }
          return tabla.toString();
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tabla.toString();
    }
    
     public String getConsultaidpro(String codigo){
        conn = connectDB();
        String query = " select * from proyectos where codigo=?";
        PreparedStatement consulta = null;
        ResultSet resultadotabla = null;
        String id="";
        try{
            consulta = conn.prepareStatement(query);
            consulta.setString(1, codigo);
            resultadotabla = consulta.executeQuery();
            id=resultadotabla.getString(1);
            while (resultadotabla.next()){
                
                if (id.equals(codigo)){
                    
                if (consulta != null) {
                    consulta.close();
                }
                if (resultadotabla != null){
                    resultadotabla.close();
                }
                if (conn != null) {
                    disconnectDB(conn);
                }
                }
            }
          return id;
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
     
    public int insertTareas(String proyectosid, String descripcion, String fecha, String observaciones, int porcentaje, int estatus, int tipo, int prioridad, String responsable, String usuariosid ){
        conn = connectDB();
        String query = " insert into tareas(proyectosid, descripcion, fecha, observaciones, porcentaje, estatus, tipo, prioridad, responsable, usuariosid) values (?,?,?,?,?,?,?,?,?,?) ";
        PreparedStatement preStmt =null;
        try {
            preStmt = conn.prepareStatement(query);
            preStmt.setString(1, proyectosid);
            preStmt.setString(2, descripcion);
            preStmt.setString(3, fecha);
            preStmt.setString(4, observaciones);
            preStmt.setInt(5, porcentaje);
            preStmt.setInt(6, estatus);
            preStmt.setInt(7, tipo);
            preStmt.setInt(8, prioridad);
            preStmt.setString(9, responsable);
            preStmt.setString(10, usuariosid);
            boolean result = preStmt.execute();
            if(result){
                return 0;
            }
            else{
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if (preStmt != null) {
                    preStmt.close();
                }
                if (conn != null) {
                    disconnectDB(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
    
    public String getConsultaTareasEmpleado(String responsable){
        conn = connectDB();
        
        String query = "select * from tareas where responsable=?";
        PreparedStatement consulta = null;
        ResultSet resultadotabla = null;
        String w = "";
        StringBuilder tabla = new StringBuilder(w);
        
        try{
            consulta = conn.prepareStatement(query);
            consulta.setString(1, responsable);
            resultadotabla = consulta.executeQuery();
            tabla.append("tid\tdescripcion|         \tfecha|        \tobservaciones|             \tprioridad|\n");
            while (resultadotabla.next()){
                tabla.append(resultadotabla.getInt(1)).append("\t");
                tabla.append(resultadotabla.getString(3)).append("\t");
                tabla.append(resultadotabla.getString(4)).append("\t");
                tabla.append(resultadotabla.getString(5)).append("\t");
                tabla.append(resultadotabla.getInt(9)).append("\n");
            }
          return tabla.toString();
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tabla.toString();
    }
    
    public String getConsultaidproyectotareas(String codigo){
        conn = connectDB();
        String query = " select * from tareas where id=?";
        PreparedStatement consulta = null;
        ResultSet resultadotabla = null;
        String id="";
        try{
            consulta = conn.prepareStatement(query);
            consulta.setString(1, codigo);
            resultadotabla = consulta.executeQuery();
            id=resultadotabla.getString(2);
            while (resultadotabla.next()){
                
                if (id.equals(codigo)){
                    
                if (consulta != null) {
                    consulta.close();
                }
                if (resultadotabla != null){
                    resultadotabla.close();
                }
                if (conn != null) {
                    disconnectDB(conn);
                }
                }
            }
          return id;
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public String getConsultadescripciontareas(String codigo){
        conn = connectDB();
        String query = " select * from tareas where id=?";
        PreparedStatement consulta = null;
        ResultSet resultadotabla = null;
        String id="";
        try{
            consulta = conn.prepareStatement(query);
            consulta.setString(1, codigo);
            resultadotabla = consulta.executeQuery();
            id=resultadotabla.getString(3);
            while (resultadotabla.next()){
                
                if (id.equals(codigo)){
                    
                if (consulta != null) {
                    consulta.close();
                }
                if (resultadotabla != null){
                    resultadotabla.close();
                }
                if (conn != null) {
                    disconnectDB(conn);
                }
                }
            }
          return id;
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public String getConsultafechatareas(String codigo){
        conn = connectDB();
        String query = " select * from tareas where id=?";
        PreparedStatement consulta = null;
        ResultSet resultadotabla = null;
        String id="";
        try{
            consulta = conn.prepareStatement(query);
            consulta.setString(1, codigo);
            resultadotabla = consulta.executeQuery();
            id=resultadotabla.getString(4);
            while (resultadotabla.next()){
                
                if (id.equals(codigo)){
                    
                if (consulta != null) {
                    consulta.close();
                }
                if (resultadotabla != null){
                    resultadotabla.close();
                }
                if (conn != null) {
                    disconnectDB(conn);
                }
                }
            }
          return id;
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public String getConsultaobservacionestareas(String codigo){
        conn = connectDB();
        String query = " select * from tareas where id=?";
        PreparedStatement consulta = null;
        ResultSet resultadotabla = null;
        String id="";
        try{
            consulta = conn.prepareStatement(query);
            consulta.setString(1, codigo);
            resultadotabla = consulta.executeQuery();
            id=resultadotabla.getString(5);
            while (resultadotabla.next()){
                
                if (id.equals(codigo)){
                    
                if (consulta != null) {
                    consulta.close();
                }
                if (resultadotabla != null){
                    resultadotabla.close();
                }
                if (conn != null) {
                    disconnectDB(conn);
                }
                }
            }
          return id;
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public String getConsultaporcentajetareas(String codigo){
        conn = connectDB();
        String query = " select * from tareas where id=?";
        PreparedStatement consulta = null;
        ResultSet resultadotabla = null;
        String id="";
        try{
            consulta = conn.prepareStatement(query);
            consulta.setString(1, codigo);
            resultadotabla = consulta.executeQuery();
            id=resultadotabla.getString(6);
            while (resultadotabla.next()){
                
                if (id.equals(codigo)){
                    
                if (consulta != null) {
                    consulta.close();
                }
                if (resultadotabla != null){
                    resultadotabla.close();
                }
                if (conn != null) {
                    disconnectDB(conn);
                }
                }
            }
          return id;
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public String getConsultaestatustareas(String codigo){
        conn = connectDB();
        String query = " select * from tareas where id=?";
        PreparedStatement consulta = null;
        ResultSet resultadotabla = null;
        String id="";
        try{
            consulta = conn.prepareStatement(query);
            consulta.setString(1, codigo);
            resultadotabla = consulta.executeQuery();
            id=resultadotabla.getString(7);
            while (resultadotabla.next()){
                
                if (id.equals(codigo)){
                    
                if (consulta != null) {
                    consulta.close();
                }
                if (resultadotabla != null){
                    resultadotabla.close();
                }
                if (conn != null) {
                    disconnectDB(conn);
                }
                }
            }
          return id;
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public String getConsultatipotareas(String codigo){
        conn = connectDB();
        String query = " select * from tareas where id=?";
        PreparedStatement consulta = null;
        ResultSet resultadotabla = null;
        String id="";
        try{
            consulta = conn.prepareStatement(query);
            consulta.setString(1, codigo);
            resultadotabla = consulta.executeQuery();
            id=resultadotabla.getString(8);
            while (resultadotabla.next()){
                
                if (id.equals(codigo)){
                    
                if (consulta != null) {
                    consulta.close();
                }
                if (resultadotabla != null){
                    resultadotabla.close();
                }
                if (conn != null) {
                    disconnectDB(conn);
                }
                }
            }
          return id;
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public String getConsultaprioridadtareas(String codigo){
        conn = connectDB();
        String query = " select * from tareas where id=?";
        PreparedStatement consulta = null;
        ResultSet resultadotabla = null;
        String id="";
        try{
            consulta = conn.prepareStatement(query);
            consulta.setString(1, codigo);
            resultadotabla = consulta.executeQuery();
            id=resultadotabla.getString(9);
            while (resultadotabla.next()){
                
                if (id.equals(codigo)){
                    
                if (consulta != null) {
                    consulta.close();
                }
                if (resultadotabla != null){
                    resultadotabla.close();
                }
                if (conn != null) {
                    disconnectDB(conn);
                }
                }
            }
          return id;
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
}
