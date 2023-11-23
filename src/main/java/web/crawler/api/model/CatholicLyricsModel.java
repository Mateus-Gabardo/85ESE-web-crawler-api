package web.crawler.api.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "catholic_lyrics")
public class CatholicLyricsModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 250)
    private String title;
    @Column(columnDefinition = "VARCHAR", length = 250)
    private String author;
    @Column(columnDefinition = "TEXT")
    private String lyric;
    @Column(columnDefinition = "TEXT")
    private String cifra;
    @Column(name = "link_ref", columnDefinition = "VARCHAR", length = 250)
    private String linkRef;
    @Column(columnDefinition = "TEXT")
    private String tags;
}

