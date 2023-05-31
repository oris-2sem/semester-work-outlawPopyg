package org.outlaw.avito.api;

import org.outlaw.avito.dto.ImageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RequestMapping("/images")
public interface ImageApi {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> uploadImage(@ModelAttribute ImageDto imageDto);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<?> getImage(@PathVariable UUID id);

}
