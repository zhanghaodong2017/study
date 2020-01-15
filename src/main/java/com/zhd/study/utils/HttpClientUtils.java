package com.zhd.study.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtils {
    private static final int SUCCESS_CODE = 200;

    public static String doGet(String url) {
        return doGet(url, null, null);
    }

    public static String doGet(String url, Map<String, String> params, Map<String, String> headers) {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (params != null) {
                for (String key : params.keySet()) {
                    builder.addParameter(key, params.get(key));
                }
            }
            URI uri = builder.build();
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            if (headers != null) {
                for (String name : headers.keySet()) {
                    httpGet.setHeader(name, headers.get(name));
                }
            }
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == SUCCESS_CODE) {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(response);
            close(httpclient);
        }
        return "";
    }

    public static String doGetProxy(String url, Map<String, String> headers, String hostname, int port) {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            URI uri = builder.build();


            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            HttpHost proxy = new HttpHost(hostname, port);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setProxy(proxy)
                    .setConnectTimeout(5000)
                    .setSocketTimeout(5000)
                    .setConnectionRequestTimeout(3000)
                    .build();
            httpGet.setConfig(requestConfig);
            if (headers != null) {
                for (String name : headers.keySet()) {
                    httpGet.setHeader(name, headers.get(name));
                }
            }
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == SUCCESS_CODE) {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }else{
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(response);
            close(httpclient);
        }
        return "";
    }


    public static String doPost(String url) {
        return doPost(url, null);
    }

    public static String doPost(String url, Map<String, String> params) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String result = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建参数列表
            if (params != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : params.keySet()) {
                    paramList.add(new BasicNameValuePair(key, params.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "utf-8");
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String doPostJson(String url, String json) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String result = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(json.toString(), "UTF-8");//解决中文乱码问题
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return result;
    }

    public static InputStream loadFileFromNet(String url) {
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);
        try {
            HttpResponse response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                return entity.getContent();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] loadByteFromNet(String url) {
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);
        try {
            HttpResponse response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toByteArray(response.getEntity());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] doGetReturnByte(String url, String params) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet requestGet = new HttpGet(url + "?" + params);
        try {
            HttpResponse httpResponse = httpClient.execute(requestGet);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = httpResponse.getEntity();
                return EntityUtils.toByteArray(httpEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] doPostReturnByte(String url, Map<String, Object> params) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost requestPost = new HttpPost(url);

        List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
        try {
            if (params != null) {
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue().toString();
                    BasicNameValuePair nameValuePair = new BasicNameValuePair(key, value);
                    parameters.add(nameValuePair);
                }
            }
            requestPost.setEntity(new UrlEncodedFormEntity(parameters, "utf-8"));
            HttpResponse httpResponse = httpClient.execute(requestPost);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = httpResponse.getEntity();
                return EntityUtils.toByteArray(httpEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] doPostJsonReturnByte(String url, String json) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost requestPost = new HttpPost(url);

        try {
            // 创建请求内容
            StringEntity entity = new StringEntity(json.toString(), "UTF-8");//解决中文乱码问题
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            requestPost.setEntity(entity);
            HttpResponse httpResponse = httpClient.execute(requestPost);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = httpResponse.getEntity();
                return EntityUtils.toByteArray(httpEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}