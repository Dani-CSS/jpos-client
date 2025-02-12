package com.daniele.controller;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.MUX;
import org.jpos.q2.iso.QMUX;
import org.jpos.util.NameRegistrar;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class EchoController {
    private Logger log = (Logger) LoggerFactory.getLogger(EchoController.class);

    @PostMapping("echo")
    public String echo() throws NameRegistrar.NotFoundException, ISOException {
        MUX mux = QMUX.getMUX("s-mux");
        ISOMsg msg= new ISOMsg();
        msg.setMTI("0800");
        msg.set(11, "000001");
        msg.set(70, "301");
        ISOMsg respMsg = mux.request(msg, 2000);
        log.info("RespMsg {}" + respMsg);
        return "echo";
    }
}
