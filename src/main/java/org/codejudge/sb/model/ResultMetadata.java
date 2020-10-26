package org.codejudge.sb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(doNotUseGetters = true, callSuper = true)
public class ResultMetadata {

    private String videoLink;
    private Integer likes;
    private Integer disLikes;

    public ResultMetadata(String videoLink, Integer likes, Integer disLikes) {
        this.videoLink = videoLink;
        this.likes = likes;
        this.disLikes = disLikes;
    }
}
