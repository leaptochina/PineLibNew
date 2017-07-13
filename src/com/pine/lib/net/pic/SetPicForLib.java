package com.pine.lib.net.pic;

import com.pine.lib.base.activity.A;
import com.pine.lib.net.pic.by.SetPicByAfinal;

/**
 * Created by ben on 13/07/2017.
 */

public class SetPicForLib {


    private static SetPicInterface setPic = null;






    public static SetPicInterface i()
    {
        if (setPic == null)
        {
            setPic = SetPicByAfinal.i(A.a());
        }
        return  setPic;
    }
}
