package edu.whu.newspace.exception;

/**
 * @author Newspace
 * @version 1.0
 * @description 项目自定义错误
 * @date 2023/5/23 11:13
 */
public class TouTiaoException extends RuntimeException{
    private String errMessage;
    private String errCode;

    public TouTiaoException() {
        super();
    }

    public TouTiaoException(String errMessage) {
        super(errMessage);
        this.errMessage = errMessage;
    }

    public TouTiaoException(String errMessage, String errCode) {
        super(errMessage);
        this.errMessage = errMessage;
        this.errCode = errCode;
    }

    public String getErrMessage() {
        return errMessage;
    }
    public static void cast(String errMessage){
        throw new TouTiaoException(errMessage);
    }

    public static void cast(String errMessage, String errCode) {
        throw new TouTiaoException(errMessage, errCode);
    }
}
