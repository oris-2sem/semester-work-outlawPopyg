package org.outlaw.avito.service;

import lombok.RequiredArgsConstructor;
import org.outlaw.avito.models.Image;
import org.outlaw.avito.repositories.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository repository;

    @Override
    public void saveImage(MultipartFile file, Image image) throws IOException {

        image.setName(file.getOriginalFilename());
        image.setType(file.getContentType());
        image.setPic(file.getInputStream().readAllBytes());

        repository.save(image);
    }

    @Override
    public Image getImageById(UUID id) {
        return repository.findById(id).orElseThrow();
    }
}
