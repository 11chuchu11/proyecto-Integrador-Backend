package com.dh.integradora.dao;

import com.dh.integradora.dominio.Odontologo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class OdontologoDAOH2 implements IDao<Odontologo>{

    private static Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver").newInstance();
        return DriverManager.getConnection("jdbc:h2:~/integrador","sa","sa");
    }

    @Override
    public List<Odontologo> listarElementos() {
        Connection connection = null;
        List<Odontologo> listaOdontologos = new ArrayList<>();
        Odontologo odontologo;

        try{
            connection= getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM odontologos");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                odontologo = new Odontologo(rs.getInt(1), rs.getInt(4), rs.getString(2), rs.getString(3) );
                listaOdontologos.add(odontologo);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                connection.close();
            } catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return listaOdontologos;
    }

    @Override
    public Odontologo buscarId(int id) {
        Connection connection = null;
        Odontologo odontologo = null;
        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM odontologos WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                odontologo = new Odontologo(rs.getInt(1),rs.getInt(4), rs.getString(2),rs.getString(3));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                connection.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return odontologo;
    }

    @Override
    public Odontologo buscarEmail(String email) {
        return null;
    }

    @Override
    public Odontologo guardar(Odontologo elemento) {
        Connection connection = null;
        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO odontologos(nombre, apellido, matricula) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, elemento.getNombre());
            preparedStatement.setString(2, elemento.getApellido());
            preparedStatement.setInt(3, elemento.getMatricula());
            preparedStatement.executeUpdate();
            ResultSet claves = preparedStatement.getGeneratedKeys();
            while(claves.next()){
                elemento.setId(claves.getInt(1));
            }
            preparedStatement.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }

        return elemento;
    }

    @Override
    public Odontologo actualizar(Odontologo elemento) {
        Connection connection = null;
        try {
            connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE odontologos SET nombre = ?, " +
                    "apellido = ? , matricula = ? WHERE id=?");
            preparedStatement.setString(1, elemento.getNombre());
            preparedStatement.setString(2, elemento.getApellido());
            preparedStatement.setInt(3, elemento.getMatricula());
            preparedStatement.setInt(4, elemento.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return elemento;

    }

    @Override
    public void eliminar(int id) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM odontologos WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
