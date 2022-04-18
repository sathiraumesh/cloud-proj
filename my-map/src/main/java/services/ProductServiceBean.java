package services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repositories.ProductRepository;

@RequiredArgsConstructor
@Service
public class ProductServiceBean implements ProductService{

    private final ProductRepository productRepository;
}
