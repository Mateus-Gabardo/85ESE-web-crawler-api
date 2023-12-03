package web.crawler.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import web.crawler.api.dto.CrawlRequestDto;
import web.crawler.api.model.CatholicLyricsModel;
import web.crawler.api.model.CrawlerUrlBuilder;
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

    public ResponseEntity<String> startCrawler(CrawlRequestDto request){
        String scrapyUrl = "http://localhost:9080/crawl.json";
        String spiderName = request.getSpiderName() != null ? request.getSpiderName() : "cifras_club_item";
        String url = request.getUrl() != null ? request.getUrl() : "https://www.cifraclub.com.br/catolicas/eu-navegarei/";

        String apiUrl = CrawlerUrlBuilder.builder()
                .scrapyUrl(scrapyUrl)
                .spiderName(spiderName)
                .url(url)
                .build();

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(apiUrl, String.class);
    }

}
