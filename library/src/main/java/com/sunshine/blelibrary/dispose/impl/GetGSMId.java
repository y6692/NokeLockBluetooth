package com.sunshine.blelibrary.dispose.impl;

import com.fitsleep.sunshinelibrary.utils.ConvertUtils;
import com.sunshine.blelibrary.config.Config;
import com.sunshine.blelibrary.dispose.BaseHandler;
import com.sunshine.blelibrary.utils.GlobalParameterUtils;

/**
 * GSM id
 * Created by sunshine on 2017/3/9.
 */

public class GetGSMId extends BaseHandler {
    @Override
    protected void handler(String hexString) {
        byte[] bytes = ConvertUtils.hexString2Bytes(hexString);
        String id = hexString.substring(6, 6 + (bytes[2]*2));
        GlobalParameterUtils.getInstance().sendBroadcast(Config.GSM_ID_ACTION,id);
    }

    @Override
    protected String action() {
        return "0523";
    }
}
