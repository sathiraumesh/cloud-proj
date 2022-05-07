package com.mr.mymap.controllers;

import com.google.cloud.storage.*;
import com.mr.mymap.domain.JwtToken;
import com.mr.mymap.domain.User;
import com.mr.mymap.repositories.ProductRepository;
import com.mr.mymap.services.UserService;
import com.mr.mymap.shopping.common.Authenticator;
import com.mr.mymap.shopping.product.ProductListSync;
import com.mr.mymap.utils.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigInteger;

@RestController()
@RequestMapping(path ="/api/users")
public class UserController {
    @Autowired
    UserService userService;

    private static Storage storage = StorageOptions.getDefaultInstance().getService();
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/signup")
    public User registerUser(@RequestBody User user){
        User newUser =  this.userService.registerUser(user);
        return newUser;
    }

    @PostMapping("/login")
    public JwtToken LogInUser(@RequestBody User user) throws Exception{
        userService.authenticate(user.email, user.password);
        UserDetails userDetails = userService.loadUserByUsername(user.email);
        String token = jwtTokenUtil.generateToken(userDetails);
        return new JwtToken(token);
    }

    @PostMapping("/sync")
    public void syncProducts(@RequestBody User user) throws IOException {
        ProductListSync productListSync = new ProductListSync(new String[]{}, new BigInteger(user.getMerchantId()), productRepository);
        productListSync.execute();
    }

    @PostMapping("/secret")
    public void updateUser(@RequestParam("file")MultipartFile file) throws IOException {
    uploadObject("merchant-center-1650186117263","map_store_secrets", file.getName(), file);
    }

    public static void uploadObject(
            String projectId, String bucketName, String objectName, MultipartFile file) throws IOException {
        Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
        BlobId blobId = BlobId.of(bucketName, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        storage.create(blobInfo, file.getBytes());
        Blob blob = storage.get(blobId);
        String value = new String(blob.getContent());
        InputStream inputStream = new ByteArrayInputStream(blob.getContent());
        System.out.println(value);
    }
}
