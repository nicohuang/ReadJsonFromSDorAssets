package com.hwz.readjsonfromsdorassets;

import android.content.Context;

import org.apache.http.util.EncodingUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 读取配置文件
 *
 * @author nico
 */
public class TabEngine
{



    /**
     * 读取下载的文件
     *
     * @param path
     * @throws FileNotFoundException
     */
    public static void init(String path) throws FileNotFoundException
    {
        File file = new File(path);
        InputStream inputStream = new FileInputStream(file);
        handleData(inputStream);
    }

    /**
     * 读取资源default_channel_data.json文件
     *
     * @param context
     */

    public static void init(Context context)
    {
        InputStream in = null;
        try
        {
            in = context.getApplicationContext().getAssets().open("default_channel_data.json");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        handleData(in);
    }

    public static void handleData(InputStream in)
    {
        try
        {
            //获取文件的字节数
            int length = 0;

            length = in.available();

            //创建byte数组
            byte[] buffer = new byte[length];
            //将文件中的数据都到byte数组中
            in.read(buffer);
            String jsonStr = EncodingUtils.getString(buffer, "utf-8");
            JSONObject jsonObject = new JSONObject(jsonStr);
            JSONArray startTab = jsonObject.getJSONArray("start_tab");

            in.close();


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static String isNullData(String key, JSONObject json) throws JSONException
    {
        if (json.get(key) != null)
        {
            return json.get(key).toString();
        }
        else
        {
            return "";
        }
    }

}
