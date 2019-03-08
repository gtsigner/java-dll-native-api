package com.oeynet.dev.mockserver.utils;

import com.alibaba.fastjson.JSON;
import com.oeynet.dev.mockserver.domain.models.Client;
import com.oeynet.dev.mockserver.domain.models.Config;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ConfigUtil {
    public static Config getConfig() throws IOException {
        URL url = ResourceUtils.getURL("config.json");

        System.out.println("配置文件URL: " + url);
        InputStream stream = url.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
        StringBuilder str = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            str.append(line);
        }
        br.close();
        return JSON.parseObject(str.toString(), Config.class);
    }

    /**
     * 获取交易的数据
     * @return
     * @throws IOException
     */
    public static Map<String, Client> getTrades() throws IOException {
        Map<String, Client> map = new HashMap<>();
        Config config = ConfigUtil.getConfig();
        for (Client client : config.getTrades()) {
            map.put(client.getToken(), client);
        }
        return map;
    }

}
