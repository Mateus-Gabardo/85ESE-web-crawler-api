package web.crawler.api.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.crawler.api.model.CatholicLyricsModel;

@Repository
public interface CatholicLyricsRepository extends JpaRepository<CatholicLyricsModel, Long>{
}
