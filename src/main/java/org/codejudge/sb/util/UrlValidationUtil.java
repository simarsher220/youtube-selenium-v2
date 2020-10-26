package org.codejudge.sb.util;

public class UrlValidationUtil {

    public UrlValidationUtil() {
    }

    public static void validate(String api) {
        String[] schemes = new String[]{"s3", "https", "http", "ftp", "sftp"};
//        UrlValidator urlValidator = new UrlValidator(schemes, 8L);
//        if (!urlValidator.isValid(api)) {
//            throw new IllegalArgumentException("The Url isn't valid!");
//        }
    }
}