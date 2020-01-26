package com.example.check.ui.main;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import org.json.JSONObject;
import java.net.URL;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import android.widget.ListView;
import android.graphics.BitmapFactory;
import java.net.MalformedURLException;
import android.view.View;
import android.widget.TextView;
import com.example.check.R;
import java.util.List;
import java.util.ArrayList;





public class CoockHttpResponse extends AsyncTask<URL, Void, List<Bitmap>>{

    private View mainActivity;
    private ImageAdapter adapter;
    private ListView list;


    public CoockHttpResponse(View view, ImageAdapter adapter, ListView listView){
        this.mainActivity = view;
        this.adapter = adapter;
        this.list = listView;
    }


    /* バックグランドで行う処理 */
    @Override
    protected List<Bitmap> doInBackground(URL... urls) {
        /* URL */

        final URL url = urls[0];


        try {
            /* 接続用HttpURLConnectionオブジェクト作成 */
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            /* リクエストメソッドの設定 */
            con.setRequestMethod("GET");
            /* リダイレクトするか設定 */
            con.setInstanceFollowRedirects(false);
            /* データの読み取り設定 今回は読み取りのみ行う */
            con.setDoInput(true);
            con.setDoOutput(false);
            /* 接続 */
            con.connect();

            /* 接続後、データをJSONで取得する * */
            /* 準備 */
            final InputStream in = con.getInputStream();
            String encoding = con.getContentEncoding();

            if(null == encoding){
                encoding = "UTF-8";
            }

            final InputStreamReader inReader = new InputStreamReader(in, encoding);
            final BufferedReader bufReader = new BufferedReader(inReader);
            StringBuilder response = new StringBuilder();
            String line = null;

            /* 読み込み */
            while((line = bufReader.readLine()) != null) {
                response.append(line);
            }

            bufReader.close();
            inReader.close();
            in.close();

            /* パース */
            JSONObject jsonObject = new JSONObject(response.toString());

            List<Bitmap> image = new ArrayList<>();



            try {
                for (int i = 0; i < jsonObject.getJSONObject("response").getInt("hit_per_page"); i++) {
                    String j = String.valueOf(i);
                    URL image_url = new URL( jsonObject.getJSONObject("response").getJSONObject(j).getJSONObject("photo").getJSONObject("image_url").getString("url_320"));
                    InputStream imageIs;
                    imageIs = image_url.openStream();
                    image.add(BitmapFactory.decodeStream(imageIs));
                }

            }catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }

            return image;

        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


    /* バックグランド処理後、UIへ反映する処理 */
    protected void onPostExecute(List<Bitmap> image) {
        if(image == null){
            final TextView not_found = mainActivity.findViewById(R.id.notfound);
            not_found.setText("提供された画像はありません");
            return;
        }

        this.adapter.setImageList(image);
        list.setAdapter(adapter);
    }

}
