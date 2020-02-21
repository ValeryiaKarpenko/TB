package com.tb.service.impl;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tb.dal.api.AccountBlockchainDao;
import com.tb.model.AccountBlockchain;
import com.tb.service.api.AccountBlockchainService;
import com.tb.service.utils.CustomCryptEncoder;

@Service
@Transactional
@PropertySource({ "classpath:service.properties" })
public class AccountBlockchainServiceImpl implements AccountBlockchainService{
    
    @Value("${blockchain.host}")
    private String host;

    @Value("${blockchain.url.createAccount}")
    private String url;
    
    @Value("${blockchain.address}")
    private String address;
    
    @Value("${blockchain.mnemonic}")
    private String mnemonic;
    
    @Autowired
    private CustomCryptEncoder caCryptEncoder;
    
    @Autowired
    private AccountBlockchainDao accountBlockchainDao;
    
    @Autowired
    private CloseableHttpClient client;
    
    private final Logger logger = LoggerFactory.getLogger(AccountBlockchainServiceImpl.class);
    
    @Override
    public AccountBlockchain createAccountBlockchain(){
        Map<String, String> map = null;
        try {

            HttpGet httpGet = new HttpGet(host + url);
            CloseableHttpResponse response = client.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpGet.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            ObjectMapper mapper = new ObjectMapper();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
                map = mapper.readValue(result, new TypeReference<Map<String, String>>() {});
            }
            EntityUtils.consume(entity);
            response.close();
        } catch (IOException e) {
            logger.error("Can't create account in blockchain: " + e);
            e.printStackTrace();
        }
        
        AccountBlockchain newAccountBlockchain = new AccountBlockchain();
        newAccountBlockchain.setAddress(map.get(address));
        newAccountBlockchain.setMnemonic(caCryptEncoder.encode(map.get(mnemonic)));
        newAccountBlockchain.setBalance(0);
        return accountBlockchainDao.create(newAccountBlockchain);
    }

}
