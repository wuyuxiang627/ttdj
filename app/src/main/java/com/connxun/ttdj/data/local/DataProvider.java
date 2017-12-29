package com.connxun.ttdj.data.local;


import com.connxun.ttdj.entity.Person;
import com.connxun.ttdj.entity.Picture;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Mr.Jude on 2016/1/6.
 */
public class DataProvider {
    public static List<Person> getPersonList(int page) {
        ArrayList<Person> arr = new ArrayList<>();
        if (page >= 4) return arr;

        arr.add(new Person("http://i2.hdslb.com/52_52/user/61175/6117592/myface.jpg", "月の星く雪" + "————————第" + page + "页", "完结来补"));
        arr.add(new Person("http://i1.hdslb.com/52_52/user/6738/673856/myface.jpg", "影·蓝玉", "一看评论被***了一脸，伐开心。"));
        arr.add(new Person("http://i1.hdslb.com/account/face/1467772/e1afaf4a/myface.png", "i琳夏i", "(｀・ω・´)"));
        arr.add(new Person("http://i0.hdslb.com/52_52/user/18494/1849483/myface.jpg", "Minerva。", "为啥下载不能了？π_π"));
        arr.add(new Person("http://i0.hdslb.com/52_52/account/face/4613528/303f4f5a/myface.png", "如歌行极", "求生肉（/TДT)/"));
        arr.add(new Person("http://i0.hdslb.com/52_52/account/face/611203/76c02248/myface.png", "GERM", "第一次看 看弹幕那些说什么影帝模式啥的 感觉日了狗了 让我怎么往后看啊 艹"));
        arr.add(new Person("http://i2.hdslb.com/52_52/user/46230/4623018/myface.jpg", "じ★ve↘魅惑", "开头吾王裙子被撩起来怎么回事！→_→"));
        arr.add(new Person("http://i2.hdslb.com/52_52/user/66723/6672394/myface.jpg", "道尘一梦", "@伪 · 卫宫士郎"));
        arr.add(new Person("http://i1.hdslb.com/user/3039/303946/myface.jpg", "潘多哥斯拉", "朋友，听说过某R吗……..我呸，听说过虫群吗？(｀・ω・´)"));
        arr.add(new Person("http://i2.hdslb.com/account/face/9034989/aabbc52a/myface.png", "一只红发的猫", "道理我都懂，我就问，几楼开车←_←"));
        arr.add(new Person("http://i0.hdslb.com/account/face/1557783/8733bd7b/myface.png", "Mikuの草莓胖次", "扶..扶我起来,喝了最后这一瓶营养快线，让我撸死up"));
        arr.add(new Person("http://i2.hdslb.com/user/3716/371679/myface.jpg", "Absolute Field", "朋也，看过里番吗？"));
        arr.add(new Person("http://i1.hdslb.com/account/face/9045165/4b11d894/myface.png", "琪雅之约", "摩西摩西.警察局么？"));
        return arr;
    }



    static final Picture[] VIRTUAL_PICTURE = {
            new Picture(566, 800, "http://o84n5syhk.bkt.clouddn.com/57154327_p0.png"),
            new Picture(2126, 1181, "http://o84n5syhk.bkt.clouddn.com/57180221_p0.jpg"),
            new Picture(1142, 800, "http://o84n5syhk.bkt.clouddn.com/57174070_p0.jpg"),
            new Picture(550, 778, "http://o84n5syhk.bkt.clouddn.com/57166531_p0.jpg"),
            new Picture(1085, 755, "http://o84n5syhk.bkt.clouddn.com/57151022_p0.jpg"),
            new Picture(656, 550, "http://o84n5syhk.bkt.clouddn.com/57172236_p0.jpg"),
            new Picture(1920, 938, "http://o84n5syhk.bkt.clouddn.com/57174564_p0.jpg"),
            new Picture(1024, 683, "http://o84n5syhk.bkt.clouddn.com/57156832_p0.jpg"),
            new Picture(723, 1000, "http://o84n5syhk.bkt.clouddn.com/57151474_p0.png"),
            new Picture(2000, 1667, "http://o84n5syhk.bkt.clouddn.com/57156623_p0.png"),
    };

