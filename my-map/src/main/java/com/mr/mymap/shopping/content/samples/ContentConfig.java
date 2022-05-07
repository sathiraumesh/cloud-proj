package com.mr.mymap.shopping.content.samples;

import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Key;
import com.mr.mymap.shopping.common.Config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;


/**
 * Wrapper for the JSON configuration file used to keep user specific details like the Merchant
 * Center account ID.
 */
public class ContentConfig extends Config {
    private static final String CONTENT_DIR = "content";
    private static final String FILE_NAME = "merchant-info.json";

    @Key private BigInteger merchantId = new BigInteger("560884867");

    @Key private String accountSampleUser = "merchant-center-1650186117263@merchant-center-1650186117263.iam.gserviceaccount.com";

    @Key private BigInteger accountSampleAdWordsCID;

    // These are no longer set via configuration, but instead by querying the API.
    private boolean isMCA;
    private String websiteUrl;

    public static ContentConfig load(File basePath) throws IOException {
        return new ContentConfig();
    }

    public BigInteger getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(BigInteger merchantId) {
        this.merchantId = merchantId;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getAccountSampleUser() {
        return accountSampleUser;
    }

    public void setAccountSampleUser(String accountSampleUser) {
        this.accountSampleUser = accountSampleUser;
    }

    public BigInteger getAccountSampleAdWordsCID() {
        return accountSampleAdWordsCID;
    }

    public void setAccountSampleAdWordsCID(BigInteger accountSampleAdWordsCID) {
        this.accountSampleAdWordsCID = accountSampleAdWordsCID;
    }

    public boolean getIsMCA() {
        return isMCA;
    }

    public void setIsMCA(boolean isMCA) {
        this.isMCA = isMCA;
    }
}
