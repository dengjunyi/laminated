package cn.tjy.laminated.util;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;


public class sounds {


/**
 * Project Name:TextToVoice
 * File Name:JacobStreamTask.java
 * Package Name:com.xx.util
 * Date:2018年12月7日上午9:54:46
 * Copyright (c) 2018, Gao All Rights Reserved.
 *
 */

//生成音频文件

        public static void main(String[] args) {
            trans("book信息错误!");
        }



        public static void trans(String text){
            ActiveXComponent activeXComponent = null;
            try {
//												jacob封装好的可供选择的API
                activeXComponent = new ActiveXComponent("Sapi.SpVoice");
//			运行时输出语音内容
                Dispatch dispatch = activeXComponent.getObject();
//			设置音量
                activeXComponent.setProperty("Volume", new Variant(100));
//			语音的朗读速度-10到 +10
                activeXComponent.setProperty("Rate", new Variant(-2));
//			调用执行朗读
                Dispatch.call(dispatch, "Speak",new Variant(text));

//			生成语音文件
                activeXComponent = new ActiveXComponent("Sapi.SpFileStream");
                Dispatch fileStreamDispatch = activeXComponent.getObject();

//			音频
                activeXComponent = new ActiveXComponent("Sapi.SpAudioFormat");
                Dispatch audioDispatch = activeXComponent.getObject();

//			设置文件流格式
                Dispatch.putRef(fileStreamDispatch, "Format", audioDispatch);
//			设置音频流格式
                Dispatch.put(audioDispatch, "Type", new Variant(22));

//			调用输出文件流打开方法，创建一个.wav .mp3 .mp4   .wma文件
                Dispatch.call(fileStreamDispatch, "Open", new Variant("src/main/resources/static/mp3/error.mp3"),new Variant(3),new Variant(true));
//			设置声音对象的音频流输出流为输出文件对象
                Dispatch.putRef(dispatch, "AudioOutputStream", fileStreamDispatch);

//			设置音量0-100
                Dispatch.put(dispatch, "Volume", new Variant(100));
//			设置朗读速度
                Dispatch.put(dispatch, "Rate", new Variant(-2));
//			开始朗读
                Dispatch.call(dispatch, "Speak",new Variant(text));

//			关闭输出文件流
                Dispatch.call(fileStreamDispatch, "Close");
                Dispatch.putRef(dispatch, "AudioOutputStream", null);

                audioDispatch.safeRelease();
                fileStreamDispatch.safeRelease();
                dispatch.safeRelease();


                activeXComponent.safeRelease();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }



    /*public static void main(String[] args) {
        ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice");
        Dispatch sapo = sap.getObject();
        try {
            // 音量 0-100
            sap.setProperty("Volume", new Variant(100));
            // 语音朗读速度 -10 到 +10
            sap.setProperty("Rate", new Variant(0));
            // 执行朗读
            Dispatch.call(sapo, "Speak", new Variant("您好，您有新的用车申请"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sapo.safeRelease();
            sap.safeRelease();
        }
    }*/




}
