package org.outlaw.avito.controllers;

import lombok.RequiredArgsConstructor;
import org.outlaw.avito.api.ImageApi;
import org.outlaw.avito.dto.ImageDto;
import org.outlaw.avito.models.*;
import org.outlaw.avito.repositories.AdRepository;
import org.outlaw.avito.repositories.UserRepository;
import org.outlaw.avito.service.ImageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ImageController implements ImageApi {

    private final ImageService imageService;
    private final UserRepository userRepository;
    private final AdRepository adRepository;
    @Override
    @Transactional
    public ResponseEntity<?> uploadImage(ImageDto imageDto) {
        try {

            if (imageDto.getType().equals("avatar")) {

                User authUser = userRepository.findById(UUID.fromString("57673bb8-0ea2-40e5-b223-236e7f91e7b5"))
                        .orElseThrow(() -> new UserPrincipalNotFoundException(":("));

                Avatar avatar = new Avatar();
                avatar.setUser(authUser);

                imageService.saveImage(imageDto.getFile(), avatar);

                authUser.setAvatar(avatar);

                return ResponseEntity.status(HttpStatus.CREATED).build();

            } else if (imageDto.getType().equals("adImage")) {
                if (imageDto.getAdId() == null) {
                    throw new IllegalArgumentException("adId is null");
                }

                Ad ad = adRepository.findById(imageDto.getAdId()).orElseThrow();

                AdImage adImage = new AdImage();
                adImage.setAd(ad);

                imageService.saveImage(imageDto.getFile(), adImage);

                return ResponseEntity.ok().build();
            }

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<?> getImage(UUID id) {
        Image image = imageService.getImageById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(image.getType()));

        return new ResponseEntity<>(image.getPic(), headers, HttpStatus.OK);
    }

}
