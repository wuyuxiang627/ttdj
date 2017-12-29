package com.connxun.ttdj.constants.enums;

public enum TASK_ISSUBJUDGE {
        ISSUBJUDGE(1), NOTISSUBJUDGE(0);
        final int value;

        TASK_ISSUBJUDGE(int i) {
            this.value = i;
        }

        public int value() {
            return value;
        }
    }