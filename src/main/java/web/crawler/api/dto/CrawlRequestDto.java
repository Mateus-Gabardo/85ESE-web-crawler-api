package web.crawler.api.dto;

import lombok.Data;

@Data
public class CrawlRequestDto {
    private String spiderName;
    private String url;
}
