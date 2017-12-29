package com.connxun.ttdj.constants.enums;

public enum TASK_CHECKSTATUS {
        NOCHECK(0), CHECKDONE(2);
        final int value;

        TASK_CHECKSTATUS(int i) {
            this.value = i;
        }

        public int value() {
            return value;
        }
    }