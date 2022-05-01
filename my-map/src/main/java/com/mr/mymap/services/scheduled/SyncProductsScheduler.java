package com.mr.mymap.services.scheduled;

import com.mr.mymap.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.mr.mymap.shopping.product.ProductListSync;

import java.io.IOException;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class SyncProductsScheduler {

    @Autowired
    private ProductListSync productListSync;

    @Scheduled(fixedRate = 10000000)
    public void syncProducts() throws IOException {
        productListSync.execute();
        System.out.println("Syncing the products");
    }
}
