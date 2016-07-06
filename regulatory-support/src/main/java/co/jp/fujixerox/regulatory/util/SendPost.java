package co.jp.fujixerox.regulatory.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendPost {
	
	
    /**
     * 発送リクエスト
     * 
     * @param url
     *            請求アドレス
     * @param filePath
     *            ファイルパス
     * @return ResponseCode
     * @throws IOException
     */
    public int send(String url, String filePath, String convertType) throws IOException {
 
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            return -1;
        }
 
        URL urlObj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
 
        // フォームコミット：Post
        con.setRequestMethod("POST");
        con.setDoInput(true);
        con.setDoOutput(true);
        // キャッシュを使いません
        con.setUseCaches(false);
        
 
        // 請求ヘッダ
        con.setRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("Charset", "UTF-8");
 
        String BOUNDARY = "----------" + System.currentTimeMillis();
        con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
 
        // 本文の情報
 
        StringBuilder sb = new StringBuilder();
        //1.文字形式のPOST
        sb.append("--" + BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data; name=\"changefile\"" + "\r\n");
        sb.append("\r\n");
        sb.append(convertType + "\r\n");
        
        // ファイルのPOST
        sb.append("--" + BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data;name=\"file\";filename=\""
                + file.getName() + "\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");
 
        byte[] head = sb.toString().getBytes("utf-8");
 
        // 出力流を取得する
        OutputStream out = new DataOutputStream(con.getOutputStream());
        out.write(head);
        
        // ファイルの本文部分
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = in.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
        in.close();
 
        // フット
        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");
 
        out.write(foot);
 
        out.flush();
        out.close();
        
        // サーバー応答
        return con.getResponseCode();
    }
}
