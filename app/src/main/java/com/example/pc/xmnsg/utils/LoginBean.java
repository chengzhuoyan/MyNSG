package com.example.pc.xmnsg.utils;

/**
 * Desc
 * Author 程茁燕
 * Time 2017/9/12.
 */
public class LoginBean extends Basebean {

    /**
     * code : 400
     * datas : {"error":"登录失败"}
     */

    private DatasBean datas;

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * error : 登录失败
         */

        private String error;

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }
}
