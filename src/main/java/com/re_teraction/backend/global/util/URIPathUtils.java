package com.re_teraction.backend.global.util;

import java.net.URI;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class URIPathUtils {

    public static URIPath extract(URI uri) {
        String path = uri.getPath().replaceFirst("^/", "");
        int lastSlash = path.lastIndexOf('/');
        return new URIPath(
                path.substring(0, lastSlash),
                path.substring(lastSlash + 1)
        );
    }

    public record URIPath(String directory, String filename) {

    }
}

