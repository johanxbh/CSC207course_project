package data_access;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.postEntity;
import okhttp3.*;
import okio.BufferedSink;
import okio.Okio;

import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class postDataAccessObject implements postDAO {
    private String API_TOKEN;
    private String PARANT_DIRECTOYRY;
    private String OUT_PUT_DIRECTORY_NAME = "/Output";
    private String LATEST_POST_NUM = "LATEST_NUM.txt";
    private String APPENDING_FILE = "APPENDING_FILE.txt";
    private String POST_DIRECTORY = "/POST";
    private String DOWNLOAD_DIRECTORY_NAME = "Download";
    private String UPLOAD_DIRECTORY_NAME = "Upload";

    public postDataAccessObject(String api) throws IOException {
        File outputDirectory = new File(OUT_PUT_DIRECTORY_NAME);
        outputDirectory.getParentFile().mkdir();
        this.API_TOKEN = api;
        if (!checkFileExist("/"+APPENDING_FILE)) {
            createTXT(APPENDING_FILE, "false");
            File file = new File(APPENDING_FILE);
            String fileToUpload = file.getAbsolutePath();
            uploadFile(fileToUpload,  APPENDING_FILE);
        }
        if (!checkFileExist("/"+LATEST_POST_NUM)) {
            createTXT(LATEST_POST_NUM, "1");
            File file = new File(LATEST_POST_NUM);
            String fileToUpLoad = file.getAbsolutePath();
            uploadFile(fileToUpLoad, LATEST_POST_NUM);
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
        deleteFile(APPENDING_FILE);
        String appendingFilePath = APPENDING_FILE;
        downloadFile("/"+APPENDING_FILE,appendingFilePath);
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
        deleteFile(APPENDING_FILE);
        createTXT(APPENDING_FILE, "true");
        File file = new File(APPENDING_FILE);
        String abpFile = file.getAbsolutePath();
        uploadFile(abpFile, POST_DIRECTORY+ "/" + APPENDING_FILE);

        deleteFile(LATEST_POST_NUM);
        downloadFile("/"+LATEST_POST_NUM, LATEST_POST_NUM);
        System.out.println(readContent(LATEST_POST_NUM));
        Integer newPostID = Integer.parseInt(readContent(LATEST_POST_NUM))+ 1;
        post.setPostID(newPostID);
        String picturePath = post.getPostPicture();
        System.out.println(checkLocalFileExist(picturePath));
        if (checkLocalFileExist(picturePath)){
            String type = checkPictureType(picturePath);
            System.out.println(picturePath +"|"+ "POST" + "/" + post.getId().toString() +"." +type);
            uploadFile(picturePath, "POST" + "/" + post.getId().toString() +"." +type);
        }
        String postpath = convertToJson(post);
        File jsonFile = new File(postpath);
        String abpJosnFile = jsonFile.getAbsolutePath();
        System.out.println(abpJosnFile);
        System.out.println(POST_DIRECTORY + "/" + post.getId().toString() + ".json");
        uploadFile(abpJosnFile,"POST" + "/" + post.getId().toString() + ".json");
        System.out.println("json upload action over");

        deleteFile(LATEST_POST_NUM);
        String newID = newPostID.toString();
        createTXT(LATEST_POST_NUM, newID);
        deleteFileFromBox("/" + LATEST_POST_NUM);
        uploadFile(new File(LATEST_POST_NUM).getAbsolutePath(), LATEST_POST_NUM);

        deleteFile(APPENDING_FILE);
        createTXT(APPENDING_FILE, "false");
        uploadFile(new File(APPENDING_FILE).getAbsolutePath(), APPENDING_FILE);
    }

    @Override
    public void cleanAllPost() {

    }

    @Override
    public postEntity getMostPopularPost() {
        return null;
    }

    @Override
    public postEntity getPost(Integer postid) throws IOException {
        if (checkLocalFileExist(postid.toString()+".json ")){
            return createPostEntityFromJson(postid.toString()+".json");
        }
        String remoteDirectory = POST_DIRECTORY + "/" + postid.toString() + ".json";
        downloadFile(remoteDirectory, postid.toString() + ".json");
        postEntity newPostEntity = createPostEntityFromJson(postid.toString() + ".json");
        String remotePicturePath =  POST_DIRECTORY + "/" + postid.toString() + "." + checkPictureType(newPostEntity.getPostPicture());
        try {
            System.out.println("check if the file exist on the cloud"+checkFileExist("POST" + "/" + postid.toString() + "." + checkPictureType(newPostEntity.getPostPicture())));
            System.out.println("check the file path:"+POST_DIRECTORY + "/" + postid.toString() + "." + checkPictureType(newPostEntity.getPostPicture()));
            if (checkFileExist(POST_DIRECTORY + "/" + postid.toString() + "." + checkPictureType(newPostEntity.getPostPicture()))) {
                downloadFile(remotePicturePath, postid.toString() + "." + checkPictureType(newPostEntity.getPostPicture()));
                newPostEntity.setPostPicture(postid.toString() + "." + checkPictureType(newPostEntity.getPostPicture()));
            }
        } catch (Exception e){
            return newPostEntity;
        }
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

    public boolean checkFileExist(String fileName) throws IOException {
        OkHttpClient client = new OkHttpClient();
        try {
            String url = "https://api.dropboxapi.com/2/files/get_metadata";
            String jsonBody = "{\"path\": \"" + fileName + "\"}";

            RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonBody);
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("Authorization", "Bearer " + API_TOKEN)
                    .addHeader("Content-Type", "application/json")
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                // File exists
                return true;
            }
            // File does not exist or other error occurred
            return false;
        } catch (Exception e) {
            // Error occurred during request
            e.printStackTrace();
            return false;
        }
    }

    private void createTXT(String filepath, String content) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void uploadFile(String localFilePath, String remoteFileName) {
        OkHttpClient client = new OkHttpClient();

        File fileToUpload = new File(localFilePath);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), fileToUpload);

        Request request = new Request.Builder()
                .url("https://content.dropboxapi.com/2/files/upload")
                .addHeader("Authorization", "Bearer " + API_TOKEN)
                .addHeader("Dropbox-API-Arg", "{\"path\": \"/" + remoteFileName + "\", \"mode\": \"add\", \"autorename\": false, \"mute\": false}")
                .addHeader("Content-Type", "application/octet-stream")
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                System.out.println("File uploaded successfully to root directory!");
            } else {
                System.out.println("File upload failed: " + response.code() + " - " + response.message());
                System.out.println("Response Body: " + response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
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

    public boolean downloadFile(String remotepath, String localpath) {
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
                File downloadedFile = new File(localpath);
                BufferedSink sink = Okio.buffer(Okio.sink(downloadedFile));
                sink.writeAll(response.body().source());
                sink.close();
                System.out.println("File downloaded successfully to: " + localpath);
                return true;
            } else {
                System.out.println("File download failed: " + response.code() + " - " + response.message());
                System.out.println("Response Body: " + response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String readContent(String filepath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String all = "";
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
        String filePath = postId + ".json"; // Replace with your desired file path
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(json);
            return postId + ".json";
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
    public String checkPictureType(String picturepath){
        String input = "This is a string with .ABC and .DEFGH, and .IJKL";

        Pattern pattern = Pattern.compile("\\.(jpg|jpeg|png|gif|bmp)$", Pattern.CASE_INSENSITIVE); // Pattern for dot followed by letters
        Matcher matcher = pattern.matcher(picturepath);

        String lastXXX = ""; // Variable to store the last XXX text found

        while (matcher.find()) {
            String match = matcher.group(1);
            lastXXX = match;
        }

        if (!lastXXX.isEmpty()) {
            return lastXXX;
        } else {
            return null;
        }
    }
    private void deleteFileFromBox(String dropboxFilePath) {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"path\": \"" + dropboxFilePath + "\"}");

        Request request = new Request.Builder()
                .url("https://api.dropboxapi.com/2/files/delete_v2")
                .post(body)
                .addHeader("Authorization", "Bearer " + API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("File deletion failed: " + response.code() + " - " + response.message());
                System.out.println("Response Body: " + response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}