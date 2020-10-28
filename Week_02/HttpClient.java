import okhttp3.*;

import java.io.IOException;

class HttpClient {
    public static void main(String[] args) throws IOException{
        String url = "http://localhost:8801";
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        final Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        System.out.println("body: " + response.body().string());
    }
}