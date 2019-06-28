package br.com.vindiesel.dao;

import br.com.vindiesel.model.Usuario;
import br.com.vindiesel.interfaces.DaoI;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author william.mauro
 */
public class UsuarioDao extends DaoBD implements DaoI<Usuario> {

    EnderecoDao enderecoDao;
    TipoUsuarioDao tipoUsuarioDao;

    public UsuarioDao() {
        super();
        enderecoDao = new EnderecoDao();
        tipoUsuarioDao = new TipoUsuarioDao();
    }

    @Override
    public int inserir(Usuario usuario) {
        String queryInsert = "INSERT INTO usuario (NOME, DATANASCIMENTO, TELEFONE, EMAIL , CPF ,"
                + " SENHA , SALARIO , NUMEROPIS , ATIVO , ENDERECO_ID , TIPOUSUARIO_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ? , ? , ?)";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(queryInsert, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, usuario.getNome());
            stmt.setDate(2, Date.valueOf(usuario.getDataNascimento()));
            stmt.setString(3, usuario.getTelefone());
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getCpf());
            stmt.setString(6, usuario.getSenha());
            stmt.setDouble(7, usuario.getSalario());
            stmt.setInt(8, usuario.getPis());
            stmt.setBoolean(9, usuario.getAtivo());
            stmt.setInt(10, usuario.getEndereco().getId());
            stmt.setInt(11, usuario.getTipoUsuario().getId());
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
    public boolean alterar(Usuario usuario) {
        String queryUpdate = "UPDATE usuario SET nome = ?, DATANASCIMENTO = ?, TELEFONE = ?, EMAIL = ?,"
                + " CPF = ?, SENHA = ?, SALARIO = ?,  NUMEROPIS = ?, ATIVO = ? , "
                + "ENDERECO_ID = ? , TIPOUSUARIO_ID = ?  WHERE ID = ? ";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryUpdate);
            stmt.setString(1, usuario.getNome());
            stmt.setDate(2, Date.valueOf(usuario.getDataNascimento()));
            stmt.setString(3, usuario.getTelefone());
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getCpf());
            stmt.setString(6, usuario.getSenha());
            stmt.setDouble(7, usuario.getSalario());
            stmt.setInt(8, usuario.getPis());
            stmt.setBoolean(9, usuario.getAtivo());
            stmt.setInt(10, usuario.getEndereco().getId());
            stmt.setInt(11, usuario.getTipoUsuario().getId());
            stmt.setInt(12, usuario.getId());
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
    public boolean desativar(Usuario usuario) {
        String sql = "UPDATE usuario SET ativo = false WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, usuario.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean desativar(int id) {
        String sql = "UPDATE usuario SET ativo = false WHERE id = ?";
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
        String querySelect = "SELECT * FROM USUARIO WHERE ATIVO = TRUE";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(querySelect);
            ResultSet result = stmt.executeQuery();
            List<Usuario> lista = new ArrayList<>();
            while (result.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(result.getInt("id"));
                usuario.setNome(result.getString("nome"));
                usuario.setDataNascimento(result.getDate("dataNascimento").toLocalDate());
                usuario.setTelefone(result.getString("telefone"));
                usuario.setEmail(result.getString("email"));
                usuario.setCpf(result.getString("cpf"));
                usuario.setSenha(result.getString("senha"));
                usuario.setSalario(result.getDouble("salario"));
                usuario.setPis(result.getInt("numeroPis"));
                usuario.setAtivo(result.getBoolean("ativo"));
                usuario.setEndereco(enderecoDao.pesquisar(result.getInt("endereco_id")));
                usuario.setTipoUsuario(tipoUsuarioDao.pesquisar(result.getInt("tipoUsuario_id")));
                lista.add(usuario);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Usuario> pesquisar(String termo) {
        String querySelectComTermo = "SELECT * FROM usuario WHERE (nome LIKE ? or email LIKE ? or telefone LIKE ? or cpf LIKE ?) AND ATIVO = TRUE ";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setString(1, "%" + termo + "%");
            stmt.setString(2, "%" + termo + "%");
            stmt.setString(3, "%" + termo + "%");
            stmt.setString(4, "%" + termo + "%");
            ResultSet result = stmt.executeQuery();
            List<Usuario> lista = new ArrayList<>();
            while (result.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(result.getInt("id"));
                usuario.setNome(result.getString("nome"));
                usuario.setDataNascimento(result.getDate("dataNascimento").toLocalDate());
                usuario.setTelefone(result.getString("telefone"));
                usuario.setEmail(result.getString("email"));
                usuario.setCpf(result.getString("cpf"));
                usuario.setSenha(result.getString("senha"));
                usuario.setSalario(result.getDouble("salario"));
                usuario.setPis(result.getInt("numeroPis"));
                usuario.setAtivo(result.getBoolean("ativo"));
                usuario.setEndereco(enderecoDao.pesquisar(result.getInt("endereco_id")));
                usuario.setTipoUsuario(tipoUsuarioDao.pesquisar(result.getInt("tipoUsuario_id")));
                lista.add(usuario);
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
        String querySelectComTermo = "SELECT * FROM usuario WHERE (email = ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setString(1, email);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(result.getInt("id"));
                usuario.setNome(result.getString("nome"));
                usuario.setDataNascimento(result.getDate("dataNascimento").toLocalDate());
                usuario.setTelefone(result.getString("telefone"));
                usuario.setEmail(result.getString("email"));
                usuario.setCpf(result.getString("cpf"));
                usuario.setSenha(result.getString("senha"));
                usuario.setSalario(result.getDouble("salario"));
                usuario.setPis(result.getInt("numeroPis"));
                usuario.setAtivo(result.getBoolean("ativo"));
                usuario.setEndereco(enderecoDao.pesquisar(result.getInt("endereco_id")));
                usuario.setTipoUsuario(tipoUsuarioDao.pesquisar(result.getInt("tipoUsuario_id")));
                return usuario;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    /**
     *
     * @param ativo
     * @return
     */
    public List<Usuario> pesquisar(Boolean ativo) {

        String querySelect = "SELECT * FROM USUARIO WHERE ATIVO = " + ativo + " ";
        if (ativo == null) {
            querySelect = "SELECT * FROM USUARIO";
        }
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(querySelect);
            ResultSet result = stmt.executeQuery();
            List<Usuario> lista = new ArrayList<>();
            while (result.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(result.getInt("id"));
                usuario.setNome(result.getString("nome"));
                usuario.setDataNascimento(result.getDate("dataNascimento").toLocalDate());
                usuario.setTelefone(result.getString("telefone"));
                usuario.setEmail(result.getString("email"));
                usuario.setCpf(result.getString("cpf"));
                usuario.setSenha(result.getString("senha"));
                usuario.setSalario(result.getDouble("salario"));
                usuario.setPis(result.getInt("numeroPis"));
                usuario.setAtivo(result.getBoolean("ativo"));
                usuario.setEndereco(enderecoDao.pesquisar(result.getInt("endereco_id")));
                usuario.setTipoUsuario(tipoUsuarioDao.pesquisar(result.getInt("tipoUsuario_id")));
                lista.add(usuario);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
     public List<Usuario> pesquisarPorCpf(String cpf) {
        String querySelectComTermo = "SELECT * FROM usuario WHERE (cpf LIKE ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setString(1, "%" + cpf + "%");
            ResultSet result = stmt.executeQuery();
            List<Usuario> lista = new ArrayList<>();
            while (result.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(result.getInt("id"));
                usuario.setNome(result.getString("nome"));
                usuario.setDataNascimento(result.getDate("dataNascimento").toLocalDate());
                usuario.setTelefone(result.getString("telefone"));
                usuario.setEmail(result.getString("email"));
                usuario.setCpf(result.getString("cpf"));
                usuario.setSenha(result.getString("senha"));
                usuario.setSalario(result.getDouble("salario"));
                usuario.setPis(result.getInt("numeroPis"));
                usuario.setAtivo(result.getBoolean("ativo"));
                usuario.setEndereco(enderecoDao.pesquisar(result.getInt("endereco_id")));
                usuario.setTipoUsuario(tipoUsuarioDao.pesquisar(result.getInt("tipoUsuario_id")));
                lista.add(usuario);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
     public List<Usuario> pesquisarPorNome(String nome) {
        String querySelectComTermo = "SELECT * FROM usuario WHERE (nome LIKE ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setString(1, "%" + nome + "%");
            ResultSet result = stmt.executeQuery();
            List<Usuario> lista = new ArrayList<>();
            while (result.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(result.getInt("id"));
                usuario.setNome(result.getString("nome"));
                usuario.setDataNascimento(result.getDate("dataNascimento").toLocalDate());
                usuario.setTelefone(result.getString("telefone"));
                usuario.setEmail(result.getString("email"));
                usuario.setCpf(result.getString("cpf"));
                usuario.setSenha(result.getString("senha"));
                usuario.setSalario(result.getDouble("salario"));
                usuario.setPis(result.getInt("numeroPis"));
                usuario.setAtivo(result.getBoolean("ativo"));
                usuario.setEndereco(enderecoDao.pesquisar(result.getInt("endereco_id")));
                usuario.setTipoUsuario(tipoUsuarioDao.pesquisar(result.getInt("tipoUsuario_id")));
                lista.add(usuario);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
