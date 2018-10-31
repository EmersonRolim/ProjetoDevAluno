
package br.unama.DAO;

import br.unama.modelo.Aluno;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

public class AlunoDAO {
    private DataSource datasource;
    private List<Aluno> listaAlunos;
    public AlunoDAO(DataSource ds){
        this.datasource = ds;
    }
    public List<Aluno> consultaAlunos(){
        listaAlunos = new ArrayList<Aluno>();
        Connection con = null;
        Statement st = null;
        ResultSet result = null;
        //Obter conexao com banco
        try {
            
            con = datasource.getConnection();
            //criar o SQL
        st = con.createStatement();
        String sql= "select * from aluno";
          //Executar o SQL
        result = st.executeQuery(sql);
        //Processar os dados retornados
        while(result.next()){
            int id = result.getInt("idaluno");
            String primeiroNome = result.getString("primeiro_nome");
            String ultimoNome = result.getString("ultimo_nome");
            String email = result.getString("email");
            Aluno aluno = new Aluno(id,primeiroNome, ultimoNome, email);
            listaAlunos.add(aluno);
    }
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
        return listaAlunos;
    }
}
