package dao;

import model.Usuario;
import util.ConectaDB;

import java.sql.*;
import java.util.ArrayList;

public class UsuarioDAO {
    private String sql;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public ArrayList<Usuario> getUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        try (Connection conn = new ConectaDB().getConexao()) {
            this.sql = "SELECT * FROM usuario";
            this.statement = conn.createStatement();
            this.resultSet = this.statement.executeQuery(sql);

            while (this.resultSet.next()) {
                Usuario usuario = new Usuario(
                        this.resultSet.getInt("id_usuario"),
                        this.resultSet.getString("nome_usuario"),
                        this.resultSet.getString("email_usuario"),
                        this.resultSet.getString("senha_usuario"),
                        this.resultSet.getString("telefone_usuario"),
                        this.resultSet.getInt("permissao_usuario"));
                usuarios.add(usuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarios;
    }

  /*public String cadastrarUsuario(Usuario usuario){

        try(Connection conn = new ConectaDB().getConexao()){
            this.sql = "INSERT INTO usuario (nome_usuario, cpf_usuario, email_usuario, senha_usuario, telefone_usuario) " +
                    "VALUES (?, ?, ?, ?, ?)";

            this.preparedStatement = conn.prepareStatement(this.sql);
            this.preparedStatement.setString(1, usuario.getNome());
            this.preparedStatement.setString(2, usuario.getCpf());
            this.preparedStatement.setString(3, usuario.getEmail());
            this.preparedStatement.setString(4, usuario.getSenha());
            this.preparedStatement.setString(5, usuario.getTelefone());

            this.preparedStatement.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
  }*/

    public boolean inserirUsuario(Usuario usuario) {
        try (Connection conn = new ConectaDB().getConexao()) {
            this.sql = "INSERT INTO usuario(nome_usuario, email_usuario, senha_usuario, telefone_usuario, permissao_usuario)" +
                    "VALUES ('"+usuario.getNome()
                    +"','"+usuario.getEmail()
                    +"','"+usuario.getSenha()
                    +"','"+usuario.getTelefone()
                    +"','"+usuario.getPermissao()
                    +"')";
            this.statement = conn.createStatement();
            statement.execute(sql);
            System.out.println("Inserir usuario executado com sucesso");

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirUsuario(int id) {
        try (Connection conn = new ConectaDB().getConexao()) {
            this.statement = conn.createStatement();
            this.sql = "DELETE FROM usuario WHERE id_usuario = " + id;

            statement.execute(sql);
            System.out.println("Excluir usuario executado com sucesso");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Usuario autenticarUsuario(String email, String senha){
        try(Connection conn = new ConectaDB().getConexao()){
            PreparedStatement autenticacao = conn.prepareStatement("SELECT * FROM usuario WHERE email_usuario = ? AND senha_usuario = ?");

            autenticacao.setString(1, email);
            autenticacao.setString(2, senha);
            this.resultSet = autenticacao.executeQuery();
            while(resultSet.next()){
                return new Usuario(
                        this.resultSet.getInt("id_usuario"),
                        this.resultSet.getString("nome_usuario"),
                        this.resultSet.getString("email_usuario"),
                        this.resultSet.getString("senha_usuario"),
                        this.resultSet.getString("telefone_usuario"),
                        this.resultSet.getInt("permissao_usuario"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}




