package org.codejudge.sb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang.StringUtils;

@Getter
@Setter
@NoArgsConstructor
@ToString(doNotUseGetters = true, callSuper = true)
public class EvalRequest {

    private Integer id;
    private String searchTitle;

    public static void validate(EvalRequest request) {
        request.validateSearchTitle();
    }

    private void validateSearchTitle() {
        if (StringUtils.isEmpty(searchTitle)) {
            throw new IllegalArgumentException("Search Title can't be empty!");
        }
    }
}
