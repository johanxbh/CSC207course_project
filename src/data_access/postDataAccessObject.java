package data_access;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.postEntity;
import okhttp3.*;

import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class postDataAccessObject implements postDAO {
    private String API_TOKEN;
    private String OUT_PUT_DIRECTORY_NAME = "/Output";
    private String LATEST_POST_NUM = "/LATEST_NUM.txt";
    private String APPENDING_FILE = "/APPENDING_FILE.txt";
    private String POST_DIRECTORY = "/POST";
    private String DOWNLOAD_DIRECTORY_NAME = "/Download";
    private String UPLOAD_DIRECTORY_NAME = "/Upload";

    public postDataAccessObject(String api) throws IOException {
        File outputDirectory = new File(OUT_PUT_DIRECTORY_NAME);
        outputDirectory.getParentFile().mkdir();
        this.API_TOKEN = api;
        if (!checkFileExist(APPENDING_FILE)) {
            createTXT(APPENDING_FILE, "false");
            uploadFile(APPENDING_FILE, APPENDING_FILE);
        }
        if (!checkFileExist(LATEST_POST_NUM)) {
            createTXT(LATEST_POST_NUM, "0");
            uploadFile(LATEST_POST_NUM, LATEST_POST_NUM);
        }
        if (!checkFileExist(POST_DIRECTORY)) {
            createFolder(POST_DIRECTORY);
        }

    }

    @Override
    public postEntity getMostRecentPost() throws IOException {
        downloadFile(LATEST_POST_NUM, LATEST_POST_NUM);
        String newestNum = readContent(OUT_PUT_DIRECTORY_NAME + LATEST_POST_NUM);
        downloadFile(newestNum, OUT_PUT_DIRECTORY_NAME + LATEST_POST_NUM);
        String latest = readContent(OUT_PUT_DIRECTORY_NAME + LATEST_POST_NUM);
        downloadFile(latest + ".json", OUT_PUT_DIRECTORY_NAME + DOWNLOAD_DIRECTORY_NAME + "/" + latest + ".json");
        return createPostEntityFromJson(OUT_PUT_DIRECTORY_NAME + DOWNLOAD_DIRECTORY_NAME + "/" + latest + ".json");
    }

    @Override
    public void savePost(postEntity post) throws IOException {
        String appendingFilePath = OUT_PUT_DIRECTORY_NAME + "/" + APPENDING_FILE;
        downloadFile(APPENDING_FILE,appendingFilePath);
        String content = readContent(appendingFilePath);
        while (content == "true"){
            try {
                Thread.sleep(1000); // 1000 milliseconds = 1 second
            } catch (InterruptedException e) {
                // Handle any potential interruptions
                e.printStackTrace();
            }
            deleteFile(appendingFilePath);
            downloadFile(APPENDING_FILE,appendingFilePath);
            content = readContent(appendingFilePath);
        }
        deleteFile(OUT_PUT_DIRECTORY_NAME + APPENDING_FILE);
        createTXT(OUT_PUT_DIRECTORY_NAME + APPENDING_FILE, "true");
        uploadFile(OUT_PUT_DIRECTORY_NAME + APPENDING_FILE, POST_DIRECTORY+APPENDING_FILE);

        deleteFile(OUT_PUT_DIRECTORY_NAME + LATEST_POST_NUM);
        downloadFile(POST_DIRECTORY + LATEST_POST_NUM, OUT_PUT_DIRECTORY_NAME + LATEST_POST_NUM);
        Integer newPostID = Integer.parseInt(readContent(OUT_PUT_DIRECTORY_NAME + LATEST_POST_NUM))+ 1;
        post.setPostID(newPostID);
        String picturePath = post.getPostPicture();
        if (checkLocalFileExist(picturePath)){
            String type = checkPictureType(picturePath);
            uploadFile(picturePath, POST_DIRECTORY + "/" + post.getId() +"." +type);
        }
        String postpath = convertToJson(post);
        uploadFile(postpath,POST_DIRECTORY + "/" + post.getId() + ".json");
        deleteFile(OUT_PUT_DIRECTORY_NAME + LATEST_POST_NUM);
        String newID = newPostID.toString();
        createTXT(OUT_PUT_DIRECTORY_NAME + LATEST_POST_NUM, newID);
        uploadFile(OUT_PUT_DIRECTORY_NAME + LATEST_POST_NUM,LATEST_POST_NUM);
        deleteFile(OUT_PUT_DIRECTORY_NAME + APPENDING_FILE);
        createTXT(OUT_PUT_DIRECTORY_NAME + APPENDING_FILE, "false");
        uploadFile(OUT_PUT_DIRECTORY_NAME + APPENDING_FILE, POST_DIRECTORY+APPENDING_FILE);
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
        String remoteDirectory = POST_DIRECTORY + "/" + postid.toString() + ".json";
        downloadFile(remoteDirectory, OUT_PUT_DIRECTORY_NAME + DOWNLOAD_DIRECTORY_NAME + postid.toString() + ".json");
        postEntity newPostEntity = createPostEntityFromJson(DOWNLOAD_DIRECTORY_NAME + postid.toString() + ".json");
        String remotePicturePath = POST_DIRECTORY + "/" + postid + "." + checkPictureType(newPostEntity.getPostPicture());
        downloadFile(remotePicturePath, OUT_PUT_DIRECTORY_NAME + DOWNLOAD_DIRECTORY_NAME + postid + "." + checkPictureType(newPostEntity.getPostPicture()));
        newPostEntity.setPothPicture(OUT_PUT_DIRECTORY_NAME + DOWNLOAD_DIRECTORY_NAME + postid + "." + checkPictureType(newPostEntity.getPostPicture()));
        return newPostEntity;
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

    private File createTXT(String filepath, String content) {
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

    private boolean uploadFile(String filepath, String remotepath) {


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

    private boolean createFolder(String remotefoldername) {

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

    private boolean downloadFile(String remotepath, String localpath) {
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
                FileOutputStream outputStream = new FileOutputStream(OUT_PUT_DIRECTORY_NAME + "/" + localpath, false);
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

    private String readContent(String filepath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String all = null;
        String line;
        while ((line = reader.readLine()) != null) {
            all = all + line; // Or perform operations with the read line
        }
        return all;
    }

    private postEntity createPostEntityFromJson(String filepath) {
        postEntity post;
        try (FileReader reader = new FileReader(filepath)) {
            // Convert JSON to postEntity object
            Gson gson = new Gson();
            post = gson.fromJson(reader, postEntity.class);
            return post;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    private String convertToJson(postEntity post) {
        Integer postId = post.getId();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(post);

        // Save JSON to a file
        String filePath = OUT_PUT_DIRECTORY_NAME + UPLOAD_DIRECTORY_NAME + "/" + postId + ".json"; // Replace with your desired file path
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(json);
            return OUT_PUT_DIRECTORY_NAME + UPLOAD_DIRECTORY_NAME + postId + ".json";
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    private void deleteFile(String filePath){
        File file = new File(filePath);
        if (file.exists()){
            file.delete();
        }
    }
    private boolean checkLocalFileExist(String filePath){
        File file = new File(filePath);
        if (file.exists()){
            return true;
        }
        return false;
    }
    private String checkPictureType(String picturepath){
        String input = "This is a string with .ABC and .DEFGH, and .IJKL";

        Pattern pattern = Pattern.compile("\\.([A-Za-z]+)"); // Pattern for dot followed by letters
        Matcher matcher = pattern.matcher(input);

        String lastXXX = ""; // Variable to store the last XXX text found

        while (matcher.find()) {
            String match = matcher.group(1);
            lastXXX = match;
        }

        if (!lastXXX.isEmpty()) {
            return null;
        } else {
            return lastXXX;
        }
    }

}