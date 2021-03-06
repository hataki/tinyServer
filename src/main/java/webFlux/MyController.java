package webFlux;

import java.io.IOException;
import java.util.Date;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;


@RestController
public class MyController {

	
//	@RequestMapping(value = "/sse",produces = "text/event-stream;charset=utf-8")
//	public Object xxoo(HttpServletRequest r) {
//		System.out.println("来啦 老弟！" + Thread.currentThread().getName());
//
//		Date date = new Date();
//		return "data:" + date.getTime() +  " \n\n";
//	}
	
	
	@GetMapping("/1")
	public String get() {
		
		System.out.println("----1");
		// Service
		String result = getResult();
		System.out.println("----2");
		return result;
		
	}

	
	
	@GetMapping("/2")
	public Mono<String> get2() {
		
		System.out.println("----1");
		// Service
		Mono<String> result = Mono.create(sink -> getResult());
		System.out.println("----2");
		return result;
		
	}

	private String getResult() {

		String s = null ;
			try {
				//数组来缓冲
				byte[] b = new byte[50];
				//读取数据
				int n = System.in.read(b);
				//转换为字符串
				s = new String(b,0,n);
				System.out.println("输入的字符串为：" + s);

			} catch (IOException e) {
				e.printStackTrace();
			}

		return s;
	}
}
