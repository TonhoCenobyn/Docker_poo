package dao;

import model.Livro;
import util.ConectaDB;

import java.sql.*;
import java.util.ArrayList;

public class LivroDAO {
    private String sql;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public ArrayList<Livro> getLivrosDisponiveis() {
        ArrayList<Livro>livros = new ArrayList<Livro>();

        try (Connection conn = new ConectaDB().getConexao()) {
            this.sql = "SELECT * FROM livro WHERE disponivel = true";
            this.statement = conn.createStatement();
            this.resultSet = this.statement.executeQuery(sql);

            while (this.resultSet.next()) {
                Livro livro = new Livro(
                        this.resultSet.getInt("id_livro"),
                        this.resultSet.getString("titulo_livro"),
                        this.resultSet.getString("autor_livro"),
                        this.resultSet.getFloat("preco_livro"),
                        this.resultSet.getInt("genero_livro"));
                livros.add(livro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return livros;
    }

    public ArrayList<Livro> getLivros() {
        ArrayList<Livro>livros = new ArrayList<Livro>();

        try (Connection conn = new ConectaDB().getConexao()) {
            this.sql = "SELECT * FROM livro";
            this.statement = conn.createStatement();
            this.resultSet = this.statement.executeQuery(sql);

            while (this.resultSet.next()) {
                Livro livro = new Livro(
                        this.resultSet.getInt("id_livro"),
                        this.resultSet.getString("titulo_livro"),
                        this.resultSet.getString("autor_livro"),
                        this.resultSet.getFloat("preco_livro"),
                        this.resultSet.getInt("genero_livro"));
                livros.add(livro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return livros;
    }

    public Livro getLivro(int id) {
        System.out.println("ENTROU NO GETLIVRO");
        System.out.println("ID: " + id);
        Livro livro = null;
        try(Connection conn = new ConectaDB().getConexao()){
            PreparedStatement procurarLivro = conn.prepareStatement("SELECT * FROM livro WHERE id_livro = ?");

            procurarLivro.setInt(1, id);

            this.resultSet = procurarLivro.executeQuery();
            while(resultSet.next()){
                Livro l = new Livro(
                        id,
                        this.resultSet.getString("titulo_livro"),
                        this.resultSet.getString("autor_livro"),
                        this.resultSet.getFloat("preco_livro"),
                        this.resultSet.getInt("genero_livro")
                );
                return l;

            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        System.out.println("RETORNOU NULL");
        return null;

    }

    public boolean inserirLivro(Livro livro) {
        try (Connection conn = new ConectaDB().getConexao()) {
            this.sql = "INSERT INTO livro(titulo_livro, autor_livro, preco_livro, genero_livro)" +
                    "VALUES ('"+livro.getNome()
                    +"','"+livro.getAutor()
                    +"','"+livro.getPreco()
                    +"','"+livro.getGenero()+"')";
            this.statement = conn.createStatement();
            statement.execute(sql);
            System.out.println("Inserir livro executado com sucesso");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirLivro(Livro livro) {
        System.out.println("LIVRO NO DAO EXCLUIR: ");
        System.out.println(livro.getId());

        try(Connection conn = new ConectaDB().getConexao()){
            PreparedStatement deletarLivro = conn.prepareStatement("DELETE FROM livro WHERE id_livro = ?");

            deletarLivro.setInt(1, livro.getId());
            deletarLivro.execute();
            return true;
        }catch(SQLException e){
            if (e.getSQLState().equals("23503")){
                return false;
            }

            e.printStackTrace();
            return false;
        }
    }

    public boolean removerLivro(Livro livro) {
        System.out.println("LIVRO NO DAO EXCLUIR: ");
        System.out.println(livro.getId());

        try(Connection conn = new ConectaDB().getConexao()){
            PreparedStatement removerLivro = conn.prepareStatement("UPDATE livro SET disponivel = false WHERE id_livro = ?");

            removerLivro.setInt(1, livro.getId());
            removerLivro.execute();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean disponibilizarLivro(Livro livro) {
        try(Connection conn = new ConectaDB().getConexao()){
            PreparedStatement removerLivro = conn.prepareStatement("UPDATE livro SET disponivel = true WHERE id_livro = ?");

            removerLivro.setInt(1, livro.getId());
            removerLivro.execute();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean editarLivro(Livro livro) {
        try(Connection conn = new ConectaDB().getConexao()){
            PreparedStatement editarLivro = conn.prepareStatement("UPDATE livro SET titulo_livro = ?, autor_livro = ?, preco_livro = ?, genero_livro = ? WHERE id_livro = ?");

            editarLivro.setString(1, livro.getNome());
            editarLivro.setString(2, livro.getAutor());
            editarLivro.setFloat(3, livro.getPreco());
            editarLivro.setInt(4, livro.getGenero());
            editarLivro.setInt(5, livro.getId());

            editarLivro.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
