package com.daniele.service;

import org.jpos.iso.MUX;
import org.jpos.q2.Q2;
import org.jpos.q2.iso.QMUX;
import org.jpos.util.NameRegistrar;
import org.springframework.stereotype.Component;

    @Component
    public class MuxManager {

        private final MUX mux;

        public MuxManager(Q2 q2) throws NameRegistrar.NotFoundException, InterruptedException {
            while (!q2.ready()) {
                Thread.sleep(10);
            }
            this.mux = QMUX.getMUX("my-mux");
        }

        public MUX getMux() {
            return mux;
        }
    }
