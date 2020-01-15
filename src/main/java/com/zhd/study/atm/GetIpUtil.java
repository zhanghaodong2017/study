package com.zhd.study.atm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhd.study.utils.HttpClientUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetIpUtil {
    private static final String MY_IP_API = "https://www.ipip.net/ip.html";
    private static final String xi_ci_ip = "https://www.xicidaili.com/";

    public static void main(String[] args) throws Exception {
        IPBean oneProxyIP = new IPBean("115.29.108.117", 8118, 2);
        System.out.println(JSON.toJSONString(oneProxyIP));
//        String v4IP = getV4IP();
//        List<IPBean> beanList = crawl(xi_ci_ip);
        boolean valid = isValid(oneProxyIP);
//        System.out.println(valid);
//        System.out.println(JSON.toJSONString(beanList));
    }


    private static Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Connection", "keep-alive");
        headers.put("Pragma", "no-cache");
        headers.put("Cache-Control", "no-cache");
        headers.put("Upgrade-Insecure-Requests", "1");
        headers.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");
        headers.put("Sec-Fetch-User", "?1");
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        headers.put("Sec-Fetch-Site", "none");
        headers.put("Sec-Fetch-Mode", "navigate");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("Accept-Language", "zh-CN,zh;q=0.9");
        headers.put("Cookie", "PSTM=1566569487; BD_UPN=123253; BIDUPSID=57A7B87094DA19068CE0B2A9EFFE8E36; BAIDUID=326D9F5ACF5AC300B561B520CF51BCA8:FG=1; H_WISE_SIDS=130610_137735_133104_100808_136646_136748_137775_106198_134982_136436_137158_120142_137708_133981_137718_132909_136455_137690_135846_131246_137743_132378_136680_118879_118865_118850_118837_118803_137890_107316_136800_136431_136094_133352_137901_137222_136862_129650_136194_137105_133847_132552_137467_129645_131423_137564_137465_137803_110085_127969_127417_137433_136636_137207_136321_137619_137449_136988; MCITY=-173%3A326%3A; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; BDUSS=2NLZ3VzZy10YXJkWjFYTGlZVUpLZDROcHNjQTF1NUZTU3R5NkRycVVrT1FuajllSVFBQUFBJCQAAAAAAAAAAAEAAACxq8MftPPJ8bK7ysfSu7DjyMsAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAJARGF6QERheQ; BDSFRCVID=zyCOJeC62RXInpTuyuPUw7H0nm-bejRTH6aofPqRQK3wiaFjJ9QOEG0Pof8g0KubZS6EogKK0eOTHktF_2uxOjjg8UtVJeC6EG0Ptf8g0f5; H_BDCLCKID_SF=tJP8oC-hfIP3fP36q4oMb-_thMuX5-RLfK7B2p7F5l8-h4n53PTAjJL-5J3U-5b7tIoLsRoH2foxOKQphP6zDRKZLnJpLJvD5gcpQJ3N3KJmefK9bT3v5tjDM4rw2-biWbR-2Mbd2-bP_IoG2Mn8M4bb3qOpBtQmJeTxoUJ25DnJhhCGe6Khj5bWjaLqqbbfb-of3Rrea-5Eq5rnhPF3Dl_TXP6-35KHyejw3J05b4ThVKT6jUA2h4rD-xoBQq37JD6ybb82Jf7ZEp6EQUTd3t0D-4oxJpOBMnbMopvaKDj4s-jvbURvD-Lg3-7W-U5dtjTO2bc_5KnlfMQ_bf--QfbQ0hOhqP-jBRIEoDI2tCLWhK-r5PQ_-4F_hxrjetJyaR3EXlOvWJ5TMCo-WMr_Kh002qoTLR-fWnRXW66t5RcCShPC-tnt0b_w0t4e--cK3DQZVxjv3l02Vb7Ie-t2ynLVWR-q04RMW20j0l7mWnvMsxA45J7cM4IseboJLfT-0bc4KKJxbnLWeIJIjj6jK4JKDGtJJ5OP; BDRCVFR[feWj1Vr5u3D]=I67x6TjHwwYf0; delPer=0; BD_CK_SAM=1; PSINO=1; BD_HOME=1; sug=3; sugstore=0; ORIGIN=0; bdime=0; COOKIE_SESSION=10406_0_8_8_3_4_0_0_8_3_0_0_481_0_0_0_1578823603_0_1578824101%7C9%23199_103_1578812941%7C9; pgv_pvi=2664556544; pgv_si=s7712432128; H_PS_PSSID=30586_1426_21099_30211_26350; H_PS_645EC=8c59Ic88T1XnwLC0YiRWDb1VWy010sLliTudV1gLhILUT%2FoVEmR3JKcNwoo; BDSVRTM=0");
        return headers;
    }


    public static IPBean getOneProxyIP() {
        String url = "http://47.106.160.121/Index-generate_api_url.html?packid=1&fa=0&qty=1&port=1&format=json&ss=5&css=&ipport=1&et=1&pi=1&co=1&pro=&city=&usertype=13";

        String s = "http://47.106.160.121/Index-generate_api_url.html?packid=0&qty=1&port=1&format=txt&ss=1&css=&ipport=0&et=0&pi=0&co=0&pro=&city=";

        String etdaili = "http://h.etdaili.com/Users-whiteIpAddNew.html?appid=XX&appkey=XX&whiteip=";

        try {
            Document document = Jsoup.connect(url).get();
            JSONObject json = JSON.parseObject(document.text());
            JSONArray dataJson = json.getJSONArray("data");
            for (int i = 0; i < dataJson.size(); i++) {
                JSONObject data = dataJson.getJSONObject(i);
                String ip = data.getString("IP");
                String[] split = ip.split(":");
                return new IPBean(split[0], Integer.valueOf(split[1]), 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<IPBean> crawl(String api) throws Exception {
        Map<String, String> headers = getHeaders();
        String html = HttpClientUtils.doGet(api, null, headers);
        System.out.println(html);
        Document document = Jsoup.parse(html);
        Elements eles = document.selectFirst("table").select("tr");
        List<IPBean> ipList = new ArrayList<>();
        for (int i = 0; i < eles.size(); i++) {
            try {
                if (i == 0) {
                    continue;
                }
                Element ele = eles.get(i);
                String ip = ele.children().get(1).text();
                int port = Integer.parseInt(ele.children().get(2).text().trim());
                String typeStr = ele.children().get(5).text().trim();

                int type;
                if ("HTTP".equalsIgnoreCase(typeStr)) {
                    type = IPBean.TYPE_HTTP;
                } else {
                    type = IPBean.TYPE_HTTPS;
                }

                IPBean ipBean = new IPBean(ip, port, type);
                if (type == IPBean.TYPE_HTTPS) {
                    ipList.add(ipBean);
                }
            } catch (Exception e) {
            }
        }
        return ipList;
    }

    /**
     * 检测代理ip是否有效
     *
     * @param ipBean
     * @return
     */
    public static boolean isValid(IPBean ipBean) {
        try {
            Map<String, String> headers = getHeaders();
            String uri = "https://www.baidu.com/s?wd=ip";
            String html = HttpClientUtils.doGetProxy(uri, headers, ipBean.getIp(), ipBean.getPort());
            System.out.println(html);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getV4IP() {
        String chinaz = "https://www.baidu.com/s?wd=ip";
        Map<String, String> headers = getHeaders();
        String html = HttpClientUtils.doGet(chinaz, null, headers);
        System.out.println(html);
        Document document = Jsoup.parse(html);

        return "";
    }
}
