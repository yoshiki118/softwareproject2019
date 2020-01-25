package com.example.check;


import android.graphics.Bitmap;
import android.os.AsyncTask;
import org.json.JSONObject;
import java.net.URL;
import android.app.Activity;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.List;
import android.widget.ListView;

import android.graphics.BitmapFactory;
import android.widget.TextView;

import com.example.check.R;

import java.net.MalformedURLException;


public class HttpResponse extends AsyncTask<URL, Void, List<ShopList>>{

    private Activity mainActivity;
    private ShopAdapter adapter;
    ListView list;
    List<ShopList> shopList;



    public HttpResponse(Activity activity, ShopAdapter adapter, ListView list, List<ShopList> shopList){
        this.mainActivity = activity;
        this.adapter = adapter;
        this.list = list;
        this.shopList = shopList;
    }


    /* バックグランドで行う処理 */
    @Override
    protected List<ShopList> doInBackground(URL... urls) {
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


            // 検索結果店　を　Listで格納していく
            for(int i = 0; i < jsonObject.getInt("hit_per_page"); i++){
                JSONObject res = jsonObject.getJSONArray("rest").getJSONObject(i);
                JSONObject respr = res.getJSONObject("pr");
                JSONObject resim = res.getJSONObject("image_url");

                ShopList shops = new ShopList();

                shops.setId(res.getString("id"));
                shops.setName(res.getString("name"));
                shops.setOpentime(res.getString("opentime"));
                shops.setPrshort(respr.getString("pr_short"));

                try {
                    Bitmap image;
                    URL image_url = new URL(resim.getString("shop_image1"));
                    InputStream imageIs;
                    imageIs = image_url.openStream();
                    image = BitmapFactory.decodeStream(imageIs);
                    shops.setImage(image);
                }catch (MalformedURLException e) {
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
                shopList.add(shops);
            }

            return shopList;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


    /* バックグランド処理後、UIへ反映する処理 */
    protected void onPostExecute(List<ShopList> resultList) {
        if(resultList == null){
            TextView not_found = mainActivity.findViewById(R.id.notfound);
            not_found.setText("該当する店舗はありません");
            return;
        }
        adapter.setShopList(resultList);
        list.setAdapter(adapter);
    }

}
