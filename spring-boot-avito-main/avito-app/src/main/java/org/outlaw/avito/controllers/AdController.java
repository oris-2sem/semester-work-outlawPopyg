package org.outlaw.avito.controllers;

import lombok.RequiredArgsConstructor;
import org.outlaw.avito.api.AdApi;
import org.outlaw.avito.models.Ad;
import org.outlaw.avito.models.AdCategory;
import org.outlaw.avito.repositories.AdRepository;
import org.outlaw.avito.repositories.CategoryRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdController implements AdApi {
    private final AdRepository adRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void createAd(Ad ad) {
        adRepository.save(ad);
    }

    @Override
    public void createCategory(AdCategory category) {
        categoryRepository.save(category);
    }
}
