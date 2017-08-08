package com.mygdx.game.tools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by Administrator on 2016/11/21.
 */
public class SerializableLib {
    public static byte[] compress(Serializable serializable) throws IOException{
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        save(serializable ,byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
    /**
     * 保存数据
     * @param serializable
     * @param outputStream
     * @throws IOException
     */
    public static void save(Serializable serializable,OutputStream outputStream) throws IOException{
        ObjectOutputStream objectOutputStream = null;
        try{
            objectOutputStream = new ObjectOutputStream(
                    new GZIPOutputStream(
                            new BufferedOutputStream(outputStream)));
        }
        finally{
            if(objectOutputStream != null){
                objectOutputStream.close();
            }
            else{
                outputStream.close();
            }
        }
    }
    /**
     * 读取数据
     * @param inputStream 输入数据流
     * @return
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static Serializable load(InputStream inputStream) throws ClassNotFoundException,IOException{
        ObjectInputStream objectInputStream = null;
        try{
            objectInputStream
                    = new ObjectInputStream(
                    new GZIPInputStream(
                            new BufferedInputStream(inputStream)));
            return (Serializable) objectInputStream.readObject();
        }
        finally{
            if(objectInputStream != null){
                objectInputStream.close();
            }else{
                inputStream.close();
            }
        }
    }
}
