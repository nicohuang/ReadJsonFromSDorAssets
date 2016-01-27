package com.hwz.readjsonfromsdorassets;


import java.io.Serializable;

/**
 * 二级选项卡
 *
 * @author nico
 */
public class Channel implements Serializable
{
    //名称
    private String name;

    //url
    private String content;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

}
