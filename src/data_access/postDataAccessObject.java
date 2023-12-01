package data_access;

import entities.postEntity;
import okhttp3.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class postDataAccessObject implements postDAO{
    private String API_TOKEN;
    private String OUT_PUT_DIRECTORY_NAME = "/Output";
    private String LATEST_POST_NUM = "/LATEST_NUM.txt";
    private String APPENDING_FILE = "/APPENDING_FILE.txt";
    private String POST_DIRECTORY = "/POST";
    public postDataAccessObject(String api) throws IOException {
        File outputDirectory = new File(OUT_PUT_DIRECTORY_NAME);
        outputDirectory.getParentFile().mkdir();
        this.API_TOKEN = api;
        if (!checkFileExist(APPENDING_FILE)){
            createTXT(APPENDING_FILE, "false");
            uploadFile( APPENDING_FILE,APPENDING_FILE);
        }
        if(! checkFileExist(LATEST_POST_NUM)){
            createTXT(LATEST_POST_NUM, "0");
            uploadFile(LATEST_POST_NUM, LATEST_POST_NUM);
        }
        if(! checkFileExist(POST_DIRECTORY)){
            createFolder(POST_DIRECTORY);
        }

    }

    @Override
    public postEntity getMostRecentPost() {

    }

    @Override
    public void savePost(postEntity post) {

    }

    @Override
    public void cleanAllPost() {

    }

    @Override
    public postEntity getMostPopularPost() {
        return null;
    }

    @Override
    public postEntity getPost(Integer postid) {
        return null;
    }

    @Override
    public List<postEntity> getLatestPosts() {
        return null;
    }

    @Override
    public postEntity getlatestPost(List<postEntity> posts) {
        return null;
    }
    private boolean checkFileExist(String fileName) throws IOException {
        OkHttpClient client = new OkHttpClient();
        try {
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),
                    "{\"path\": \"" + fileName + "\", \"include_media_info\": false}");

            Request request = new Request.Builder()
                    .url("https://api.dropboxapi.com/2/files/get_metadata")
                    .addHeader("Authorization", "Bearer " + API_TOKEN)
                    .addHeader("Content-Type", "application/json")
                    .post(requestBody)
                    .build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                if (responseBody.contains("metadata")) {
                    return true;
                } else {
                    return false;
                }
            } else {
                System.out.println("Request failed: " + response.code() + " - " + response.message());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return false;
    }
    private File createTXT(String filepath, String content){
        try {
            File file = new File(filepath);

            // Create directories if they don't exist

            // Create the file if it doesn't exist
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getAbsolutePath());
            } else {
                System.out.println("File already exists.");
            }

            // Write content to the file (optional)
            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.close();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    private boolean uploadFile(String filepath, String remotepath){


        File fileToUpload = new File(filepath);

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("path", remotepath)
                .addFormDataPart("mode", "add")
                .addFormDataPart("autorename", "true")
                .addFormDataPart("mute", "false")
                .addFormDataPart("file", fileToUpload.getName(),
                        RequestBody.create(MediaType.parse("application/octet-stream"), fileToUpload))
                .build();

        Request request = new Request.Builder()
                .url("https://content.dropboxapi.com/2/files/upload")
                .addHeader("Authorization", "Bearer " + API_TOKEN)
                .addHeader("Content-Type", "application/octet-stream")
                .addHeader("Dropbox-API-Arg", "{\"path\": \"" + remotepath + "\",\"mode\": \"add\",\"autorename\": true,\"mute\": false}")
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    private boolean createFolder(String remotefoldername){

        OkHttpClient client = new OkHttpClient();

        // Create the JSON request body with the folder path
        String requestBodyJson = "{\"path\": \"" + remotefoldername + "\", \"autorename\": false}";

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestBodyJson);

        Request request = new Request.Builder()
                .url("https://api.dropboxapi.com/2/files/create_folder_v2")
                .addHeader("Authorization", "Bearer " + API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    private boolean downloadFile(String remotepath, String localpath){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://content.dropboxapi.com/2/files/download")
                .addHeader("Authorization", "Bearer " + API_TOKEN)
                .addHeader("Dropbox-API-Arg", "{\"path\": \"" + remotepath + "\"}")
                .get()
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                // Save the downloaded file locally
                FileOutputStream outputStream = new FileOutputStream(localpath, false);
                outputStream.write(response.body().bytes());
                outputStream.close();
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
