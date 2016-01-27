package com.hwz.readjsonfromsdorassets;


import java.io.Serializable;
import java.util.List;

/**
 * 选项卡
 *
 * @author nico
 */
public class SubChannel implements Serializable
{
    //图标
    private String icon;

    //选中图标
    private String selectIcon;

    //名称
    private String name;

    private List<Channel> channels;

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<Channel> getChannels()
    {
        return channels;
    }

    public void setChannels(List<Channel> channels)
    {
        this.channels = channels;
    }

    public String getSelectIcon()
    {
        return selectIcon;
    }

    public void setSelectIcon(String selectIcon)
    {
        this.selectIcon = selectIcon;
    }

}
