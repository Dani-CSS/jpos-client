package com.daniele;

import com.daniele.service.Base24Channel;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.q2.Q2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public Q2 q2() {
		Q2 q2 = new Q2();
		q2.start();
		return q2;
	}

	private final Base24Channel.MuxManager muxManager;

	@Autowired
	public App(@Lazy Base24Channel.MuxManager muxManager) {
		this.muxManager = muxManager;
	}

	@GetMapping("echo")
	public String echo() throws ISOException {
		ISOMsg msg = new ISOMsg();
		msg.setMTI("0800");
		msg.set(11, "000001");
		msg.set(70, "301");
		ISOMsg respMsg = muxManager.getMux().request(msg, 30000);
		return respMsg.toString();
	}
}
