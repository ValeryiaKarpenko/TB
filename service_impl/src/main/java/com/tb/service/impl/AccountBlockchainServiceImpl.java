package com.tb.service.impl;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tb.dal.api.AccountBlockchainDao;
import com.tb.model.AccountBlockchain;
import com.tb.service.api.AccountBlockchainService;

@Service
@Transactional
public class AccountBlockchainServiceImpl implements AccountBlockchainService{
    
    @Autowired
    private AccountBlockchainDao accountBlockchainDao;
    
    @Override
    public AccountBlockchain createAccountBlockchain(Integer balance){
        Map<String, String> map = null;
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("http://localhost:8081/create_account");
         
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
            e.printStackTrace();
        }
        
        AccountBlockchain newAccountBlockchain = new AccountBlockchain();
        newAccountBlockchain.setAddress(map.get("address"));
        newAccountBlockchain.setMnemonic(map.get("mnemonic"));
        newAccountBlockchain.setBalance(balance);
        return accountBlockchainDao.create(newAccountBlockchain);
    }

}
