package com.example.workswithserver.asyncTask;

import android.os.AsyncTask;
import android.widget.TextView;

import com.example.workswithserver.data.MusicSaveData;
import com.example.workswithserver.data.MyAppDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

public class MyAsyncTask extends AsyncTask<String, String, String> {
    private static MyAppDatabase myAppDatabase;
    private TextView textView;

    public MyAsyncTask(MyAppDatabase myAppDatabase, TextView textView) {
        this.myAppDatabase = myAppDatabase;
        this.textView = textView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected String doInBackground(String... string) {
        try {
            HttpURLConnection conn = createURLWithPOST();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));

            String line = in.readLine();
            if (line != null) {
                publishProgress(line);
                workWithDB(line);
            }

        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    private HttpURLConnection createURLWithPOST() throws Exception {
        URL url = new URL("http://media.ifmo.ru/api_get_current_song.php");

        JSONObject postDataParams = new JSONObject();
        postDataParams.put("login", "4707login");
        postDataParams.put("password", "4707pass");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write(getPostDataString(postDataParams));

        writer.flush();
        writer.close();
        os.close();

        return conn;
    }

    @Override
    protected void onProgressUpdate(String... strings) {
        super.onProgressUpdate(strings);
        textView.setText(strings[0]);
    }

    public void workWithDB(String line) throws JSONException {
        String nameTrack = (String) new JSONObject(line).get("info");
        String[] words = nameTrack.split("-");

        MusicSaveData musicSaveDataLast = myAppDatabase.daoMusicSaveData().getLast();

        if (musicSaveDataLast == null ||
                !musicSaveDataLast.getNameTrack().equals(words[1])) {
            MusicSaveData musicSaveData = new MusicSaveData();
            musicSaveData.setMusician(words[0]);
            musicSaveData.setNameTrack(words[1]);
            musicSaveData.setCurrentTime("2019-19-10");
            myAppDatabase.daoMusicSaveData().insert(musicSaveData);
        }
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while (itr.hasNext()) {

            String key = itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }
}
