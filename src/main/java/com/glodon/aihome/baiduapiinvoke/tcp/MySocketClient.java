package com.glodon.aihome.baiduapiinvoke.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class MySocketClient {
    private Socket soc = null;
    private String server = "";
    private int port = 0;

    public MySocketClient(String server, int port) {
        super();
        this.server = server;
        this.port = port;
        try {
            soc = new Socket(server, port);
            PrintWriter writer = new PrintWriter(soc.getOutputStream());
            String message = "cmd=1&uid=0d5d6ac76046086701657e76eb15c579&topic=FitDitePicture\r\n";
            writer.print(message);
            writer.flush();/*不关闭流，清空输入缓存区*/
            System.out.println("信息已发送，正在接收");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        MySocketClient client = new MySocketClient("img.bemfa.com", 8347);
        client.recMsgFromServer();
    }

    //从服务器接收消息
    public void recMsgFromServer() throws IOException {
        byte[] b = new byte[1024];
        InputStream in = soc.getInputStream();
        while (true) {
            try {
                int len = 0;
                while ((len = in.read(b)) != -1) {
                    String strText = new String(b, 0, len);
                    System.out.print(strText);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}