package com.dh.integradora.dao;

import com.dh.integradora.dominio.Domicilio;

import java.sql.*;
import java.util.List;

public class DomicilioDAOH2 implements IDao<Domicilio>{
    //obtener la conexión
    private static Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver").newInstance();
        return DriverManager.getConnection("jdbc:h2:~/clase23","sa","sa");
    }

    @Override
    public List<Domicilio> listarElementos() {
        return null;
    }

    @Override
    public Domicilio buscarId(int id) {
        Connection connection=null;
        Domicilio domicilio=null;

        try{
           connection=getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM domicilios WHERE ID=?");
            preparedStatement.setInt(1,id);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                domicilio= new Domicilio(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return domicilio;
    }

    @Override
    public Domicilio buscarEmail(String email) {
        return null;
    }


    //clase 25
    @Override
    public Domicilio guardar(Domicilio elemento) {
        Connection connection=null;
        try{
            connection=getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement("INSERT INTO domicilios(calle, numero," +
                    " localidad, provincia) VALUES (?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, elemento.getCalle());
            preparedStatement.setInt(2,elemento.getNumero());
            preparedStatement.setString(3, elemento.getLocalidad());
            preparedStatement.setString(4, elemento.getProvincia());
            preparedStatement.executeUpdate();
            ResultSet claves=preparedStatement.getGeneratedKeys();
            while(claves.next()){
                elemento.setId(claves.getInt(1));
            }
            preparedStatement.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return elemento;
    }

    @Override
    public Domicilio actualizar(Domicilio elemento) {
        return null;
    }
}