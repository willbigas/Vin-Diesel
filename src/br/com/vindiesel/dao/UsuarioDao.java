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
public class UsuarioDao extends GenericDao<Usuario> implements DaoI<Usuario> {

    EnderecoDao enderecoDao;
    TipoUsuarioDao tipoUsuarioDao;

    public UsuarioDao() {
        super();
        enderecoDao = new EnderecoDao();
        tipoUsuarioDao = new TipoUsuarioDao();
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

        String querySelect = "SELECT * FROM USUARIO WHERE (ATIVO = " + ativo + " ";
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

    public Usuario pesquisarPorLogin(String email) {
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
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return null;
    }
    
    public List<Usuario> pesquisar(String termo , Boolean ativo) {
        String querySelectComTermo = "SELECT * FROM usuario WHERE (nome LIKE ? or email LIKE ? or telefone LIKE ? or cpf LIKE ?) AND ATIVO = " + ativo + " ";
        if (ativo == null) {
            querySelectComTermo = "SELECT * FROM USUARIO WHERE (nome LIKE ? or email LIKE ? or telefone LIKE ? or cpf LIKE ?) " ;
        }
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
}
