package com.heina.customer.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.collections.MapUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;


public class HttpsClient {

    private static final MediaType JSON_MEDIA = MediaType.parse("application/json; charset=utf-8");

    public JSONObject post(String url, Map<String, String> params, JSONObject body) {
        JSONObject result = new JSONObject();
        OkHttpClient client = new OkHttpClient.Builder().sslSocketFactory(createSSLSocketFactory(), new TrustAllCerts())
                .hostnameVerifier(new TrustAllHostnameVerifier()).build();
        StringBuilder sb = new StringBuilder();
        sb.append(url).append(Constants.URL_PARAM_PREFIX);
        if (!params.isEmpty()) {
            Iterator<String> iterator = params.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                sb.append(key).append(Constants.URL_PARAM_EQUALS).append(MapUtils.getString(params, key));
                if (iterator.hasNext()) {
                    sb.append(Constants.URL_PARAM_SEPARATOR);
                }
            }
        }
        RequestBody requestBody = RequestBody.create(JSON_MEDIA, body.toJSONString());
        Request request = new Request.Builder().url(sb.toString()).post(requestBody).build();
        try {
            Response response = client.newCall(request).execute();
            result = JSON.parseObject(response.body().string());
            result.put("status", 0);
        } catch (IOException e) {
            result.put("status", -1);
            result.put("msg", e.getMessage());
        }
        return result;
    }

    public JSONObject get(String url, Map<String, String> params) {
        JSONObject result = new JSONObject();
        OkHttpClient client = new OkHttpClient.Builder().sslSocketFactory(createSSLSocketFactory(), new TrustAllCerts())
                .hostnameVerifier(new TrustAllHostnameVerifier()).build();
        StringBuilder sb = new StringBuilder();
        sb.append(url).append(Constants.URL_PARAM_PREFIX);
        if (!params.isEmpty()) {
            Iterator<String> iterator = params.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                sb.append(key).append(Constants.URL_PARAM_EQUALS).append(MapUtils.getString(params, key));
                if (iterator.hasNext()) {
                    sb.append(Constants.URL_PARAM_SEPARATOR);
                }
            }
        }
        Request request = new Request.Builder().url(sb.toString()).build();
        try {
            Response response = client.newCall(request).execute();
            result = JSON.parseObject(response.body().string());
            result.put("status", 0);
        } catch (IOException e) {
            result.put("status", -1);
            result.put("msg", e.getMessage());
        }
        return result;
    }

    private static class TrustAllCerts implements X509TrustManager {
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());
            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ssfFactory;
    }
}
