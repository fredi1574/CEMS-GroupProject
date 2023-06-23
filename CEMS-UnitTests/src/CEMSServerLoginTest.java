
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class CEMSServerLoginTest {

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
