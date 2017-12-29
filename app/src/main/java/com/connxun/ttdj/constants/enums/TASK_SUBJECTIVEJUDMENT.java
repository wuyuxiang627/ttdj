package com.connxun.ttdj.constants.enums;

public enum TASK_SUBJECTIVEJUDMENT {
        NORMAL(1),ABNORMAL(0);
        final int value;

        TASK_SUBJECTIVEJUDMENT(int i) {
            this.value = i;
        }

        public int value() {
            return value;
        }
    }