    public static ArrayList<Picture> getPictures(int page) {
        ArrayList<Picture> arrayList = new ArrayList<>();
        for (int i = 0; i < VIRTUAL_PICTURE.length; i++) {
            arrayList.add(VIRTUAL_PICTURE[i]);
        }
        return arrayList;
    }

    static final int[] NarrowImage = {
//            R.drawable.yy01,
//            R.drawable.yy02,
//            R.drawable.yy03,
//            R.drawable.yy04,
//            R.drawable.yy05,
//            R.drawable.yy06,
//            R.drawable.yy07,
//            R.drawable.yy08,
//            R.drawable.yy09,
    };

//    public static List<VideoClass> getVideoClass() {
//        List<VideoClass> arrayList = new ArrayList<>();
//        arrayList.add(new VideoClass("微信小程序快速开发【喜科堂互联教育】", "本课程手把手从基础讲解最新开始流行的微信小程序。把握学习机会，可以让您快速提升自身价值！", "https://10.url.cn/qqcourse_logo_ng/ajNVdqHZLLDDu1evPwRIEYJsMQDn7ib9FAMtreHhvhUQtgmNWTfbTLF4IaXITYG2Qsibw4bWrf7L4/220?tp=webp"));
//        arrayList.add(new VideoClass("IOS应用开发就业课【职坐标】（面向岗位/月薪上万）", "职坐标教学团队中从事10年以上IOS开发的专业老师，通过调研分析各大优质企业的就业趋势，精心研发的面向岗位招聘的全套精讲视频。课程内容涉及IOS开发环境、基础组件、UI高级组件及布局、数据存储、网络编程、多媒体、高级动画、CocoaPods、SVN/Git，并结合对对碰游戏案例，学员学成后，基本胜任IOS开发工程师初级岗位。", "https://10.url.cn/qqcourse_logo_ng/ajNVdqHZLLCt13ibnicDh9o10CdxddBhR1qibqT4eD1PqzFtYsByvtBKib9BiaIyQA7eZnBBQFehXLOE/220?tp=webp"));
//        arrayList.add(new VideoClass("尚学堂大欢教你5天打造直播app", "本iOS_UI视频教程由刘雨佳欢主讲，在网红经济和直播平台的风口之下，这两年直播平台发展迅猛。从早期的YY，到后来的虎牙、斗鱼，再到现在的花椒、映客、NOW直播，直播平台在资本推动下迎来了全面爆发。", "https://10.url.cn/qqcourse_logo_ng/ajNVdqHZLLDB1YcuibJOQQpfCtBA2L3xXnLlWIVY24kcJs4PlVXKvyFpDsILyvgpg9KmjcZKPayw/220?tp=webp"));
//
//
//        arrayList.add(new VideoClass("Android Studio高级开发【职坐标】（Android开发进阶Mini课程）", " Android Studio 是一个Android开发环境，基于IntelliJ IDEA. 类似 Eclipse ADT，Android Studio 提供了集成的 Android 开发工具用于开发和调试。基于Android开发，实现众多热门游戏及软件的开发与应用。", "https://10.url.cn/qqcourse_logo_ng/ajNVdqHZLLB9mMZCVVq9X8hPbQLSkcVkxfoVkYkib8hQciblt1ow8LqrG1fqeIRz0Bvib4PIIffrQ0/220?tp=webp"));
//        arrayList.add(new VideoClass("IOS应用开发就业课【职坐标】（面向岗位/月薪上万）",
//                "职坐标教学团队中从事10年以上IOS开发的专业老师，通过调研分析各大优质企业的就业趋势，精心研发的面向岗位招聘的全套精讲视频。课程内容涉及IOS开发环境、基础组件、UI高级组件及布局、数据存储、网络编程、多媒体、高级动画、CocoaPods、SVN/Git，并结合对对碰游戏案例，学员学成后，基本胜任IOS开发工程师初级岗位。",
//                "https://10.url.cn/qqcourse_logo_ng/ajNVdqHZLLCt13ibnicDh9o10CdxddBhR1qibqT4eD1PqzFtYsByvtBKib9BiaIyQA7eZnBBQFehXLOE/220?tp=webp"));
//
//
//        arrayList.add(new VideoClass("Android高级工程师-公开课【喜科堂互联教育】",
//                " 本课程，从零基础给学员讲解Andorid开发的基础知识，并贯穿相关实战内容，让学员在短时间内容快速掌握安卓开发的核心知识。",
//                "https://10.url.cn/qqcourse_logo_ng/ajNVdqHZLLCCy1zaWgMKvch0kzTISyfJWjmew3NEx3DC1Nia1HTW2cjGeHVavMrtFqqiaQ22zhQIg/220?tp=webp"));
//        arrayList.add(new VideoClass("3天学会Kotlin",
//                "小波说雨燕系列视频作者，专注Swift、iOS、Android等移动开发领域技术",
//                "https://10.url.cn/qqcourse_logo_ng/ajNVdqHZLLAH2NjdwKfgMTIq6G73t5Y1pHKw4Leg295HQ4UuiaKtF1TJREn6V9p7Yn8KSbBupoT4/220?tp=webp"));
//        arrayList.add(new VideoClass("跟老张一起学Android--安卓基础篇--开发环境搭建［至远科技］",
//                "跟老张一起学Android，是一个以零基础为起点，以生动幽默的方式，带将大家进入Android的世界。",
//                "https://10.url.cn/qqcourse_logo_ng/ajNVdqHZLLCNbaV98KicgROyr9vU7WqULJUvbqHVicDFgS3Cf2bCmU6FJWl2VdJrc3LPMUYVViaFdk/220?tp=webp"));
//        arrayList.add(new VideoClass("Java微信二次开发从基础到项目实战",
//                " Java微信二次开发从基础到项目特训班旨在为那些真正想跟上互联网+、移动互联网时代发展的机遇，能够解决微信项目开发过程中的实际问题，重点讲解微信公众平台的整个开发思路、流程、项目实战案例。",
//                "https://10.url.cn/qqcourse_logo_ng/ajNVdqHZLLBQRzMdPIK4IlabFCruOibEJ4uic3QU302B5IDrmC04mUZvGpU29AWRGXyyVaficzGKlg/220?tp=webp"));
//        arrayList.add(new VideoClass("[Android] kotlin从零基础到进阶",
//                " 每个Android程序员必备的Kotlin课程, 每个Java程序员都需要了解的Kotlin\n" +
//                        "Kotlin现在是Google官方认定Android一级开发语言\n" +
//                        "与Java100%互通，并具备诸多Java尚不支持的新特性\n" +
//                        "Spring5 已经原生支持kotlin\n" +
//                        "最好用的构建工具gradle也开始原生支持kotlin",
//                "https://10.url.cn/qqcourse_logo_ng/ajNVdqHZLLDVZjXzqLGpxfEb1Cqaqv5ZfZEWzTPHIyjOUB26JwzL7uFrXBeO8mpIkmdocXCMQ4s/220?tp=webp"));
//
//
//        return arrayList;
//    }
//
//    public static List<VideoClass> getVideoClassItems() {
//        List<VideoClass> arrayList = new ArrayList<>();
//
//        arrayList.add(new VideoClass("JDK下载、安装和配置",
//                "",
//                ""));
//        arrayList.add(new VideoClass("AndroidStudio下载和安装",
//                "",
//                ""));
//        arrayList.add(new VideoClass(
//                "Android虚拟机介绍",
//                "",
//                ""));
//        arrayList.add(new VideoClass(
//                "开发第一个Android程序",
//                "",
//                ""));
//        return arrayList;
//    }

    public static ArrayList<Integer> getNarrowImage(int page) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (page == 4) return arrayList;

        for (int i = 0; i < NarrowImage.length; i++) {
            arrayList.add(NarrowImage[i]);
        }
        return arrayList;
    }
}
