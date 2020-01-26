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
import android.widget.TextView;
import android.widget.ImageView;
import android.graphics.BitmapFactory;
import com.example.check.R;
import java.net.MalformedURLException;
import android.view.View;




public class ShopHttpResponse extends AsyncTask<URL, Void, ShopDetail>{

    private View mainActivity;

    public ShopHttpResponse(View view){
        this.mainActivity = view;

    }


    /* バックグランドで行う処理 */
    @Override
    protected ShopDetail doInBackground(URL... urls) {
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
            JSONObject res = jsonObject.getJSONArray("rest").getJSONObject(0);
            JSONObject res_qupon = res.getJSONObject("flags");
            JSONObject res_access = res.getJSONObject("access");
            JSONObject resim = res.getJSONObject("image_url");

            ShopDetail info = new ShopDetail();
            // 店舗名
            info.setName(res.getString("name"));
            // 電話番号
            info.setTel(res.getString("tel"));
            // 住所
            info.setAddress(res.getString("address"));
            //　営業時間
            info.setOpen(res.getString("opentime"));
            //　休日
            info.setHoliday(res.getString("holiday"));
            // ランチタイム平均予算
            info.setBudget(res.getString("budget"));
            // 駅名
            info.setStation(res_access.getString("station"));
            // 徒歩（分）
            info.setWalk(res_access.getString("walk"));
            // クレジットカード名称
            info.setCcard(res.getString("credit_card"));
            // 電子マネー名称
            info.setEmonay(res.getString("e_money"));
            // クーポン有り無し
            info.setMqupon(res_qupon.getInt("mobile_coupon"));
            info.setPqupon(res_qupon.getInt("pc_coupon"));


            try {
                Bitmap image;
                URL image_url = new URL(resim.getString("shop_image1"));
                InputStream imageIs;
                imageIs = image_url.openStream();
                image = BitmapFactory.decodeStream(imageIs);
                info.setImage(image);
            }catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }

            return info;

        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


    /* バックグランド処理後、UIへ反映する処理 */
    protected void onPostExecute(ShopDetail info) {

        final ImageView imageView = mainActivity.findViewById(R.id.image);
        imageView.setImageBitmap(info.getImage());

        final TextView name = mainActivity.findViewById(R.id.name);
        name.setText(info.getName());
        final TextView tel = mainActivity.findViewById(R.id.tel);
        tel.setText("TEL:"+info.getTel());
        final TextView address = mainActivity.findViewById(R.id.address);
        address.setText(info.getAddress());

        //　営業時間
        final TextView open = mainActivity.findViewById(R.id.open);
        open.setText("営業時間："+info.getOpen());
        //　休日
        final TextView holiday = mainActivity.findViewById(R.id.holiday);
        holiday.setText("定休日：:"+info.getHoliday());

        // ランチタイム平均予算
        final TextView budget = mainActivity.findViewById(R.id.budget);
        budget.setText("平均予算："+info.getBudget());

        // 駅名から何分
        final TextView station = mainActivity.findViewById(R.id.walk);
        station.setText(info.getStation()+"から徒歩"+info.getWalk()+"分");


        // 電子マネー名称
        final TextView credit_card = mainActivity.findViewById(R.id.ccard);
        credit_card.setText(info.getCcard());
        final TextView e_monay = mainActivity.findViewById(R.id.ecard);
        e_monay.setText(info.getEmonay());

        // クーポン有
        if(info.getMqupon() == 1 || info.getPqupon() == 1) {
            final TextView qupon = mainActivity.findViewById(R.id.qupon);
            qupon.setText("クーポン有り");
        }

    }

}
