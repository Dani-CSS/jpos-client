package com.daniele.listener;

import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOSource;
import org.jpos.iso.ISOMsg;
import org.springframework.stereotype.Service;

@Service
public class MyISORequestListener implements ISORequestListener {

    @Override
    public boolean process(ISOSource source, ISOMsg m) {
        // Lógica de processamento da mensagem
        return true;
    }
}
