import org.gieback.Entity.CRMEntity.Client;
import org.gieback.DAO.CRMDao.ClientDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class TESTCLIENT {
    private EntityManager m;
    private EntityTransaction t;

    public TESTCLIENT() {

    }
    public static void main( String[] args )
    {
        List<Client> listClients = new ArrayList<>();
        ClientDao dao = new ClientDao();
        Client c = new Client("aaaa","bbb","66666","gg@gg",0000);
        dao.addClient(c);
        listClients = dao.getAllClients();

        for(Client elt : listClients){
            System.out.println(elt);
        }
    }
}
