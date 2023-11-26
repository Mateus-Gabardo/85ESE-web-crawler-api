package web.crawler.api.model;

public class CrawlerUrlBuilder {
    private String scrapyUrl;
    private String spiderName;
    private boolean startRequests;
    private String url;
    private int maxRequests;

    private CrawlerUrlBuilder() {
    }

    public static CrawlerUrlBuilder builder() {
        return new CrawlerUrlBuilder();
    }

    public CrawlerUrlBuilder scrapyUrl(String scrapyUrl) {
        this.scrapyUrl = scrapyUrl;
        return this;
    }

    public CrawlerUrlBuilder spiderName(String spiderName) {
        this.spiderName = spiderName;
        return this;
    }

    public CrawlerUrlBuilder startRequests(boolean startRequests) {
        this.startRequests = startRequests;
        return this;
    }

    public CrawlerUrlBuilder url(String url) {
        this.url = url;
        return this;
    }

    public CrawlerUrlBuilder maxRequests(int maxRequests) {
        this.maxRequests = maxRequests;
        return this;
    }

    public String build() {
        StringBuilder apiUrl = new StringBuilder(scrapyUrl)
                .append("?spider_name=").append(spiderName);

        if(startRequests){
            apiUrl.append("&start_requests=").append(startRequests);
        }
        if (url != null) {
            apiUrl.append("&url=").append(url);
        }
        return apiUrl.toString();
    }
}

