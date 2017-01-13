package com.help.server.util;

import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by i on 18.02.15.
 */
public class FileUploadHelper {

    private static final List<MediaType> imageMediaTypes = new ArrayList<>(3);

    static {
        imageMediaTypes.add(MediaType.IMAGE_GIF);
        imageMediaTypes.add(MediaType.IMAGE_JPEG);
        imageMediaTypes.add(MediaType.IMAGE_PNG);
    }

    /**
     * @param filename File name
     * @return file extension without dot
     */
    public static String getFileExtension(String filename) {
        String re = "\\.(\\w+)$";
        Pattern pattern = Pattern.compile(re);
        Matcher matcher = pattern.matcher(filename);
        return matcher.find() ? matcher.group(1) : "";
    }

    /**
     * @param oldFileName Old file name. Need that get an extension file
     * @return new unique file name with old file extension
     */
    public static String getUniqueName(String oldFileName) {
        String extension = getFileExtension(oldFileName);
        String newName = UUID.randomUUID().toString();
        return newName + '.' + extension;
    }

    public static boolean isImageContentType(String contentType) {
        try {
            MediaType mediaType = MediaType.valueOf(contentType);
            return imageMediaTypes.indexOf(mediaType) > -1;
        } catch (InvalidMediaTypeException e) {
            return false;
        }
    }

    public static List<MediaType> getImageMediaTypes() {
        return new ArrayList<>(imageMediaTypes);
    }
}
