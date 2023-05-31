package org.outlaw.avito.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
@Builder
public class ImageDto {
    private MultipartFile file;
    private String type;
    private UUID adId;
}
