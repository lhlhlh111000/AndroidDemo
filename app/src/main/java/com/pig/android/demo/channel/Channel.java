package com.pig.android.demo.channel;

import android.app.Application;
import android.text.TextUtils;

/**
 * Title:
 * Description:
 * Copyright © 2001-2019 17173. All rights reserved.
 *
 * @author liangbx
 * @version 2019-05-22
 */
public class Channel {

    private static Channel sChannel;

    private String mName;

    public static void init(Application application) {
        sChannel = new Channel();
        sChannel.mName = WalleChannelReader.getChannel(application, "default");
        if(TextUtils.isEmpty(sChannel.mName)) {
            sChannel.mName = "default";
        }
    }

    public static Channel getInstance() {
        return sChannel;
    }

    public String getName() {
        return mName;
    }

    public String getParentName() {
        String[] names = mName.split("-");
        if(names.length > 0) {
            return names[0];
        } else {
            return mName;
        }
    }
}
