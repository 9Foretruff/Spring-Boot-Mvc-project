package org.foretruff.collegemvcproject.util;


import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class ImageUtil {

    public String getImageUrl(byte[] photo) {
        return "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(photo);
    }
}
