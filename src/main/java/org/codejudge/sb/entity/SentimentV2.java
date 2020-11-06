package org.codejudge.sb.entity;

import com.fasterxml.jackson.databind.node.JsonNodeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.codejudge.sb.model.ProcStatus;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString(doNotUseGetters = true, callSuper = true)
@Entity
@Table(name = "sentiment_v2")
@TypeDef(name = "json", typeClass = JsonNodeType.class)
public class SentimentV2 {

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

    public SentimentV2(SentimentV2.SentimentBuilderV2 builder) {
        this.id = builder.id;
        this.searchTitle = builder.searchTitle;
        this.likes = builder.likes;
        this.dislikes = builder.dislikes;
        this.videoUrl = builder.videoUrl;
    }

    public static class SentimentBuilderV2 {

        private Integer id;
        private String searchTitle;
        private Integer likes;
        private Integer dislikes;
        private String videoUrl;

        public SentimentBuilderV2() {
        }

        public SentimentV2.SentimentBuilderV2 searchTitle(String searchTitle) {
            this.searchTitle = searchTitle;
            return this;
        }

        public SentimentV2 build() {
            return new SentimentV2(this);
        }

        public SentimentV2.SentimentBuilderV2 dislikes(Integer dislikes) {
            this.dislikes = dislikes;
            return this;
        }

        public SentimentV2.SentimentBuilderV2 videoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
            return this;
        }

        public SentimentV2.SentimentBuilderV2 likes(Integer likes) {
            this.likes = likes;
            return this;
        }
    }
}
