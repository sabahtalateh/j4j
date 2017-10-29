package com.sabahtalateh.jenkov_tutorials.networking.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * ClientExample.
 */
public class ClientExample {
    /**
     * Run netcat in server mode to see the result.
     *
     * while true ; do nc -l -p 4444 -e 'echo "HELLO! $(date)"' ; done
     *
     * To kill
     *
     * lsof -n -i4TCP:4444 | grep LISTEN
     * Then kill by pid.
     *
     * @param args args.
     * @throws IOException exception.
     */
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 4444);
        InputStream in = socket.getInputStream();

        int data = in.read();

        while (data != -1) {
            System.out.print((char) data);
            data = in.read();
        }

        in.close();
        socket.close();
    }
}
