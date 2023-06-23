package JunitTest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.Mock;
import java.sql.SQLException;
import java.util.ArrayList;
import static com.mysql.cj.MysqlConnection.*;

public class CemsServerTest {
    @Mock
    private MysqlConnection mysqlConnection;
    @BeforeClass
    public static void openConnection()
            throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        MysqlConnection.connectToDb("AB12345tre");
    }

    @Before
    public void setUp() throws Exception {
        mysqlConnection = mock(MysqlConnection.class);

    }
}
