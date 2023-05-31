package org.outlaw.avito.api;

import org.outlaw.avito.models.Ad;
import org.outlaw.avito.models.AdCategory;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/ad")
public interface AdApi {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createAd(@RequestBody Ad ad);

    @PostMapping("/category")
    @ResponseStatus(HttpStatus.CREATED)
    void createCategory(@RequestBody AdCategory category);
}
