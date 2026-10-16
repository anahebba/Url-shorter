package com.urlshortner.urlshortner;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class UrlService {
    private final UrlMappingRepository repository;

    public UrlService(UrlMappingRepository repository) {
        this.repository = repository;
    }


    public UrlMapping shorten(String longUrl) {
        UrlMapping mapping = repository.save(new UrlMapping(longUrl)); // save → DB gives id
        String code = Base62.encode(mapping.getId() + 100000);                // id → short code
        mapping.setShortCode(code);
        return repository.save(mapping);                               // save again with code
    }

    public Optional<String> getLongUrl(String shortCode) {
        return repository.findByShortCode(shortCode)
                .map(UrlMapping::getLongUrl);
    }

    public List<UrlMapping> getAllUrls(){
        return repository.findAll();
    }
}
