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
import java.util.ArrayList;
import java.util.List;

/**
 * 读取配置文件
 *
 * @author nico
 */
public class TabEngine
{

    //保存本地列表
    public static List<SubChannel> subChannelList = new ArrayList<>();

    //保存网络配置
    public static List<SubChannel> subChannelLoadList = new ArrayList<>();

    /**
     * 读取下载的文件
     * @param path
     * @throws FileNotFoundException
     */
    public static void init(String path) throws FileNotFoundException
    {
        File file = new File(path);
        InputStream inputStream = new FileInputStream(file);
        handleData(inputStream,subChannelLoadList);
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
        handleData(in,subChannelList);
    }

    public static void handleData(InputStream in,List<SubChannel> subChannelList)
    {
        subChannelList.clear();
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
            JSONObject jsonObject = new JSONObject(jsonStr);// JSON.parseObject(jsonStr);
            JSONArray startTab = jsonObject.getJSONArray("start_tab");
            for (int i = 0; i < startTab.length(); i++)
            {
                SubChannel subChannel = new SubChannel();
                JSONObject tabJson = startTab.getJSONObject(i);
                subChannel.setIcon(isNullData("icon", tabJson));
                subChannel.setName(isNullData("name", tabJson));
                subChannel.setSelectIcon(isNullData("selected_icon", tabJson));

                JSONArray topJsonList = tabJson.getJSONArray("children");
                List<Channel> channels = new ArrayList<>();
                for (int j = 0; j < topJsonList.length(); j++)
                {
                    Channel channel = new Channel();
                    JSONObject topJson = topJsonList.getJSONObject(j);
                    channel.setName(isNullData("name", topJson));
                    channel.setContent(isNullData("content", topJson));
                    channels.add(channel);
                }

                subChannel.setChannels(channels);

                System.out.println("channel" + subChannel.getIcon());
                System.out.println("channel" + subChannel.getName());
                System.out.println("channel" + subChannel.getChannels().get(0).getName());

                subChannelList.add(subChannel);
                in.close();
            }

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
