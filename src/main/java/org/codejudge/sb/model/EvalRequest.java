package org.codejudge.sb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang.StringUtils;
import org.codejudge.sb.error.CustomException;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@ToString(doNotUseGetters = true, callSuper = true)
public class EvalRequest {

    private Integer id;
    private String searchTitle;

    public static void validate(EvalRequest request) throws CustomException {
        request.validateSearchTitle();
    }

    private void validateSearchTitle() throws CustomException {
        if (StringUtils.isEmpty(searchTitle)) {
            throw new CustomException("Search Title can't be empty!", HttpStatus.BAD_REQUEST);
        }
    }
}
