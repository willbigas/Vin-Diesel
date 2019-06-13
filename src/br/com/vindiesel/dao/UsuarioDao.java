package br.com.vindiesel.dao;

import br.com.vindiesel.model.Usuario;
import br.com.vindiesel.interfaces.DaoI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author william.mauro
 */
public class UsuarioDao extends Dao implements DaoI<Usuario> {

    EnderecoDao enderecoDao;
    TipoUsuarioDao tipoUsuarioDao;

    public UsuarioDao() {
        super();
        enderecoDao = new EnderecoDao();
        tipoUsuarioDao = new TipoUsuarioDao();
    }

    @Override
    public int inserir(Usuario funcionario) {
        String queryInsert = "INSERT INTO usuarios (NOME, PIS, SALARIO, TELEFONE, SENHA, EMAIL, FK_TIPOUSUARIO, FK_ENDERECO, ATIVO) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(queryInsert, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, funcionario.getNome());
            stmt.setInt(2, funcionario.getPis());
            stmt.setDouble(3, funcionario.getSalario());
            stmt.setString(4, funcionario.getTelefone());
            stmt.setString(5, funcionario.getSenha());
            stmt.setString(6, funcionario.getEmail());
            stmt.setInt(7, funcionario.getTipoUsuario().getId());
            stmt.setInt(8, funcionario.getEndereco().getId());
            stmt.setBoolean(9, funcionario.getAtivo());
            ResultSet res;
            if (stmt.executeUpdate() > 0) {
                res = stmt.getGeneratedKeys();
                res.next();
                return res.getInt(1);
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }

    @Override
    public boolean alterar(Usuario funcionario) {
        String queryUpdate = "UPDATE usuarios SET nome = ?, PIS = ?, SALARIO = ?, TELEFONE = ?, SENHA = ?, EMAIL = ?, FK_TIPOUSUARIO = ?,  FK_USUARIO = ?, ATIVO = ?  WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryUpdate);
            stmt.setString(1, funcionario.getNome());
            stmt.setInt(2, funcionario.getPis());
            stmt.setDouble(3, funcionario.getSalario());
            stmt.setString(4, funcionario.getTelefone());
            stmt.setString(5, funcionario.getSenha());
            stmt.setString(6, funcionario.getEmail());
            stmt.setInt(7, funcionario.getTipoUsuario().getId());
            stmt.setInt(8, funcionario.getEndereco().getId());
            stmt.setInt(9, funcionario.getId());
            stmt.setBoolean(10, funcionario.getAtivo());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(Usuario funcionario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deletar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desativar(Usuario funcionario) {
        String sql = "UPDATE usuarios SET ativo = false WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, funcionario.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean desativar(int id) {
        String sql = "UPDATE usuarios SET ativo = false WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Usuario> pesquisar() {
        String querySelect = "SELECT * FROM USUARIOS WHERE ATIVO = TRUE";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(querySelect);
            ResultSet result = stmt.executeQuery();
            List<Usuario> lista = new ArrayList<>();
            while (result.next()) {
                Usuario funcionario = new Usuario();
                funcionario.setId(result.getInt("id"));
                funcionario.setNome(result.getString("nome"));
                funcionario.setEmail(result.getString("email"));
                funcionario.setTelefone(result.getString("telefone"));
                funcionario.setPis(result.getInt("pis"));
                funcionario.setSalario(result.getDouble("salario"));
                funcionario.setSenha(result.getString("senha"));
                funcionario.setAtivo(result.getBoolean("ativo"));
                funcionario.setEndereco(enderecoDao.pesquisar(result.getInt("fk_endereco")));
                funcionario.setTipoUsuario(tipoUsuarioDao.pesquisar(result.getInt("fk_tipoUsuario")));
                lista.add(funcionario);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Usuario> pesquisar(String termo) {
        String querySelectComTermo = "SELECT * FROM usuarios WHERE (nome LIKE ?, email LIKE ?, telefone LIKE ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setString(1, "%" + termo + "%");
            stmt.setString(2, "%" + termo + "%");
            stmt.setString(3, "%" + termo + "%");
            ResultSet result = stmt.executeQuery();
            List<Usuario> lista = new ArrayList<>();
            while (result.next()) {
                Usuario funcionario = new Usuario();
                funcionario.setId(result.getInt("id"));
                funcionario.setNome(result.getString("nome"));
                funcionario.setEmail(result.getString("email"));
                funcionario.setTelefone(result.getString("telefone"));
                funcionario.setPis(result.getInt("pis"));
                funcionario.setSalario(result.getDouble("salario"));
                funcionario.setSenha(result.getString("senha"));
                funcionario.setAtivo(result.getBoolean("ativo"));
                funcionario.setEndereco(enderecoDao.pesquisar(result.getInt("fk_endereco")));
                funcionario.setTipoUsuario(tipoUsuarioDao.pesquisar(result.getInt("fk_tipoUsuario")));
                lista.add(funcionario);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public Usuario pesquisar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Usuario pesquisarLogin(String email) {
        String querySelectComTermo = "SELECT * FROM usuarios WHERE (email = ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setString(1, email);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                Usuario funcionario = new Usuario();
                funcionario.setId(result.getInt("id"));
                funcionario.setNome(result.getString("nome"));
                funcionario.setEmail(result.getString("email"));
                funcionario.setTelefone(result.getString("telefone"));
                funcionario.setPis(result.getInt("pis"));
                funcionario.setSalario(result.getDouble("salario"));
                funcionario.setSenha(result.getString("senha"));
                funcionario.setAtivo(result.getBoolean("ativo"));
                funcionario.setEndereco(enderecoDao.pesquisar(result.getInt("fk_endereco")));
                funcionario.setTipoUsuario(tipoUsuarioDao.pesquisar(result.getInt("fk_tipoUsuario")));
                return funcionario;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
