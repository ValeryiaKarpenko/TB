package com.tb.service.impl;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource({ "classpath:blockchain.properties" })
public class BlockchainConfiguration {
    
    @Autowired
    private Environment environment;

    @Bean
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager result = new PoolingHttpClientConnectionManager();
        result.setMaxTotal(Integer.parseInt(environment.getProperty("default.pool.max.total")));
        result.setDefaultMaxPerRoute(Integer.parseInt(environment.getProperty("default.pool.max.per.route")));
        return result;
    }

    @Bean
    public RequestConfig requestConfig() {
        RequestConfig result = RequestConfig.custom()
                .setConnectionRequestTimeout(Integer.parseInt(environment.getProperty("default.req.timeout")))
                .setConnectTimeout(Integer.parseInt(environment.getProperty("default.con.timeout")))
                .setSocketTimeout(Integer.parseInt(environment.getProperty("default.socket.timeout"))).build();
        return result;
    }

    @Bean
    public CloseableHttpClient httpClient(PoolingHttpClientConnectionManager poolingHttpClientConnectionManager,
            RequestConfig requestConfig) {
        CloseableHttpClient result = HttpClientBuilder.create().setConnectionManager(poolingHttpClientConnectionManager)
                .setDefaultRequestConfig(requestConfig).build();
        return result;
    }


}
