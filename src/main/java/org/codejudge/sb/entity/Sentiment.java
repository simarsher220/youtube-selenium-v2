package org.codejudge.sb.entity;

import com.fasterxml.jackson.databind.node.JsonNodeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.codejudge.sb.model.ProcStatus;
import org.codejudge.sb.model.ResultMetadata;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString(doNotUseGetters = true, callSuper = true)
@Entity
@Table(name = "sentiment")
@TypeDef(name = "json", typeClass = JsonNodeType.class)
public class Sentiment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "search_title")
    private String searchTitle;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "likes")
    private Integer likes;

    @Column(name = "dislikes")
    private Integer dislikes;

    @Enumerated(value = EnumType.STRING)
    private ProcStatus status;

    public Sentiment(SentimentBuilder builder) {
        this.id = builder.id;
        this.searchTitle = builder.searchTitle;
        this.status = builder.status;
    }

    public static class SentimentBuilder {

        private Integer id;
        private String searchTitle;
        private ProcStatus status;

        public SentimentBuilder() {
        }

        public SentimentBuilder searchTitle(String searchTitle) {
            this.searchTitle = searchTitle;
            return this;
        }

        public Sentiment build() {
            return new Sentiment(this);
        }

        public SentimentBuilder status(ProcStatus status) {
            this.status = status;
            return this;
        }
    }
}
