package bhz.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


@EnableCircuitBreaker	//开启断路器功能
@EnableDiscoveryClient	//表示我是一个服务,注册到服务中心上
@SpringBootApplication
public class Application {

	
	@Bean
	@ConfigurationProperties(prefix = "custom.rest")
	public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory(){
		HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		return new HttpComponentsClientHttpRequestFactory();
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate(httpComponentsClientHttpRequestFactory());
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
