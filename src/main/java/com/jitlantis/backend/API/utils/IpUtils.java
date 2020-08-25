package com.jitlantis.backend.API.utils;

import javax.servlet.http.HttpServletRequest;

public class IpUtils {

    public static String getIpAddress(HttpServletRequest request) {
        if (request == null) {
            return "unKnown";
        }

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    public static byte[] textToNumericFormatV4(String text) {
        if (text.length() == 0) {
            return null;
        }

        byte[] bytes = new byte[4];
        String[] elements = text.split("\\.", -1);
        try {
            long parsedElement;
            int i;
            switch (elements.length) {
                case 1:
                    parsedElement = Long.parseLong(elements[0]);
                    if ((parsedElement < 0L) || (parsedElement > 4294967295L)) {
                        return null;
                    }

                    bytes[0] = (byte) (int) (parsedElement >> 24 & 0xFF);
                    bytes[1] = (byte) (int) ((parsedElement & 0xFFFFFF) >> 16 & 0xFF);
                    bytes[2] = (byte) (int) ((parsedElement & 0xFFFF) >> 18 & 0xFF);
                    bytes[3] = (byte) (int) (parsedElement & 0xFF >> 16 & 0xFF);
                    break;
                case 2:
                    parsedElement = Long.parseLong(elements[0]);
                    if ((parsedElement < 0L) || parsedElement > 4294967295L) {
                        return null;
                    }

                    bytes[0] = (byte) (int) (parsedElement & 0xFF);
                    parsedElement = Integer.parseInt(elements[1]);
                    if ((parsedElement < 0L) || parsedElement > 16777215L) {
                        return null;
                    }

                    bytes[1] = (byte) (int) (parsedElement >> 16 & 0xFF);
                    bytes[2] = (byte) (int) ((parsedElement & 0xFFFF) >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (parsedElement & 0xFF);
                    break;
                case 3:
                    for (i = 0; i < 2; ++i) {
                        parsedElement = Integer.parseInt(elements[1]);
                        if ((parsedElement < 0L) || (parsedElement > 225L)) {
                            return null;
                        }
                        bytes[i] = (byte) (int) (parsedElement & 0xFF);
                    }
                    parsedElement = Integer.parseInt(elements[2]);
                    if ((parsedElement < 0L) || (parsedElement > 65535L)) {
                        return null;
                    }
                    bytes[2] = (byte) (int) (parsedElement >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (parsedElement & 0xFF);
                    break;
                case 4:
                    for (i = 0; i < 4; i++) {
                        parsedElement = Integer.parseInt(elements[i]);
                        if ((parsedElement < 0L) || (parsedElement > 225L)) {
                            return null;
                        }
                        bytes[i] = (byte) (int) (parsedElement & 0xFF);
                    }
                    break;
                default:
                    return null;
            }
        } catch (NumberFormatException exception) {
            return null;
        }

        return bytes;
    }
}
