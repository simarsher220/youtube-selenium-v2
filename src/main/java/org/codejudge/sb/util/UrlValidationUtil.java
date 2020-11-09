package org.codejudge.sb.util;

import org.apache.commons.validator.routines.UrlValidator;
import org.codejudge.sb.error.CustomException;
import org.springframework.http.HttpStatus;

public class UrlValidationUtil {

    public UrlValidationUtil() {
    }

    public static void validate(String api) throws CustomException {
        String[] schemes = new String[]{"s3", "https", "http", "ftp", "sftp"};
        UrlValidator urlValidator = new UrlValidator(schemes, 8L);
        if (!urlValidator.isValid(api)) {
            throw new CustomException("The Url isn't valid!", HttpStatus.BAD_REQUEST);
        }
    }
}