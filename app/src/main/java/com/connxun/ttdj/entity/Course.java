package com.connxun.ttdj.entity;

/**
 * Created by wushange on 2017/9/21.
 */
public class Course {

    private int id;
    public int courseid;
    private String              coursename;
    private String              coursedesc;
    private String              coursepic;
    private int                 coursetype;
    private long                datetime;
    private int                 ishot;
    private int                 hotsort;
    private int                 state;
    private int                 statesort;
    private int                 isnew;
    private int                 newsort;
//    private MosCourseTypeBean   mosCourseType;
//    private MosDissertationBean mosDissertation;
    private int                 dissertationid;
    private int                 dissertationsort;
//
//    public static class MosCourseTypeBean {
//        /**
//         * id : 34
//         * typename : null
//         * datetime : 1504251061000
//         * state : 1
//         * typepic : null
//         * mosCourseList : null
//         */
//
//        private int id;
//        private Object typename;
//        private long   datetime;
//        private int    state;
//        private Object typepic;
//        private Object mosCourseList;
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public Object getTypename() {
//            return typename;
//        }
//
//        public void setTypename(Object typename) {
//            this.typename = typename;
//        }
//
//        public long getDatetime() {
//            return datetime;
//        }
//
//        public void setDatetime(long datetime) {
//            this.datetime = datetime;
//        }
//
//        public int getState() {
//            return state;
//        }
//
//        public void setState(int state) {
//            this.state = state;
//        }
//
//        public Object getTypepic() {
//            return typepic;
//        }
//
//        public void setTypepic(Object typepic) {
//            this.typepic = typepic;
//        }
//
//        public Object getMosCourseList() {
//            return mosCourseList;
//        }
//
//        public void setMosCourseList(Object mosCourseList) {
//            this.mosCourseList = mosCourseList;
//        }
//
//        @Override
//        public String toString() {
//            return "MosCourseTypeBean{" +
//                    "id=" + id +
//                    ", typename=" + typename +
//                    ", datetime=" + datetime +
//                    ", state=" + state +
//                    ", typepic=" + typepic +
//                    ", mosCourseList=" + mosCourseList +
//                    '}';
//        }
//    }
//
//    public static class MosDissertationBean {
//        /**
//         * id : 34
//         * typename : null
//         * datetime : 1504251061000
//         * state : 1
//         * typepic : null
//         */
//
//        private int id;
//        private Object typename;
//        private long   datetime;
//        private int    state;
//        private Object typepic;
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public Object getTypename() {
//            return typename;
//        }
//
//        public void setTypename(Object typename) {
//            this.typename = typename;
//        }
//
//        public long getDatetime() {
//            return datetime;
//        }
//
//        public void setDatetime(long datetime) {
//            this.datetime = datetime;
//        }
//
//        public int getState() {
//            return state;
//        }
//
//        public void setState(int state) {
//            this.state = state;
//        }
//
//        public Object getTypepic() {
//            return typepic;
//        }
//
//        public void setTypepic(Object typepic) {
//            this.typepic = typepic;
//        }
//
//        @Override
//        public String toString() {
//            return "MosDissertationBean{" +
//                    "id=" + id +
//                    ", typename=" + typename +
//                    ", datetime=" + datetime +
//                    ", state=" + state +
//                    ", typepic=" + typepic +
//                    '}';
//        }
//    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", coursename='" + coursename + '\'' +
                ", coursedesc='" + coursedesc + '\'' +
                ", coursepic='" + coursepic + '\'' +
                ", coursetype=" + coursetype +
                ", datetime=" + datetime +
                ", ishot=" + ishot +
                ", hotsort=" + hotsort +
                ", state=" + state +
                ", statesort=" + statesort +
                ", isnew=" + isnew +
                ", newsort=" + newsort +
//                ", mosCourseType=" + mosCourseType +
//                ", mosDissertation=" + mosDissertation +
                ", dissertationid=" + dissertationid +
                ", dissertationsort=" + dissertationsort +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getCoursedesc() {
        return coursedesc;
    }

    public void setCoursedesc(String coursedesc) {
        this.coursedesc = coursedesc;
    }

    public String getCoursepic() {
        return coursepic;
    }

    public void setCoursepic(String coursepic) {
        this.coursepic = coursepic;
    }

    public int getCoursetype() {
        return coursetype;
    }

    public void setCoursetype(int coursetype) {
        this.coursetype = coursetype;
    }

    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }

    public int getIshot() {
        return ishot;
    }

    public void setIshot(int ishot) {
        this.ishot = ishot;
    }

    public int getHotsort() {
        return hotsort;
    }

    public void setHotsort(int hotsort) {
        this.hotsort = hotsort;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getStatesort() {
        return statesort;
    }

    public void setStatesort(int statesort) {
        this.statesort = statesort;
    }

    public int getIsnew() {
        return isnew;
    }

    public void setIsnew(int isnew) {
        this.isnew = isnew;
    }

    public int getNewsort() {
        return newsort;
    }

    public void setNewsort(int newsort) {
        this.newsort = newsort;
    }

//    public MosCourseTypeBean getMosCourseType() {
//        return mosCourseType;
//    }
//
//    public void setMosCourseType(MosCourseTypeBean mosCourseType) {
//        this.mosCourseType = mosCourseType;
//    }
//
//    public MosDissertationBean getMosDissertation() {
//        return mosDissertation;
//    }
//
//    public void setMosDissertation(MosDissertationBean mosDissertation) {
//        this.mosDissertation = mosDissertation;
//    }

    public int getDissertationid() {
        return dissertationid;
    }

    public void setDissertationid(int dissertationid) {
        this.dissertationid = dissertationid;
    }

    public int getDissertationsort() {
        return dissertationsort;
    }

    public void setDissertationsort(int dissertationsort) {
        this.dissertationsort = dissertationsort;
    }
}
