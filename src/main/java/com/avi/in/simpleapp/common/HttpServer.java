package com.avi.in.simpleapp.common;

import okhttp3.*;

import java.io.IOException;
import java.io.InputStream;

public class HttpServer {
    private static final String BASE_URL = "http://localhost:9011";
    OkHttpClient client = new OkHttpClient();
    public static MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }


    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public void uploadFilePost(String bucketName, String folderName, String fileName, byte[] fileContent) throws IOException {
        MultipartBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", fileName,
                        RequestBody.create(fileContent, MediaType.parse("application/octet-stream")))
                .addFormDataPart("folderName", folderName)
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL + "/api/minio/uploads" + "/" + bucketName)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            System.out.println("File uploaded: " + response.body().string());
        }
    }

    public InputStream getDocument(String bucketName, String folderName) throws IOException {
        // Build the form data
        RequestBody formBody = new FormBody.Builder()
                .add("bucketName", bucketName)
                .add("folderName", folderName)
                .build();

        // Build the request
        Request request = new Request.Builder()
                .url(BASE_URL + "/api/minio/getDocument")
                .post(formBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Handle the response
            System.out.println(response.body().string());
            return response.body().byteStream();
        }

    }
}


