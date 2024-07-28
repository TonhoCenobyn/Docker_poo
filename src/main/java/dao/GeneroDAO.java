package dao;

import model.Genero;
import util.ConectaDB;

import java.sql.*;
import java.util.ArrayList;

public class GeneroDAO {
    private String sql;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public ArrayList<Genero> getGeneros() {
        ArrayList<Genero>generos = new ArrayList<Genero>();

        try (Connection conn = new ConectaDB().getConexao()) {
            this.sql = "SELECT * FROM genero";
            this.statement = conn.createStatement();
            this.resultSet = this.statement.executeQuery(sql);

            while (this.resultSet.next()) {
                Genero genero = new Genero(
                        this.resultSet.getInt("id_genero"),
                        this.resultSet.getString("nome_genero")
                        );
                generos.add(genero);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generos;
    }

    public Genero getGenero(int id) {
        Genero genero = null;
        try(Connection conn = new ConectaDB().getConexao()){
            PreparedStatement procurarGenero = conn.prepareStatement("SELECT * FROM genero WHERE id_genero = ?");

            procurarGenero.setInt(1, id);

            this.resultSet = procurarGenero.executeQuery();
            while(resultSet.next()){
                Genero g = new Genero(
                        this.resultSet.getInt("id_genero"),
                        this.resultSet.getString("nome_genero")
                );

                return g;

            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public boolean cadastrarGenero(Genero genero) {
        try (Connection conn = new ConectaDB().getConexao()) {
            this.sql = "INSERT INTO genero(nome_genero)" +
                    "VALUES ('"+genero.getNome()+"')";
            this.statement = conn.createStatement();
            statement.execute(sql);
            System.out.println("Inserir genero executado com sucesso");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirGenero(Genero genero) {
        try(Connection conn = new ConectaDB().getConexao()){
            PreparedStatement deletarGenero = conn.prepareStatement("DELETE FROM genero WHERE id_genero = ?");

            deletarGenero.setInt(1, (genero.getId()));
            deletarGenero.executeUpdate();
            return true;
        }catch(SQLException e){
            if (e.getSQLState().equals("23503")){
                return false;
            }
            e.printStackTrace();
            return false;
        }
    }
    public boolean editarGenero(Genero genero) {
        System.out.println("GENERO NO DAO: " + genero.getNome() + " " + genero.getId());

        try(Connection conn = new ConectaDB().getConexao()){
            PreparedStatement editarGenero = conn.prepareStatement("UPDATE genero SET nome_genero = ? WHERE id_genero = ?");

            editarGenero.setString(1, genero.getNome());
            editarGenero.setInt(2, genero.getId());

            editarGenero.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public Integer contGenero(int id) {
        try(Connection conn = new ConectaDB().getConexao()){
            Integer qnt=null;
            PreparedStatement contarGenero = conn.prepareStatement("SELECT COUNT(*) FROM livro WHERE genero_livro = ?");
            contarGenero.setInt(1, id);

            this.resultSet = contarGenero.executeQuery();

            while(resultSet.next()){
                qnt = resultSet.getInt(1);
            }
            return qnt;

        }catch(SQLException e){
            e.printStackTrace();

        }
        return null;
    }
}
