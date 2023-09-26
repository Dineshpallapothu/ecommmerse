import com.ecommers.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRespository extends JpaRepository<Profile,Integer> {

}
