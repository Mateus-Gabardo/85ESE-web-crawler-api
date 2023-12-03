package web.crawler.api.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import web.crawler.api.dto.CatholicLyricsDto;
import web.crawler.api.dto.CrawlRequestDto;
import web.crawler.api.model.CatholicLyricsModel;
import web.crawler.api.model.CrawlerUrlBuilder;
import web.crawler.api.service.CatholicLyricsService;

import javax.validation.Valid;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/catholicLyrics")
public class CatholicLyricsController {
    final CatholicLyricsService catholicLyricsService;

    public CatholicLyricsController(CatholicLyricsService catholicLyricsService) {
        this.catholicLyricsService = catholicLyricsService;
    }

    @PostMapping
    public ResponseEntity<Object> saveProduct(@RequestBody @Valid CatholicLyricsDto lyricsDto){
        CatholicLyricsModel lyrics = new CatholicLyricsModel();
        BeanUtils.copyProperties(lyricsDto, lyrics);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.catholicLyricsService.save(lyrics));
    }

    @GetMapping
    public ResponseEntity<Page<CatholicLyricsModel>> getLyrics(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable page){
        return ResponseEntity.status(HttpStatus.OK).body(this.catholicLyricsService.findAll(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getLyric(@PathVariable(value = "id") Long id) {
        Optional<CatholicLyricsModel> lyrics = this.catholicLyricsService.findById(id);
        if(lyrics.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lyrics not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(lyrics.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteLyric(@PathVariable(value = "id") Long id) {
        Optional<CatholicLyricsModel> lyricModelOptional = this.catholicLyricsService.findById(id);
        if(lyricModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lyrics not found!");
        }
        this.catholicLyricsService.delete(lyricModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Lyric deleted!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateLyric(@PathVariable(value = "id") Long id,
                                                @RequestBody @Valid CatholicLyricsDto lyricDto){

        Optional<CatholicLyricsModel> productModelOptional = this.catholicLyricsService.findById(id);
        if(productModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lyrics not found!");
        }
        CatholicLyricsModel lyricModel = new CatholicLyricsModel();
        BeanUtils.copyProperties(lyricDto, lyricModel);
        lyricModel.setId(productModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(this.catholicLyricsService.save(lyricModel));
    }

    @PostMapping("/startCrawl")
    public ResponseEntity<String> startCrawl(@RequestBody CrawlRequestDto crawlRequest) {
        ResponseEntity<String> response = catholicLyricsService.startCrawler(crawlRequest);
        return ResponseEntity.ok(response.getBody());
    }

    @PostMapping("/startCrawlAll")
    public ResponseEntity<String> startCrawlAll() {
        CompletableFuture.runAsync(catholicLyricsService::startCrawlerAll);
        return ResponseEntity.ok("O processo de rastreamento foi iniciado. Aguarde a conclusão.");
    }
}
