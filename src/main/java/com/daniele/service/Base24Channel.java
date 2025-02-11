package com.daniele.service;

import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;
import org.jpos.iso.*;

import java.io.IOException;
import java.net.ServerSocket;

public class Base24Channel extends BaseChannel {

    boolean tpduSwap = true;
    int lenlen = 0;

    public Base24Channel() {
        super();
    }

    public Base24Channel(String host, int port, ISOPackager packager, byte[] TPDU) {
        super(host, port, packager);
        this.header = TPDU;
    }

    public Base24Channel(ISOPackager packager, byte[] TPDU) throws IOException {
        super(packager);
        this.header = TPDU;
    }

    public Base24Channel(ISOPackager packager, byte[] TPDU, ServerSocket serverSocket) throws IOException {
        super(packager, serverSocket);
        this.header = TPDU;
    }

    protected void sendMessageLength(int len) throws IOException {
        len += lenlen;
        serverOut.write (len >> 8);
        serverOut.write (len);
    }

    protected int getMessageLength() throws IOException, ISOException {
        byte[] b = new byte[2];
        serverIn.readFully(b,0,2);
        return (((int)b[0] &0xFF) << 8 | (int)b[1] &0xFF) - lenlen;
    }

    protected void sendMessageHeader(ISOMsg isoMsg, int len) throws IOException {
        byte[] header = isoMsg.getHeader();
        if (header != null) {
            if (tpduSwap && header.length == 5) {
                // swap src/dest address
                byte[] tmp = new byte[2];
                System.arraycopy (header,   1, tmp, 0, 2);
                System.arraycopy (header,   3,   header, 1, 2);
                System.arraycopy (tmp, 0,   header, 3, 2);
            }
        }
        else
            header = this.header;
        if (header != null)
            serverOut.write(header);
    }

    public void setHeader (String header) {
        super.setHeader (ISOUtil.str2bcd(header, false));
    }

    public void setConfiguration (Configuration cfg)
            throws ConfigurationException
    {
        super.setConfiguration (cfg);
        tpduSwap = cfg.getBoolean ("tpdu-swap", true);
        lenlen = cfg.getBoolean ("include-header-length", false) ? 2 : 0;
    }
}
