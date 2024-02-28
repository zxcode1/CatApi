//package org.example;
//
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.client.SimpleClientHttpRequestFactory;
//import org.springframework.web.client.RestClient;
//import org.springframework.web.client.support.RestClientAdapter;
//import org.springframework.web.service.annotation.GetExchange;
//import org.springframework.web.service.invoker.HttpServiceProxyFactory;
//
//import java.net.InetSocketAddress;
//import java.net.Proxy;
//import java.util.List;
//
//@SpringBootApplication
//public class App {
//
//    public static void main(String[] args) {
//        SpringApplication.run(App.class, args);
//    }
//
//    @Bean
//    RestClient restClient(RestClient.Builder builder) {
//        String proxyHost = "proxy1.lan.bnm.md";
//        int proxyPort = 8080;
//        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
//        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
//        requestFactory.setProxy(proxy);
//        return builder
//                .requestFactory(requestFactory)
//                .baseUrl("https://jsonplaceholder.typicode.com/todos")
//                .build();
//    }
//
//    @Bean
//    TodoClient todoClient(RestClient restClient) {
//        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(
//                RestClientAdapter.create(restClient)).build();
//        return factory.createClient(TodoClient.class);
//    }
//
//    @Bean
//    ApplicationRunner applicationRunner(TodoClient todoClient) {
//        return args -> {
//            var response = todoClient.todos();
//            System.out.println(response);
//        };
//    }
//
//    record Todo(Long id, long userId, String title) {}
//
//    interface TodoClient {
//        @GetExchange
//        List<Todo> todos();
//    }
//}
