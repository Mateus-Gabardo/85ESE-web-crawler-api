package web.crawler.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import web.crawler.api.model.CatholicLyricsModel;
import web.crawler.api.repository.CatholicLyricsRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CatholicLyricsService {

    final CatholicLyricsRepository catholicLyricsRepository;

    public CatholicLyricsService(CatholicLyricsRepository catholicLyricsRepository) {
        this.catholicLyricsRepository = catholicLyricsRepository;
    }

    @Transactional
    public CatholicLyricsModel save(CatholicLyricsModel catholicLyricsModel){
        return this.catholicLyricsRepository.save(catholicLyricsModel);
    }

    public Page<CatholicLyricsModel> findAll(Pageable pageable) {
        return this.catholicLyricsRepository.findAll(pageable);
    }

    public Optional<CatholicLyricsModel> findById(Long id) {
        return this.catholicLyricsRepository.findById(id);
    }

    public void delete(CatholicLyricsModel saleItemModel) {
        this.catholicLyricsRepository.delete(saleItemModel);
    }

}
