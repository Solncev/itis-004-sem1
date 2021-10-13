package com.solncev.net.helper;

import com.cloudinary.Cloudinary;

import java.util.HashMap;
import java.util.Map;

public class CloudinaryHelper {
    private static Cloudinary cloudinary;

    public static Cloudinary getInstance() {
        if (cloudinary == null){
            Map configMap = new HashMap();
            //credentials from cloudinary app profile
            configMap.put("cloud_name", "cloud_name");
            configMap.put("api_key", "");
            configMap.put("api_secret", "");
            cloudinary = new Cloudinary(configMap);
        }
        return cloudinary;
    }
}
