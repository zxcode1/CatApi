//package org.example;
//
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.client.SimpleClientHttpRequestFactory;
//import org.springframework.web.client.RestTemplate;
//
//import java.net.InetSocketAddress;
//import java.net.Proxy;
//
//public class ProxyRestTemplateExample {
//
//    public static void main(String[] args) {
//
//        String proxyHost = "proxy1.lan.bnm.md";
//        int proxyPort = 8080;
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        // Создание объекта прокси
//        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
//        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
//        requestFactory.setProxy(proxy);
//        restTemplate.setRequestFactory(requestFactory);
//
//        String url = "https://jsonplaceholder.typicode.com/todos";
//
//        // Отправка GET-запроса и получение ответа
//        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
//        System.out.println("Response: " + response.getBody());
//    }
//}
