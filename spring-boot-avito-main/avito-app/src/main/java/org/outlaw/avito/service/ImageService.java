package org.outlaw.avito.service;

import org.outlaw.avito.models.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface ImageService {
    void saveImage(MultipartFile file, Image image) throws IOException;
    Image getImageById(UUID id);
}
