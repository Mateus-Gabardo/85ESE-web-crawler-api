package web.crawler.api.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

public class CatholicLyricsDto {

    private String title;

    private String author;

    private String lyric;

    private String cifra;

    private String linkRef;

    private String tags;
}
