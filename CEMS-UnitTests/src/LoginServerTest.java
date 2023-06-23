import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginServerTest {

    @BeforeEach
    public void setUp(){
        MysqlConnection.getInstance();
        
    }

}
