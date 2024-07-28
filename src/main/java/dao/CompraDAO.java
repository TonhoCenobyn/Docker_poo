package dao;

import model.Compra;
import model.Livro;
import model.Usuario;
import util.ConectaDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class CompraDAO {
    private String sql;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public ArrayList<Compra> getHistorico() {
        return null;
    }

    public boolean inserirCompra(Usuario usuario, ArrayList<Livro> carrinho) {
            boolean retorno = false;

            for (Livro livro : carrinho) {
                try (Connection conn = new ConectaDB().getConexao()) {
                    this.preparedStatement = conn.prepareStatement("INSERT INTO compra (id_usuario, id_livro, data_compra) VALUES (?, ?, CURRENT_DATE);");

                    this.preparedStatement.setInt(1, usuario.getId());
                    this.preparedStatement.setInt(2, livro.getId());

                    this.preparedStatement.executeUpdate();
                    System.out.println("Inserir livro executado com sucesso");
                    retorno = true;

                } catch (SQLException e) {
                    e.printStackTrace();
                    retorno = false;
                }
            }
            return retorno;
    }

    public ArrayList<Compra> buscarCompras(Usuario usuario) {
        ArrayList<Compra> compras = new ArrayList<>();

        try(Connection conn = new ConectaDB().getConexao()){
            this.preparedStatement = conn.prepareStatement("SELECT DISTINCT titulo_livro, autor_livro, preco_livro, genero_livro, data_compra FROM livro, compra, usuario WHERE compra.id_livro = livro.id_livro AND compra.id_usuario = ?");
            this.preparedStatement.setInt(1, usuario.getId());
            resultSet = this.preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Livro livro = new Livro(
                        this.resultSet.getString("titulo_livro"),
                        this.resultSet.getString("autor_livro"),
                        this.resultSet.getFloat("preco_livro"),
                        this.resultSet.getInt("genero_livro")
                );

                Compra compra = new Compra(livro, this.resultSet.getDate("data_compra"));
                compras.add(compra);
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

        return compras;
    }
}
