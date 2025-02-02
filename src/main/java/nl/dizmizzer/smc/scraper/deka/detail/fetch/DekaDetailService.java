package nl.dizmizzer.smc.scraper.deka.detail.fetch;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DekaDetailService {

    private final DekaDetailRepository dekaDetailRepository;
    private final DekaDetailFetcher dekaDetailFetcher;
    public DekaDetailService(DekaDetailRepository dekaDetailRepository, DekaDetailFetcher dekaDetailFetcher) {

        this.dekaDetailRepository = dekaDetailRepository;
        this.dekaDetailFetcher = dekaDetailFetcher;
    }

    public DekaDetailProduct findProductDetail(long productId) {
        Optional<DekaDetailProduct> productOptional = dekaDetailRepository.findById(productId);
        return productOptional.orElseGet(() -> requestAndSave(productId));
    }

    public DekaDetailProduct requestAndSave(long productId) {
        DekaDetailProduct product = dekaDetailFetcher.fetchFromId(productId);
        if (product == null) return null;
        dekaDetailRepository.save(product);
        return product;
    }
}
